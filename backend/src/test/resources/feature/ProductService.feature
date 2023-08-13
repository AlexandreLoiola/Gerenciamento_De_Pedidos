Feature: Funcionalidade do serviço de produtos

Scenario: Obter todos os produtos
  Given que o repositório de produtos possui os seguintes produtos:
    | name  | description     | unitPrice | stockQuantity | isActive |
    | Prod1 | Description 1   | 10.0      | 100           | true     |
    | Prod2 | Description 2   | 15.0      | 50            | true     |
  When o cliente solicita todos os produtos
  Then a resposta deve conter os seguintes produtos:
    | name  | description     | unitPrice | stockQuantity | isActive |
    | Prod1 | Description 1   | 10.0      | 100           | true     |
    | Prod2 | Description 2   | 15.0      | 50            | true     |

Scenario: Obter produto por nome
  Given que o repositório de produto possua o seguinte produto:
    | name  | description     | unitPrice | stockQuantity | isActive |
    | Prod1 | Description 1   | 10.0      | 100           | true     |
  When o cliente solicita o produto com o nome: "Prod1"
  Then a resposta deve conter o produto com o nome: "Prod1"

Scenario: Inserir novo produto
  When o cliente insere um novo produto com os seguintes detalhes:
    | name    | description      | unitPrice | stockQuantity | isActive |
    | NewProd | New Description  | 20.0      | 200           | true     |
  Then a resposta deve conter um novo produto com o nome: "NovoProd"

Scenario: : Atualizar produto
  Given que o repositório de produtos possui o seguinte produto:
    | name  | description     | unitPrice | stockQuantity | isActive |
    | Prod1 | Description 1   | 10.0      | 100           | true     |
  When o cliente atualiza o produto com o nome: "Prod1" com os seguintes detalhes:
    | name    | description      | unitPrice | stockQuantity | isActive |
    | Prod1   | Updated Desc     | 15.0      | 150           | false    |
  Then a resposta deve conter o produto com o nome: "Prod1"

Scenario: Deletar produto
  Given que o repositório de produtos possui o seguinte produto:
    | name  | description     | unitPrice | stockQuantity | isActive |
    | Prod1 | Description 1   | 10.0      | 100           | true     |
  When o cliente deleta o produto com o nome: "Prod1"
  Then o produto com o nome: "Prod1" deve ser deletado do repositório
