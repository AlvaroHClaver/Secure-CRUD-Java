# 🔐 Secure-CRUD-Java

Este projeto é uma API RESTful completa com autenticação baseada em **JWT**, segurança configurada com **Spring Security**, e persistência de dados em um banco **PostgreSQL**. O objetivo é fornecer um CRUD seguro e extensível para gestão de usuários, cidades, contas e aplicativos, com controle de acesso robusto.

---

## 🎯 Objetivo

Demonstrar como implementar um backend Java com:

- Autenticação via **JWT (JSON Web Token)**
- Autorização baseada em filtros personalizados do Spring Security
- Acesso seguro a endpoints
- Integração com banco **PostgreSQL**
- Separação clara entre as camadas (Controller, Service, Repository)
- Configuração do Swagger/OpenAPI para documentação da API

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.0.6
- Spring Security
- JWT (TokenService + FilterToken)
- PostgreSQL (via ElephantSQL)
- Spring Data JPA (Hibernate)
- SpringDoc OpenAPI (Swagger UI)
- Maven Wrapper

---

## 📁 Estrutura do Projeto

```
Secure-CRUD-Java/
├── src/main/java/com/api/
│   ├── ApiApplication.java              # Classe principal
│   ├── config/                          # Beans e configuração
│   │   ├── Configs.java                 # Segurança e CORS
│   │   └── OpenAPIConfig.java          # Swagger
│   ├── controller/                      # REST endpoints
│   │   ├── AuthController.java         # Autenticação/login
│   │   └── UserController.java         # CRUD usuários (autenticado)
│   ├── filter/                          # JWT Token Filter
│   │   └── FilterToken.java
│   ├── service/
│   │   └── TokenService.java           # Criação e validação do token JWT
│   ├── model/                           # Entidades
│   └── repository/                      # Repositórios JPA
├── application.properties               # Configuração do PostgreSQL e token
├── pom.xml                              # Maven
└── run.sh (opcional)
```

---

## 🔐 Segurança com JWT

A segurança é implementada por meio de:

- `AuthController`: gera o token JWT com base nas credenciais enviadas.
- `TokenService`: cria e valida os tokens com segredo e tempo de expiração.
- `FilterToken`: intercepta as requisições e valida o JWT antes de autorizar o acesso.
- `Configs.java`: registra o filtro e configura rotas públicas e protegidas.

### Configuração do Token (em `application.properties`):

```properties
api.token.secret=chave-ultra-secreta
api.token.issuer=api
api.token.expires=30
```

---

## 🗃️ Banco de Dados: PostgreSQL

O projeto se conecta ao banco **PostgreSQL** hospedado na ElephantSQL:

```properties
spring.datasource.url=jdbc:postgresql://babar.db.elephantsql.com/ddoskjic
spring.datasource.username=ddoskjic
spring.datasource.password=************
```

### Configurações adicionais:

- `spring.jpa.hibernate.ddl-auto=update` — cria e atualiza tabelas automaticamente
- `spring.jpa.show-sql=true` — imprime SQL no console

---

## 🚀 Como Executar

### Pré-requisitos

- Java 17
- Maven
- PostgreSQL (se quiser executar localmente)

### Passos

```bash
git clone https://github.com/AlvaroHClaver/Secure-CRUD-Java.git
cd Secure-CRUD-Java
./mvnw spring-boot:run
```

Acesse em:

- `http://localhost:8080` — API principal
- `http://localhost:8080/swagger-ui.html` — documentação OpenAPI

---

## 🧪 Endpoints de Exemplo

| Método | Rota              | Descrição                 |
|--------|-------------------|---------------------------|
| POST   | `/auth/login`     | Autentica e retorna token |
| GET    | `/user`           | Lista usuários            |
| POST   | `/conta`          | Cadastra conta            |
| GET    | `/cidade`         | Lista cidades             |

⚠️ Endpoints como `/user` exigem token JWT no header `Authorization: Bearer <token>`

---

## 📄 Licença

Distribuído sob a licença MIT. Veja o arquivo `LICENSE`.

---

> Projeto desenvolvido por [Alvaro H. Claver](https://github.com/AlvaroHClaver) com foco em segurança de aplicações Java modernas.
