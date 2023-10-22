# Agrix Fase A 

API de controle de fazendas e plantações, utilizando Spring Boot, Spring Boot Web, Spring Boot Starter Actuator, Spring Data JPA e MySQL Connector-J 

<br> 

## Premissa do Projeto 

Maria e João são pessoas empreendedoras que estão muito preocupadas com os impactos ambientais e sociais dos nossos processos agrícolas. Por isso, decidiram criar a AgroTech, uma empresa especializada em tecnologias para melhorar a eficiência no cultivo de plantações. Isso visa reduzir o desperdício de recursos em geral e de alimentos em específico, fazendo um uso mais responsável da terra disponível para plantio.

O primeiro produto dessa empresa será o Agrix, um sistema que permitirá a gestão e o monitoramento das fazendas participantes. Esse produto será desenvolvido em fases.

Neste projeto você deverá desenvolver uma aplicação Spring Boot com algumas funcionalidades iniciais, e que servirá de base para as próximas fases.

<br>

## Habilidades desenvolvidas

- Aplicar o conhecimento do ecossistema Spring para criar rotas da API.
- Aplicar a injeção de dependência para conectar as camadas de controle, serviço e persistência.
- Utilizar o Spring Data JPA para implementar entidades e repositórios para a persistência em banco
  de dados.
- Implementar gerenciamento de erros no Spring Web.
- Criar o Dockerfile multiestágio para configurar a aplicação para execução no Docker.

<br>

## Instalação

1. Clone o repositório
    - Use o comando: `git clone git@github.com:yurioneix/agrix-fase-a.git`
    - Entre na pasta do repositório que você acabou de clonar:
      - `cd agrix-fase-a`

2. Instale as dependências
    - `mvn install -DskipTests`

3. Suba os containers Docker
    - `docker-compose up -d`

<br> 

## Endpoints

- <strong> POST `/farms` </strong>

<details>
  <summary>Cadastra uma nova fazenda</summary>

  - Exemplo de requisição:
    ```json
      {
        "name": "Fazendinha",
        "size": 5
      }
    ```

  - Exemplo de resposta com `status 200`:

    ```json
      {
        "id": 1,
        "name": "Fazendinha",
        "size": 5
      }
    ```
</details>

<br>

- <strong> GET `/farms` </strong>

<details>
  <summary>Retorna todas as fazendas cadastradas</summary>

  - Exemplo de resposta com `status 200`:

    ```json
      [
        {
          "id": 1,
          "name": "Fazendinha",
          "size": 5.0
        },
        {
          "id": 2,
          "name": "Fazenda do Júlio",
          "size": 2.5
        }
      ]
    ```

  - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

</details>

<br>

- <strong> GET `/farms/{id}` </strong>

<details>
  <summary>Retorna uma fazenda pelo seu id</summary>

  - Exemplo de resposta com `status 200` para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

    ```json
      {
        "id": 3,
        "name": "My Cabbages!",
        "size": 3.49
      }
    ```
</details>

<br>

- <strong> POST `/farms/{farmId}/crops`</strong>

<details>
  <summary>Associa uma plantação à uma fazenda, através do id da fazenda</summary>

  - Exemplo de requisição na rota `/farms/1/crops` com `status 201` (supondo que exista uma fazenda com `id = 1`):

    ```json
      {
        "name": "Couve-flor",
        "plantedArea": 5.43
      }
    ```

  - Exemplo de resposta:

    ```json
      {
        "id": 1,
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "farmId": 1
      }
    ```

  - Caso não exista uma fazenda com o `id` passado, a rota deve retornar o `status HTTP 404` com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

</details>

<br> 

- <strong> GET `/farms/{farmId}/crops` </strong>

<details>
  <summary>Retorna uma plantação pelo id da fazenda em que ela está associada</summary>

  - Exemplo de resposta para a rota `/farms/1/crops` com `status 200` (supondo que exista uma fazenda com `id = 1`):

    ```json
      [
        {
          "id": 1,
          "name": "Couve-flor",
          "plantedArea": 5.43,
          "farmId": 1
        },
        {
          "id": 2,
          "name": "Alface",
          "plantedArea": 21.3,
          "farmId": 1
        }
      ]
    ```
    
  - Caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.
</details>

<br> 

- <strong> GET `/crops` </strong>

<details> 
  <summary>Retorna todas as plantações cadastradas</summary>

  - Exemplo de resposta da requisição, com `status HTTP 200`:
    ```json
      [
        {
          "id": 1,
          "name": "Couve-flor",
          "plantedArea": 5.43,
          "farmId": 1
        },
        {
          "id": 2,
          "name": "Alface",
          "plantedArea": 21.3,
          "farmId": 1
        },
        {
          "id": 3,
          "name": "Tomate",
          "plantedArea": 1.9,
          "farmId": 2
        }
      ]
    ```
</details>

<br>

- <strong> GET `/crops/{id}` </strong>

<details>
  <summary> Retorna uma plantação pelo seu id</summary>

  - Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:
  ```json
    {
      "id": 3,
      "name": "Tomate",
      "plantedArea": 1.9,
      "farmId": 2
    }
  ```

- Caso não exista uma plantação com o `id` passado, a rota deve retornar o `status HTTP 404` com a
      mensagem `Plantação não encontrada!` no corpo da resposta.
</details>

<br>

## Pastas/arquivos desenvolvidos por mim 

```bash
  src/main/java/com.betrybe.agrix.controller
  src/main/java/com.betrybe.agrix.model
  src/main/java/com.betrybe.agrix.service
  src/main/java/com.betrybe.agrix.util
```

