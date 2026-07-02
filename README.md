# 💸 MiniPix

> Um projeto desenvolvido para simular um sistema de transferências Pix utilizando **Java**, **Spring Boot**, **JPA/Hibernate**, **PostgreSQL**, **Auth0** e arquitetura REST.

Ideal para estudantes que desejam praticar desenvolvimento de APIs, relacionamentos entre entidades, autenticação e persistência de dados.

---

# 🚀 Tecnologias utilizadas

- ☕ Java
- 🍃 Spring Boot
- 🗄️ Spring Data JPA
- 🐘 PostgreSQL
- 🔐 Auth0
- 📦 Maven
- 🌐 REST API
- 🔄 Jackson

---

# 📚 O que este projeto possui?

O MiniPix implementa um fluxo completo de transferência Pix, contendo:

- 👤 Conta
- 🔑 Chave Pix
- 👥 Favorecido
- 💸 Transferência Pix
- 🧾 Comprovante

Todas as entidades possuem operações de:

- ✅ Create
- 🔍 Read
- ✏️ Update
- 🗑️ Delete

Além disso, o projeto utiliza relacionamentos entre entidades através do JPA.

---

# ⚠️ Importante

Durante o desenvolvimento foi necessário utilizar `@JsonIgnore` em alguns relacionamentos para evitar recursão infinita durante a serialização realizada pelo Jackson.

Sem isso ocorria um ciclo semelhante a:

```
Conta
 └── Transferência Enviada
      └── Conta Origem
           └── Transferência Enviada
                └── Conta Origem
                     ...
```

O mesmo acontecia entre:

```
Transferência
 └── Comprovante
      └── Transferência
           └── Comprovante
                ...
```

Esse é um comportamento bastante comum em relacionamentos bidirecionais utilizando JPA.

---

# ▶️ Como executar o projeto

Antes de iniciar, tenha instalado:

- ☕ Java
- 🐘 PostgreSQL
- 📦 Maven

Também será necessário possuir:

- 🔐 Uma aplicação configurada no Auth0

---

## Configure o banco

No arquivo:

```
application.properties
```

Configure:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

---

## Configure o Auth0

Altere os seguintes campos:

```properties
auth0.issuer=
auth0.audience=
```

Utilize os valores correspondentes à sua aplicação cadastrada no Auth0.

---

## Execute

Basta iniciar a aplicação pela classe:

```
MinipixApplication.java
```

A API ficará disponível em:

```
http://localhost:8080
```

---

# 🔐 Autenticação

Todos os exemplos abaixo foram disponibilizados **sem autenticação**, para facilitar a leitura.

Não esqueça de adicionar o Bearer Token nas requisições:

```http
Authorization: Bearer SEU_TOKEN
```

---

# 🧪 Ordem recomendada para testar a API

Como existem relacionamentos entre as entidades, siga esta sequência:

```text
1️⃣ Criar Conta

      ↓

2️⃣ Criar Chave Pix

      ↓

3️⃣ Criar Favorecido

      ↓

4️⃣ Criar Transferência

      ↓

5️⃣ Criar Comprovante

      ↓

6️⃣ Consultar (GET)

      ↓

7️⃣ Atualizar (PUT)

      ↓

8️⃣ Excluir (DELETE)
```

A ordem é importante para evitar erros de chave estrangeira.

---

# 📌 Endpoints

## 👤 Conta

| Método | Endpoint |
|---------|----------|
| POST | `/contas` |
| GET | `/contas` |
| GET | `/contas/{id}` |
| PUT | `/contas/{id}` |
| DELETE | `/contas/{id}` |

---

## 🔑 Chave Pix

| Método | Endpoint |
|---------|----------|
| POST | `/chaves-pix` |
| GET | `/chaves-pix` |
| GET | `/chaves-pix/{id}` |
| PUT | `/chaves-pix/{id}` |
| DELETE | `/chaves-pix/{id}` |

---

## 👥 Favorecido

| Método | Endpoint |
|---------|----------|
| POST | `/favorecidos` |
| GET | `/favorecidos` |
| GET | `/favorecidos/{id}` |
| PUT | `/favorecidos/{id}` |
| DELETE | `/favorecidos/{id}` |

---

## 💸 Transferência Pix

| Método | Endpoint |
|---------|----------|
| POST | `/transferencias-pix` |
| GET | `/transferencias-pix` |
| GET | `/transferencias-pix/{id}` |
| PUT | `/transferencias-pix/{id}` |
| DELETE | `/transferencias-pix/{id}` |

---

## 🧾 Comprovante

| Método | Endpoint |
|---------|----------|
| POST | `/comprovantes` |
| GET | `/comprovantes` |
| GET | `/comprovantes/{id}` |
| PUT | `/comprovantes/{id}` |
| DELETE | `/comprovantes/{id}` |

