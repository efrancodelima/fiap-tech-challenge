## Define a imagem base
FROM openjdk:17-jdk-alpine

## Define as variáveis de ambiente
ENV DATASOURCE_URL=${DATASOURCE_URL}
ENV DATASOURCE_USERNAME=${DATASOURCE_USERNAME}
ENV DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD}
ENV DATASOURCE_DRIVER_CLASS_NAME=${DATASOURCE_DRIVER_CLASS_NAME}
ENV JPA_DIALECT=${JPA_DIALECT}

## Define o diretório de trabalho da imagem
WORKDIR /app

## Copia a aplicação para o diretório de trabalho da imagem
COPY target/tech-challenge-*.jar .

## Script para inicializar a aplicação
COPY run-project.sh .
RUN chmod +x run-project.sh
CMD ["sh", "-c", "./run-project.sh"]
