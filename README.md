
# DesafioElotech

Repositório para expor código do DesafioElotech




# Documentação da API

## - Endpoints Pessoas

* ### Retorna todas as pessoas

```http
  GET /pessoas
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| Nenhum |  | Retorna todas as pessoas registradas |

* ### Retorna uma pessoa

```http
  GET /pessoas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | O ID da pessoa que você quer |


* ### Busca com paginação

```http
  GET /pessoas/busca?page={page}&size={size}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| PessoaParam      | `JSON` | Passado no requestBody, json com atributos para filtrar |
| page | `Long` | Qual pagina você quer receber |
| size | `Long` | Quantos registros por pagina |

#### Uso/Exemplos
```json
{    
    "nome": "Joe Doe",
    "cpf": "778.039.880-32",
    "dataNascimento": "01/11/1997"
}
```

* ### Adicionar Pessoa

```http
  POST /pessoas
```

| Parâmetro | Tipo  | Descrição                |
| :----- | :---- | :-------------------------- |
| Pessoa | `JSON` | Passado no requestBody, json com todos os campos obrigatorios |

#### Uso/Exemplos
```json
{
    "nome": "Rodrigo Azagh",
    "cpf": "778.039.880-32",
    "dataNascimento": "01/11/1997",
    "contatos":[
        {
            "nome": "TESTE",
            "email": "jose@cleber.com",
            "telefone": "123123123"
        }
    ]
}
```

* ### Atualiza pessoa

```http
  PUT /pessoas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | O ID da pessoa que você quer atualizar |
| PessoaParam | `JSON` | Passado no requestBody, json com validação nos campos |

#### Uso/Exemplos
```json
{    
    "nome": "Joe Doe",
    "cpf": "778.039.880-32",
    "dataNascimento": "01/11/1997"
}
```

* ### Deleta uma pessoa

```http
  DELETE /pessoas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | O ID da pessoa que você quer deletar|