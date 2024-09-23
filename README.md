# Tech Challenge - Fase 2

Projeto fornecido como atividade avaliativa do curso de **Software Architecture - Pós-Tech - FIAP**. \

Link do projeto no GitHub: https://github.com/efrancodelima/fiap-tech-challenge<br><br>
Link do Swagger: http://localhost:8080/api/v2/swagger-ui/index.html<br><br>
Link do vídeo demonstrando a arquitetura: pendente

## Objetivos

Desenvolver um sistema para uma lanchonete, seguindo os pré-requisitos especificados no Tech Challenge.

## Requisitos do negócio

### Arquitetura

O projeto da fase 1 (arquitetura hexagonal) deverá ser adaptado para a Clean Architecture.

### API's web

Cliente

- Cadastrar cliente
- Buscar cliente pelo CPF

Produto:

- Criar, editar e remover produtos
- Buscar produtos por categoria

Pedido

- Fazer checkout
  - Deverá retornar a identificação do pedido
- Atualizar o status do pedido
- Consultar o status do pagamento
- Listar pedidos nessa ordem: Pronto > Em Preparação > Recebido
  - Pedidos mais antigos primeiro e mais novos depois.
  - Pedidos finalizados não devem aparecer na lista.

### Infraestrutura

Usamos o minikube para rodar os containeres na máquina local. \
Para o banco de dados, foi utilizado o MySQL 8.4.0.

## Instruções para executar a aplicação

### Pré-requisitos

As instruções citadas nesse documento foram testadas com:

- Linux Ubuntu 22.04.4 LTS;
- Docker 27.2.1
- Minikube 1.34.0
- helm 3.16.1
- Git 2.34.1

### Alguns esclarecimentos antes de iniciar

O minikube irá trabalhar com as imagens do DockerHub, que foram construídas a partir deste projeto. \
Poderíamos trabalhar com imagens locais, mas preferimos colocar no DockerHub para deixar salvo na nuvem. \
Se desejar, você pode fazer o build da aplicação local com os comandos abaixo:

```
docker build -t app-lanchonete -f Dockerfile.app .
docker build -t bd-lanchonete -f Dockerfile.bd .
```

Como você pode perceber, nós temos dois arquivos dockerfile: um para a aplicação e outro para o banco de dados. \
Criamos uma imagem customizada para o banco de dados a fim de poder populá-lo com dados de exemplo. \
Em uma aplicação real, o banco de dados não viria pré-populado, mas aqui fizemos desta forma porque facilita na hora de testar e conhecer a aplicação.

### Rodando a aplicação

#### 1. Inicie o Minikube.

Apague o cluster anterior, se já tiver um, pois o minikube não trabalha com mais de um cluster simultâneo.

```
minikube delete
```

Inicie o minikube com o comando abaixo. \
Ajuste os valores de cpu e memória, se necessário. \
CPU se refere à quantidade de núcleos e memory está em MiB.

```
minikube start --driver=docker --cpus=3 --memory=3870
```

#### 2. Habilite os addons necessários para a aplicação.

Iremos utilizar o metrics para que o HPA possa funcionar e o dashboard para analisar o cluster.

```
minikube addons enable metrics-server
minikube addons enable dashboard
```

#### 3. Abra um terminal e clone o projeto.

```
git clone https://github.com/efrancodelima/fiap-tech-challenge.git
```

#### 4. Vá para o diretório onde estão os arquivos manifestos.

No nosso caso, é o diretório k8s, que fica na raiz do projeto

#### 5. Inicie a aplicação.

O comando abaixo cria ou atualiza os recursos existentes. \
Não instalamos o kubectl diretamente, pois ele já vem incluído no minikube.

```
minikube kubectl -- apply -f .
```

#### 6. Acompanhe a inicialização dos PODs.

O comando abaixo exibe os pods e atualiza a tela a cada 2 segundos. \
Esse é um comando não responsivo, ou seja, ele irá usar/ocupar o terminal enquanto estiver rodando. Digite CTRL+C quando quiser sair. \
Se tudo estiver ok, os PODs irão subir e após algum tempo deverão estar com "STATUS Running" e "READY 1/1".

```
watch -n 2 minikube kubectl -- get pods
```

#### 7. Em caso de erro no POD.

Essa aplicação roda na máquina local e os health checks foram configurados conforme os recursos do ambiente local. \
É possível que, em outra máquina, com cpu e memória diferentes, os PODs demorem mais para responder e talvez seja necessário um ajuste nos tempos da configuração dos health checks. \
Verifique a descrição detalhada do POD com o comando abaixo. Se alguma probe falhar, irá aparecer nessa descrição. \
O `<nome_pod>` deve ser igual ao que foi mostrado na etapa anterior, com o comando get pods.

```
minikube kubectl -- describe pod <nome_pod>
```

Se não resolver, verifique o log do pod.

```
minikube kubectl -- logs -f <nome_pod>
```

#### 8. Acesse a aplicação pelo navegador.

Use o comando abaixo para expor o serviço para acesso externo. Uma `<URL>` será gerada. \
Esse é um comando não responsivo, digite CTRL+C quando quiser sair. Note que ao liberar o terminal, o comando não estará mais executando e o acesso externo será cortado (a `<URL>` vai parar de funcionar).

```
minikube service app --url
```

Abra o navegador e acesse: `<URL>`/api/v2/ \
Esse link deverá abrir o Swagger da aplicação.

#### 9. Acesse o dashboard do minikube.

Veja informações mais detalhadas sobre a aplicação no dashboard do minikube. \
Use o comando abaixo para gerar o link para o dashboard.

