# ETAPA 1: Compilação do projeto
## Usa uma imagem base como builder
FROM maven:3.9.8-eclipse-temurin-17-alpine AS builder

## Define o diretório de trabalho do builder
WORKDIR /app

## Copia o projeto para o builder
COPY src /app/src
COPY pom.xml /app

## Baixa as dependências do projeto
RUN mvn dependency:go-offline
RUN mvn clean package -Dmaven.test.skip=true


# ETAPA 2: Criação da imagem do projeto
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

## Copia o projeto compilado no builder para o diretório de trabalho da imagem
COPY --from=builder /app/target/tech-challenge-*.jar .

## Copia e executa o script que inicializa a aplicação
COPY run-project.sh .
RUN chmod +x run-project.sh
CMD ["sh", "-c", "./run-project.sh"]
