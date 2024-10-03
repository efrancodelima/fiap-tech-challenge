#!/bin/bash

LOG_FILE="run-log.txt"
TIMEOUT=360 # 6 minutos
LOCK_FILE="/tmp/script.lock"

source ./run-functions.sh

main() {
  touch $LOCK_FILE

  # Inicia o timeout
  (sleep $TIMEOUT && [ -f $LOCK_FILE ] && echo "$(date '+%Y-%m-%d %H:%M:%S') - Timeout atingido!" | tee -a $LOG_FILE && kill $$) &

  # Inicia as variáveis de ambiente
  echo -e "\n$(date '+%Y-%m-%d %H:%M:%S') - Iniciando as variáveis de ambiente" | tee -a $LOG_FILE
  minikube kubectl -- apply -f env-config.yaml | tee -a $LOG_FILE
  wait_for_resource "configmap" "env-config" "default" "Variáveis de ambiente OK"

  # Inicia o volume de dados
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o volume de dados" | tee -a $LOG_FILE
  minikube kubectl -- apply -f dados-pvc.yaml | tee -a $LOG_FILE
  wait_for_resource "pvc" "dados-pvc" "default" "Volume de dados OK"

  # Inicia o banco de dados
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o banco de dados" | tee -a $LOG_FILE
  minikube kubectl -- apply -f mysql-deployment.yaml | tee -a $LOG_FILE
  minikube kubectl -- apply -f mysql-service.yaml | tee -a $LOG_FILE

  # Inicia a aplicação
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando a aplicação" | tee -a $LOG_FILE
  minikube kubectl -- apply -f app-deployment.yaml | tee -a $LOG_FILE
  minikube kubectl -- apply -f app-service.yaml | tee -a $LOG_FILE
  wait_for_pod "app" "default" "Aplicação OK"

  # Inicia o metrics-server
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o metrics server" | tee -a $LOG_FILE
  minikube kubectl -- apply -f metrics-server-config.yaml | tee -a $LOG_FILE
  wait_for_resource "configmap" "metrics-server-config" "kube-system" "Metrics server OK"

  # Inicia o HPA
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o HPA" | tee -a $LOG_FILE
  minikube kubectl -- apply -f app-hpa.yaml | tee -a $LOG_FILE
  wait_for_hpa "app-hpa" "default" "HPA OK"

  # Finaliza o script
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Script concluído com sucesso!" | tee -a $LOG_FILE
  rm -f $LOCK_FILE
}

main 2>>$LOG_FILE