```
minikube dashboard --url
```

#### 10. Encerre a aplicação e libere os recursos.

Se precisar encerrar a aplicação e liberar (apagar) os recursos criados, execute:

```
# Apaga os recursos criados com o 'apply'.
minikube kubectl -- delete -f .

# Apaga o cluster
minikube delete

# Para o minikube
minikube stop
```

## Estrutura do projeto

O projeto foi estruturado em diretórios e subdiretórios conforme as camadas da Clean Architecture. \
As camadas foram nomeadas como: "business layer", "application layer", "interface layer" e "external layer".

- tech_challenge
  - business_layer
  - application_layer
  - interface_layer
  - external_layer

### Camada de negócios

- business_layer
  - entities
    - enums
  - constants
  - exceptions
    - messages

#### entities

Aqui temos as entidades de negócio, construídas como POJOs (Plain Old Java Objects). \
Temos alguns VOs (Value Objects) entre elas (CPF, ItemPedido, StatusPedido e StatusPagamento), trabalhando com a ideia de composição. \
Temos também alguns enums: CategoriaProduto, StatusPedidoEnum e StatusPagamentoEnum.

#### constants

Temos uma classe que guarda e compartilha a data/hora mínima que o sistema aceita em suas validações. \
Temos três datas/horas no sistema: do checkout, do pagamento e do status do pedido.

#### exceptions

Em exceptions, criei uma classe customizada (BusinessRuleException) para as exceções lançadas por esta camada. \
As mensagens das exceções ficam catalogadas nos enums dentro de /exceptions/messages.

### Camada de aplicação

- application_layer
  - exceptions
    - messages
  - interfaces
    - gateways
  - use_cases
    - interfaces

#### use_cases

Aqui temos os casos de uso, todos com interface e implementação.

#### interfaces / gateways

Temos as interfaces dos gateways, sem a implementação, que fica em outra camada. \
As interfaces dos gateways são necessárias aqui para que os casos de uso possam saber como recuperar os dados necessários à sua função. \
Pesquisando, encontrei outras formas de fazer isso, mas optei por essa que me pareceu a mais robusta.

#### exceptions

Por fim temos as exceções, no mesmo formato da camada anterior: classes customizadas e mensagens de erro catalogadas em enums.

### Camada de interface/adaptação

- interface_layer
  - controllers
    - adapters
    - request_adapters
    - response_adapters
  - dtos
  - interfaces
  - exception_handler
  - gateways
  - entities
  - mappers
  - repositories

#### controllers

Nesta camada temos a interface e a implementação dos controllers. \
O controller:

- recebe a requisição da camada externa
- adpta o formato para um objeto que o caso de uso conheça (entidades de negócio)
- chama o caso de uso apropriado, passando o gateway para ele (inversão de dependência)
- recebe a resposta do caso de uso
- adapta a resposta para a camada externa (com ajuda do presenter)

#### controllers / dtos

Os DTOs documentam os tipos de requisições que o Controller aceita. \
Por ser um DTO, uma classe java muito simples, não fiz a interface para eles, somente a implementação.

#### controllers / adapters

Temos os adapters, tanto da requisição quanto da resposta. \
Os response_adapters fazem o papel do Presenter (dei outro nome, mas a função é a mesma).

#### gateways

Aqui temos os gateways implementados. Os gateways ligam os use cases com os repositórios para persistência de dados. Use cases > gateways > repositórios. Aplicação > interface > camada externa. \
Temos também as entidades JPA (ORM), quer não se confundem com as entidades de negócio. \
E temos as interfaces dos repositórios, para que o gateway saiba como utilizá-los.

#### gateways / mappers

Os mappers também trabalham como adaptadores: eles convertem as entidades de negócio em entidades JPA e vice-versa. \
Deixei os mappers dentro da pasta gateways e os adaptadores dentro de controllers para não confundir. \
Os dois fazem um trabalho parecido, mas cada um em um contexto diferente.

#### exceptions_handler

Por fim, temos uma pasta chamada exception_handler, que também faz um papel de "adaptar", só que um pouco diferente. \
Ela capturas as exceções lançadas pelo sistema e as transforma em objetos do tipo ErrorResponse com os campos: code, status, message e timestamp. \
Depois encapsula esse objeto em um ResponseEntity, que é o mesmo usado pelos response_adapters (presenters). \
Lembra daquelas exceções customizadas que criamos nas camadas internas (domain e application)? Então, o handler vai usar aquelas exceções para ajustar corretamente o statusCode de cada uma.

### Camada externa

- external_layer
  - apis
    - interfaces
  - swagger

Nesse projeto, usamos SpringBoot e JPA. \
Normalmente a conexão com o banco de dados seria feita na camada externa (de infra), mas o SpringBoot já gerencia automaticamente as conexões, então isso não foi necessário. \
A implementação do repositório para acesso ao banco de dados também seria feita nesta camada e depois passada para o Gateway por meio do Controller. \
Mas, como estamos usando JPA, os repositórios não necessitam ser implementados. Além disso, o SpringBoot injeta o repositório no gateway automaticamente. \
Enfim, ajustamos os princípios da Clean Architecture para as tecnologias utilizadas no projeto. \

#### apis

Aqui temos as APIs web (endpoints) com interface e implementação. \
As anotações do Swagger ficam apenas na interface, deixando o código da classe mais limpo, pois são muitas anotações. \
Além disso, também atendemos ao princípio da "programação para interface".

#### swagger

Na pasta do swagger temos apenas um arquivo de configuração para a API do sistema.
