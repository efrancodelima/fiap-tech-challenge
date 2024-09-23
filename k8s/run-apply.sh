#!/bin/bash

# Aplica os ConfigMaps
echo "Aplicando os ConfigMaps..."
minikube kubectl -- apply -f env-configmap.yaml
minikube kubectl -- apply -f metrics-server-config.yaml

# Verifica se os ConfigMaps estão prontos
while true; do
  configmap_status=$(minikube kubectl -- get configmap env -o jsonpath='{.metadata.name}')
  if [ "$configmap_status" == "env" ]; then
    echo "Variáveis de ambiente OK."
    break
  else
    echo "Aguardando..."
    sleep 2
  fi
done

# Inicia o volume de dados
echo "Iniciando o volume de dados..."
minikube kubectl -- apply -f dados-pvc.yaml

# Verifica se o volume está pronto
while true; do
  status=$(minikube kubectl -- get pvc dados-lanchonete -o jsonpath='{.status.phase}')
  if [ "$status" == "Bound" ]; then
    echo "Volume de dados OK."
    break
  else
    echo "Aguardando o volume de dados."
    sleep 2
  fi
done

# Inicia o banco de dados
echo "Iniciando o banco de dados..."
minikube kubectl -- apply -f bd-deployment.yaml
minikube kubectl -- apply -f bd-service.yaml

# Inicia a aplicação
echo "Iniciando a aplicação..."
minikube kubectl -- apply -f app-deployment.yaml
minikube kubectl -- apply -f app-service.yaml

echo "Script concluído com sucesso!"
