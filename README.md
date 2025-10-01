### Sobre
O projeto consiste em uma API REST que permite a um client gerenciar um banco de dados de contatos. Cada contato pode ter nome, email, telefone e tags descritivas.

### Tecnologias
Lista das principais tecnologias utilizadas no projeto:
1. Linguagens: Kotlin v1.9.25 e Java v21
2. Framework: Spring Boot v3.5.6
3. Banco de dados: H2 v2.3.232 
4. Build tool: Maven (latest)

### Modelo de dados
O sistema contem duas entidades principais: contatos e tags.

### Funcionalidades
É possível realizar operações CRUD das entidades, bem como criar relacionamentos entre determinado contato e tag (muitos para muitos).

### Respostas da API
As respostas da API foram padronizadas conforme o exemplo abaixo:

```json
{
  "status":"success",
  "data":[],
  "messages":[],
  "timestamp":"16-09-2025 14:50"
}
```
