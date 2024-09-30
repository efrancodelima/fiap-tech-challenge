#!/bin/bash

load_image_if_not_exists() {
  local image=$1
  if minikube image ls | grep -q "$image"; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Imagem $image já está presente" | tee -a $LOG_FILE
  else
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Iniciando o download da imagem $image" | tee -a $LOG_FILE
    load_image_with_message "$image"
  fi
}

load_image_with_message() {
  local image=$1
  {
    minikube image load $image &
    pid=$!
    while kill -0 $pid 2>/dev/null; do
      echo "$(date '+%Y-%m-%d %H:%M:%S') - Baixando a imagem $image" | tee -a $LOG_FILE
      sleep 5
    done
    wait $pid
    if [ $? -ne 0 ]; then
      echo "$(date '+%Y-%m-%d %H:%M:%S') - Erro ao baixar a imagem $image" | tee -a $LOG_FILE
      exit 1
    fi
  } 2>/dev/null
  echo "$(date '+%Y-%m-%d %H:%M:%S') - Imagem $image baixada com sucesso" | tee -a $LOG_FILE
}

wait_for_resource() {
  local resource_type=$1
  local resource_name=$2
  local namespace=$3
  local success_message=$4

  while true; do
    resource_status=$(minikube kubectl -- get $resource_type $resource_name -n $namespace -o jsonpath='{.metadata.name}' 2>>$LOG_FILE)
    if [ "$resource_status" == "$resource_name" ]; then
      echo "$(date '+%Y-%m-%d %H:%M:%S') - $success_message" | tee -a $LOG_FILE
      break
    else
      echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando $resource_name" | tee -a $LOG_FILE
      sleep 2
    fi
  done
}

wait_for_pod() {
  local selector=$1
  local namespace=$2
  local success_message=$3
  while true; do
    pod_status=$(minikube kubectl -- get pods --selector=$selector -n $namespace -o jsonpath='{.items[0].status.containerStatuses[0].ready}' 2>>$LOG_FILE)
    if [ "$pod_status" == "true" ]; then
      echo "$(date '+%Y-%m-%d %H:%M:%S') - $success_message" | tee -a $LOG_FILE
      break
    else
      echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando $selector" | tee -a $LOG_FILE
      sleep 5
    fi
  done
}

wait_for_hpa() {
  local hpa_name=$1
  local namespace=$2
  local success_message=$3
  while true; do
    hpa_status=$(minikube kubectl -- get hpa $hpa_name -n $namespace -o jsonpath='{.status.conditions[?(@.type=="AbleToScale")].status}' 2>>$LOG_FILE)
    if [ "$hpa_status" == "True" ]; then
      echo "$(date '+%Y-%m-%d %H:%M:%S') - $success_message" | tee -a $LOG_FILE
      break
    else
      echo "$(date '+%Y-%m-%d %H:%M:%S') - Aguardando $hpa_name" | tee -a $LOG_FILE
      sleep 5
    fi
  done
}
