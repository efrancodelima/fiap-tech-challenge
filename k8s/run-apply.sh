#!/bin/bash

LOG_FILE="run-log.txt"
TIMEOUT=600 # 10 minutos
LOCK_FILE="/tmp/script.lock"

source ./run-functions.sh

main() {
  touch $LOCK_FILE

  # Inicia o timeout
  (sleep $TIMEOUT && [ -f $LOCK_FILE ] && echo "$(date '+%Y-%m-%d %H:%M:%S') - Timeout atingido!" | tee -a $LOG_FILE && kill $$) &

  # Inicia as variáveis de ambiente
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando as variáveis de ambiente" | tee -a $LOG_FILE
  minikube kubectl -- apply -f env-configmap.yaml >> $LOG_FILE 2>&1
  wait_for_resource "configmap" "env-config" "default" "Variáveis de ambiente OK"

  # Inicia o volume de dados
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o volume de dados" | tee -a $LOG_FILE
  minikube kubectl -- apply -f dados-pvc.yaml >> $LOG_FILE 2>&1
  wait_for_resource "pvc" "dados-lanchonete" "default" "Volume de dados OK"

  # Inicia o banco de dados
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o banco de dados" | tee -a $LOG_FILE
  minikube kubectl -- apply -f bd-deployment.yaml >> $LOG_FILE 2>&1
  minikube kubectl -- apply -f bd-service.yaml >> $LOG_FILE 2>&1

  # Inicia a aplicação
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando a aplicação" | tee -a $LOG_FILE
  minikube kubectl -- apply -f app-deployment.yaml >> $LOG_FILE 2>&1
  minikube kubectl -- apply -f app-service.yaml >> $LOG_FILE 2>&1
  wait_for_pod "app-lanchonete" "default" "Aplicação OK"

  # Inicia o metrics-server
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o metrics server" | tee -a $LOG_FILE
  minikube kubectl -- apply -f metrics-server-config.yaml >> $LOG_FILE 2>&1
  wait_for_resource "configmap" "metrics-server-config" "kube-system" "Metrics server OK"

  # Inicia o HPA
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o HPA" | tee -a $LOG_FILE
  minikube kubectl -- apply -f app-hpa.yaml >> $LOG_FILE 2>&1
  wait_for_hpa "app-hpa" "default" "HPA OK"

  # Finaliza o script
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Script concluído com sucesso!" | tee -a $LOG_FILE
  echo -e "\n\n" >> $LOG_FILE
  rm -f $LOCK_FILE
}

main 2>>$LOG_FILE
