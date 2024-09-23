#!/bin/bash

# Cadastrar cliente
curl -X 'POST' \
  'http://127.0.0.1:42223/api/v2/clientes/cadastrar' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "cpf": "2054358070",
  "nome": "Monteiro Lobato",
  "email": "mlobato@gmail.com"
}'

# Buscar cliente
for i in {1..500}
do
   curl -X GET "http://127.0.0.1:42223/api/v2/clientes/buscar/02054358070"
   sleep 0.1
done
