# PosTech - Software Architecture

#### Projeto relacionado ao Tech Challenge fornecido como atividade avaliativa do curso de pós graduação em Arquitetura de Software da FIAP.

## Objetivos

Desenvolver um sistema para uma lanchonete, seguindo os pré-requisitos do Tech Challenge.

## Requisitos do Tech Challenge

#### Arquitetura:

Uma das premissas para esse projeto, foi desenvolver um sistema monolito, seguindo a arquitetura hexagonal.</br>
Ou seja, a ideia principal é conseguir fornecer um sistema que favorece reusabilidade de código, alta coesão, baixo acoplamento, independência de tecnologia e que são mais fáceis de serem testados.

#### API's:

- Cadastro do Cliente
- Identificação do Cliente via CPF
- Criar, editar e remover produtos
- Buscar produtos por categoria
- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.
- Listar os pedidos

#### Banco de dados:

De livre escolha (utilizamos o MySQL).

## Pré-requisitos para executar a aplicação

Você precisa ter o docker e o docker-compose instalados na sua máquina para poder rodar o projeto.<br>
Também vai precisar do git para poder clonar o projeto.<br>
As instruções citadas nesse documento foram testadas com:

- Linux Ubuntu 22.04.4 LTS;
- Docker 27.1.1;
- Docker Compose 1.26.0.

## Roteiro para executar a aplicação

1. Clone o projeto na sua máquina </br>
   **Certifique-se ter o git instalado em sua máquina e execute o comando abaixo pelo terminal:**  
   `git clone https://github.com/HenriqueSaKi/fiap-tech-challenge-8soat.git`

2. Crie as imagens e suba os containeres</br>
   **Acesse a pasta raiz do projeto e execute os comandos abaixo:**

   ```sh
   # Crie as imagens docker
   docker-compose build

   # Suba os containeres
   docker-compose up -d

   # Confira se os containeres foram iniciados corretamente
   # O status deve estar 'Up'
   docker-compose ps -a
   ```

3. Acesse o Swagger da aplicação, pelo link abaixo</br>
   http://localhost:8080/api/v1/swagger-ui/index.html

## Outros comandos relacionados a containeres, aplicação e banco de dados.

#### 1. [Docker] Comando para reiniciar os containeres

```sh
# Reinicializa os containeres
docker-compose restart
```

#### 2. [Docker] Comandos para visualizar os logs dos containeres

```sh
# Log do banco de dados
docker logs bd_lanchonete

# Log da aplicação
docker logs app_lanchonete
```

#### 3. [Banco de dados] Testar o banco de dados individualmente

Suba o container do banco de dados, abra o terminal e execute o comando abaixo.

```sh
# Executa o mysql pela interface de linha de comando (CLI)
docker exec -it bd_lanchonete mysql -u user_fiap -p
# Após inserir a senha, selecione o database
use lanchonete;
```

Nota: se você subiu apenas o banco de dados e tentou testá-lo, provavelmente não encontrou nenhuma tabela no banco de dados. Isso ocorre porque as tabelas são criadas pela aplicação usando JPA.

#### 4. [Banco de dados] Comando para apagar o volume de dados

Cuidado: o procedimento abaixo apagará todos os registros do banco.

```sh
# Reinicia todos os containeres
# Um novo volume de dados será criado durante a reinicialização
docker-compose down -v && docker-compose up -d
```

#### 5. [Aplicação] Caso queira executar apenas a aplicação localmente, siga os passos abaixo:

1. Instale as dependências:</br>
   `mvn clean install`
2. Inclua as variáveis de ambiente relacionados ao banco de dados na sua IDE. </br>

   ```
   DATASOURCE_URL=
   DATASOURCE_USERNAME=
   DATASOURCE_PASSWORD=
   DATASOURCE_DRIVER_CLASS_NAME=
   ```

   **Observação:** Caso não tenha uma preferência de banco de dados, recomendo utilizar o banco de dados H2.
   <details>
     <summary>Explicação de como utilizar o H2, clique para expandir.</summary>

   O primeiro passo para configurarmos o banco será adicionar a dependência do H2 no arquivo pom.xml

   ```XML
   <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
   </dependency>

   ```

   Feito isso, podemos alterar nosso arquivo application.yml, com as informações padrões do H2.

   ```YAML
   spring:
      application:
         name: tech-challenge
      datasource:
         url: ${DATASOURCE_URL:jdbc:h2:mem:testdb}
         username: ${DATASOURCE_USERNAME:sa}
         password: ${DATASOURCE_PASSWORD:password}
         driverClassName: ${DATASOURCE_DRIVER_CLASS_NAME:org.h2.Driver}
   ```

   **Observação:** Mantendo as configurações dessa forma, você permite que por padrão o banco de dados utilizado seja o H2. No entanto, caso você informe na sua IDE as variáveis de ambiente de acordo com o banco de dados de sua preferência e incluir as dependências necessárias, você poderá utilizar inúmeras opções de banco de dados relacional.

   Por fim, podemos habilitar a visualização do console do H2 adicionando a seguinte configuração:

   ```YAML
   spring:
      h2:
         console:
            enabled: true
            path: /h2-console
   ```

   **Observação:** Após subir sua aplicação com o banco de dados H2, você poderá acessar o console do banco de dados através desse link: http://localhost:8080/api/v1/h2-console

  </details>

4. Tudo pronto, só dar um <i>Run</i> :arrow_forward: na aplicação!
