## Sobre
O projeto consiste em uma API REST que permite a um client gerenciar contatos, salvando os dados do contato em um banco de dados portável. Cada contato pode ter nome, email, telefone e tags descritivas.
<br><br>

## Tecnologias
Lista das principais tecnologias utilizadas no projeto:
- **Linguagens**: Kotlin v1.9.25 e Java v21
- **Framework**: Spring Boot v3.5.6
- **Banco de dados**: H2 v2.3.232
- **Build tool**: Maven (latest)
<br><br>

## Modelo de dados
O sistema contem duas entidades principais: contatos e tags.
<br><br>

## Funcionalidades
É possível realizar operações CRUD das entidades, bem como criar relacionamentos entre determinado contato e tag (muitos para muitos).
<br><br>

## Respostas da API
As respostas da API foram padronizadas conforme o exemplo abaixo:

```json
{
  "status":"success",
  "data":[],
  "messages":[],
  "timestamp":"16-09-2025 14:50"
}
```