---

# 📂 Exemplos de requisições

Todos os exemplos utilizando **cURL** estão disponíveis logo abaixo neste README.

A sequência já está organizada na ordem correta para facilitar os testes.

> 💡 Dica: caso utilize Postman ou Insomnia, basta importar os exemplos convertendo cada cURL.

# 🚀 Testando a API com cURL

Como existem dependências entre as entidades, recomenda-se executar as requisições na seguinte ordem para evitar erros de relacionamento.

## 📋 Ordem recomendada

| Ordem | Operação | Dependência |
|------:|-----------|-------------|
| 1️⃣ | Criar Conta | - |
| 2️⃣ | Criar Chave Pix | Conta |
| 3️⃣ | Criar Favorecido | Conta |
| 4️⃣ | Criar Transferência Pix | Conta + Favorecido |
| 5️⃣ | Criar Comprovante | Transferência |
| 6️⃣ | Executar os GETs | - |
| 7️⃣ | Executar os PUTs | Entidades já criadas |
| 8️⃣ | Executar os DELETEs | Ordem inversa |

> [!IMPORTANT]
> Considere que a aplicação está executando em:
>
> **http://localhost:8080**

> [!WARNING]
> Os exemplos abaixo **não possuem autenticação**.
>
> Adicione o header abaixo em todas as requisições quando necessário:
>
> ```http
> Authorization: Bearer <SEU_TOKEN>
> ```

> [!NOTE]
> Caso utilize IDs diferentes dos exemplos, ajuste os relacionamentos (`id`) nos corpos das requisições.

---

# 🏦 Conta

## ➕ Criar

```bash
curl --location 'http://localhost:8080/contas' \
--header 'Content-Type: application/json' \
--data '{
  "nomeTitular": "Maria Silva",
  "cpf": "12345678900",
  "banco": "Banco MiniPix",
  "agencia": "0001",
  "numeroConta": "12345-6",
  "senha": "123456",
  "saldo": 5000.00,
  "limiteDiario": 2000.00,
  "dataCadastro": "2026-07-01"
}'
```

## 📄 Listar

```bash
curl http://localhost:8080/contas
```

## 🔍 Buscar

```bash
curl http://localhost:8080/contas/1
```

## ✏️ Atualizar

```bash
curl --location --request PUT 'http://localhost:8080/contas/1' \
--header 'Content-Type: application/json' \
--data '{
  "nomeTitular": "Maria Silva Oliveira",
  "cpf": "12345678900",
  "banco": "Banco MiniPix",
  "agencia": "0001",
  "numeroConta": "12345-6",
  "senha": "654321",
  "saldo": 7000.00,
  "limiteDiario": 3000.00,
  "dataCadastro": "2026-07-01"
}'
```

---

# 🔑 Chave Pix

## ➕ Criar

```bash
curl --location 'http://localhost:8080/chaves-pix' \
--header 'Content-Type: application/json' \
--data '{
  "tipo":"EMAIL",
  "valorChave":"maria@email.com",
  "conta":{
    "id":1
  },
  "ativa":true,
  "dataCadastro":"2026-07-01"
}'
```

## 📄 Listar

```bash
curl http://localhost:8080/chaves-pix
```

## 🔍 Buscar

```bash
curl http://localhost:8080/chaves-pix/1
```

## ✏️ Atualizar

```bash
curl --location --request PUT 'http://localhost:8080/chaves-pix/1' \
--header 'Content-Type: application/json' \
--data '{
  "tipo":"EMAIL",
  "valorChave":"novo@email.com",
  "conta":{
    "id":1
  },
  "ativa":true,
  "dataCadastro":"2026-07-01"
}'
```

---

# 👤 Favorecido

## ➕ Criar

```bash
curl --location 'http://localhost:8080/favorecidos' \
--header 'Content-Type: application/json' \
--data '{
  "nome":"João Pedro",
  "banco":"Banco XPTO",
  "chavePix":"joao@email.com",
  "tipoChave":"EMAIL",
  "contaOrigem":{
    "id":1
  },
  "dataCadastro":"2026-07-01"
}'
```

## 📄 Listar

```bash
curl http://localhost:8080/favorecidos
```

## 🔍 Buscar

```bash
curl http://localhost:8080/favorecidos/1
```

## ✏️ Atualizar

```bash
curl --location --request PUT 'http://localhost:8080/favorecidos/1' \
--header 'Content-Type: application/json' \
--data '{
  "nome":"João Pedro Souza",
  "banco":"Banco XPTO",
  "chavePix":"joao@email.com",
  "tipoChave":"EMAIL",
  "contaOrigem":{
    "id":1
  },
  "dataCadastro":"2026-07-01"
}'
```

---

