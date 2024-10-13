#!/bin/bash

end=$((SECONDS+120))

while [ $SECONDS -lt $end ]; do
  curl -X GET "http://127.0.0.1:39503/api/v2/clientes/buscar/12345678900"
  curl -X GET "http://127.0.0.1:39503/api/v2/produtos/buscar/lanche"
  curl -X GET "http://127.0.0.1:39503/api/v2/pedidos/listar"
done
