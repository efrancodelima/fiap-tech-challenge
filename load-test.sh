#!/bin/bash

for i in {1..500}
do
   curl -X GET "http://127.0.0.1:38461/api/v2/clientes/buscar/11122233396"
done