# 💸 Transferência Pix

## ➕ Criar

```bash
curl --location 'http://localhost:8080/transferencias-pix' \
--header 'Content-Type: application/json' \
--data '{
  "contaOrigem":{
    "id":1
  },
  "favorecido":{
    "id":1
  },
  "valor":150.75,
  "descricao":"Pagamento almoço",
  "dataTransferencia":"2026-07-01",
  "dataAgendamento":"2026-07-01",
  "status":"CONCLUIDA"
}'
```

## 📄 Listar

```bash
curl http://localhost:8080/transferencias-pix
```

## 🔍 Buscar

```bash
curl http://localhost:8080/transferencias-pix/1
```

## ✏️ Atualizar

```bash
curl --location --request PUT 'http://localhost:8080/transferencias-pix/1' \
--header 'Content-Type: application/json' \
--data '{
  "contaOrigem":{
    "id":1
  },
  "favorecido":{
    "id":1
  },
  "valor":250.00,
  "descricao":"Pagamento atualizado",
  "dataTransferencia":"2026-07-01",
  "dataAgendamento":"2026-07-01",
  "status":"PROCESSANDO"
}'
```

---

# 🧾 Comprovante

## ➕ Criar

```bash
curl --location 'http://localhost:8080/comprovantes' \
--header 'Content-Type: application/json' \
--data '{
  "transferencia":{
    "id":1
  },
  "codigoAutenticacao":"ABC123XYZ987",
  "dataGeracao":"2026-07-01",
  "status":"GERADO"
}'
```

## 📄 Listar

```bash
curl http://localhost:8080/comprovantes
```

## 🔍 Buscar

```bash
curl http://localhost:8080/comprovantes/1
```

## ✏️ Atualizar

```bash
curl --location --request PUT 'http://localhost:8080/comprovantes/1' \
--header 'Content-Type: application/json' \
--data '{
  "transferencia":{
    "id":1
  },
  "codigoAutenticacao":"XYZ987ABC123",
  "dataGeracao":"2026-07-01",
  "status":"VALIDADO"
}'
```

---

# 🗑️ DELETE

Como existem relacionamentos entre as entidades, faça as exclusões na **ordem inversa** da criação.

## 🧾 Excluir Comprovante

```bash
curl --request DELETE http://localhost:8080/comprovantes/1
```

## 💸 Excluir Transferência

```bash
curl --request DELETE http://localhost:8080/transferencias-pix/1
```

## 👤 Excluir Favorecido

```bash
curl --request DELETE http://localhost:8080/favorecidos/1
```

## 🔑 Excluir Chave Pix

```bash
curl --request DELETE http://localhost:8080/chaves-pix/1
```

## 🏦 Excluir Conta

```bash
curl --request DELETE http://localhost:8080/contas/1
```

---

# 🗑️ Ordem correta para exclusão

Como existem relacionamentos entre as entidades, faça as exclusões nesta ordem:

```
🧾 Comprovante

↓

💸 Transferência

↓

👥 Favorecido

↓

🔑 Chave Pix

↓

👤 Conta
```

Assim você evita erros de integridade referencial.

---

# 🎥 Commits utilizados nas aulas

Caso esteja acompanhando as gravações da disciplina, estes são os commits utilizados durante o desenvolvimento.

| Aula | Commit |
|------|--------|
| Módulo 7 | `6ce9757d049b91d5ca11a51ea2540ef16aa170b8` |
| Módulo 6 - Parte 2 | `638686213e189c315340353a91fee5f9a8de7a1f` |
| Módulo 6 - Parte 1 | `cba5979ab20f3a2517f0cdeb58708cb35d85d469` |
| Módulo 5 | `84da3728ab723e60beb9d9f912b4e086c96667cf` |
| Módulo 4 | `cc252975792ed84e7e32a3e2574dec3ab4c76592` |
| Entre Módulos 3 e 4 | `16ecc3c3394e82b1b7a8b1d9b62c2d0b7a1fd994` |
| Módulo 3 - Aula 3 | `550ff946d26938d0241b927da7eeebec941e90a5` |
| Módulo 3 - Aula 2 | `24fcb6eda3819ea5ddc621d9fa6680dc55bd84f1` |

---

# 🎓 Produto final

Após esses commits, todo o restante do desenvolvimento corresponde ao **produto final da disciplina**, criado para validação e entrega do projeto.

Essas implementações adicionais **não possuem gravações em vídeo**, servindo como uma referência de evolução do sistema e boas práticas para estudo.

---

# ⭐ Bons estudos!

Se este projeto ajudou você a aprender Spring Boot, JPA e desenvolvimento de APIs REST, aproveite para explorá-lo, modificar funcionalidades e criar novas features.

A melhor forma de aprender é colocando a mão no código. 🚀☕