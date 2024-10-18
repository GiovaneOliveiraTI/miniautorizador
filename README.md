# MiniAutorizador

MiniAutorizador é uma aplicação baseada em Java desenvolvida usando Spring Boot e Maven. 
Ela é projetada para lidar com operações relacionadas a cartões de crédito.

## Recursos

- Criar um novo cartão
- Consultar o saldo de um cartão
- Fazer uma transação em um cartão

## Tecnologias

- Java 17
- Spring Framework 5.3.14
- Spring Data JPA 2.5.6
- Spring Boot 3.3.4
- Lombok
- Maven
- MySQL
- Hibernate 6.5.3.Final
- Spring HATEOAS 2.3.3
- JUnit 5
- Mockito

## Configuração

Para executar este projeto, você precisa ter o Java 17, Maven, docker  e postman(ou outra ferramenta de sua Preferência) instalados em sua máquina.

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando `mvn clean install` para construir o projeto
4. Dentro do diretorio que esta localizado o arquivo docker-compose.yml execute docker-compose up -d
5. Execute o comando `mvn spring-boot:run` para iniciar a aplicação
6. A aplicação estará disponível em `http://localhost:8080`

## Endpoints da API

- `POST /cartoes` - Criar um novo cartão
- `GET /cartoes/{numeroCartao}` - Consultar o saldo de um cartão
- `POST /transacoes` - Fazer uma transação em um cartão


