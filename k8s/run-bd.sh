#!/bin/bash

# Aplica os ConfigMaps
echo "$(date '+%Y-%m-%d %H:%M:%S') - Aplicando os ConfigMaps..."
minikube kubectl -- apply -f env-configmap.yaml
minikube kubectl -- apply -f prometheus-config.yaml
minikube kubectl -- apply -f prometheus-adapter-config.yaml

# Verifica se os ConfigMaps estão prontos
while true; do
  configmap_status=$(minikube kubectl -- get configmap env-config -o jsonpath='{.metadata.name}')
  if [ "$configmap_status" == "env-config" ]; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Variáveis de ambiente OK."
    break
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando env-config..."
    sleep 2
  fi
done

while true; do
  configmap_status=$(minikube kubectl -- get configmap prometheus-config -n kube-system -o jsonpath='{.metadata.name}')
  if [ "$configmap_status" == "prometheus-config" ]; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Prometheus OK."
    break
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando prometheus-config..."
    sleep 2
  fi
done

while true; do
  configmap_status=$(minikube kubectl -- get configmap prometheus-adapter-config -n kube-system -o jsonpath='{.metadata.name}')
  if [ "$configmap_status" == "prometheus-adapter-config" ]; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Prometheus Adapter OK."
    break
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando prometheus-adapter-config..."
    sleep 2
  fi
done

# Inicia o volume de dados
echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o volume de dados..."
minikube kubectl -- apply -f dados-pvc.yaml

# Verifica se o volume de dados está pronto
while true; do
  status=$(minikube kubectl -- get pvc dados-lanchonete -o jsonpath='{.status.phase}')
  if [ "$status" == "Bound" ]; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Volume de dados OK."
    break
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando o volume de dados..."
    sleep 2
  fi
done

# Baixa as imagens necessárias
echo "$(date '+%Y-%m-%d %H:%M:%S') - Baixando as imagens..."
docker pull efrancodelima/app-lanchonete:latest
docker pull mysql:8.4.0

# Verifica se as imagens foram baixadas
while true; do
  app_image_status=$(docker images -q efrancodelima/app-lanchonete:latest)
  mysql_image_status=$(docker images -q mysql:8.4.0)
  if [ -n "$app_image_status" ] && [ -n "$mysql_image_status" ]; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Imagens baixadas com sucesso."
    break
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando o download das imagens..."
    sleep 5
  fi
done

# Inicia o banco de dados
echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o banco de dados..."
minikube kubectl -- apply -f bd-deployment.yaml
minikube kubectl -- apply -f bd-service.yaml

# Verifica se o banco de dados está pronto
while true; do
  pod_status=$(minikube kubectl -- get pods --selector=app=bd-lanchonete -o jsonpath='{.items[0].status.containerStatuses[0].ready}')
  if [ "$pod_status" == "true" ]; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aplicação OK."
    break
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando o banco de dados..."
    sleep 5
  fi
done
