# API REST STAR WARS 
Criada para cadastrar planetas do Star Wars passando nome, clima e terreno. E para consumir uma API externa sendo a [API publica do StarWars](https://swapi.dev/) para obter a quantidade de vezes que o planeta cadastrado apareceu nos filmes da franquia. Para o desenvolvimento foi utilizado Java 8, Spring Boot, Mongodb e Docker.

## Processos para execução
- Baixe e instale o [Docker](https://docs.docker.com/install/) e o [Git](https://git-scm.com/downloads).
- Após a instalação do docker e do git, clone o projeto com o seguinte comando:
    ```
    git clone https://github.com/tarikwesley/ApiRest-StarWars.git
    ```
- Acesse o diretório do projeto clonado.
- Execute o comando ```docker-compose up -d``` para que o docker inicie os serviços do projeto em segundo plano.
- Quando obtiver o seguinte status abaixo será possivel realizar os testes da aplicação.
    ```
    Creating apirest-starwars-mongodb ... done
    Creating apirest-starwars         ... done
    ```
Caso o processo do Mongo ```apirest-starwars-mongodb``` der algum erro. Tente desativar o serviço do mongo na maquina local e tente novamente o comando anterior.
Exemplo: para desativar o serviço do Mongo na maquina local Linux:
Basta executar o comnando
```sudo systemctl stop mongod```
Assim que estiver tudo ok continue o prcesso.

- Após este processo, a API poderá ser testada no link ```http://localhost:8081/api/planetas``` para exibir todos os planetas com os dados da API interna ou no link ```http://localhost:8081/starwars-api/planetas``` para exibir os dados da API externa contendo os nomes e os filmes que cada planeta ta presente.
- Para testes de requisições na API, pode-se utilizar o [Postman](https://www.getpostman.com/downloads/).
- Para encerrar a aplicação utilize o comando ```docker-compose down```.

## Links para requisições na API
- POST: ```http://localhost:8081/api/planetas``` (adiciona um novo planeta)
  - Exemplo:
```
{
    "nome": "Mustafar",
    "clima": "quente",
    "terreno": "vulcões, rios de lava, montanhas, cavernas"
}

```
- Esse é o retorno para o planeta adicionado
```
{
    "data": {
        "id": "1",
        "nome": "Mustafar",
        "clima": "quente",
        "terreno": "vulcões, rios de lava, montanhas, cavernas",
        "aparicoesFilmes": 1
    },
    "erros": null
}
```

- GET: ```http://localhost:8081/api/planetas``` (Retorna todos os planetas)
``` 
[
    {
        "id": "1",
        "nome": "Alderaan",
        "clima": "temperate",
        "terreno": "grasslands, mountains",
        "aparicoesEmFilmes": 2
    }
    ,
    {
        "id": "2",
        "nome": "Coruscant",
        "clima": "temperate",
        "terreno": "cityscape, mountains",
        "aparicoesEmFilmes": 4
    }
]
```

- GET: ```http://localhost:8081/api/planetas/{id}``` (Retorna o planeta pertencente ao id informado)
    - Exemplo: ```http://localhost:8081/api/planetas/1```
``` 
{
    "id": "1",
    "nome": "Alderaan",
    "clima": "temperate",
    "terreno": "grasslands, mountains",
    "aparicoesEmFilmes": 2
}
```
- GET: ```http://localhost:8081/api/planetas/?nome={nome}``` (Retorna o planeta pertencente ao nome informado)
    - Exemplo:  ```http://localhost:8081/api/planetas/?nome=Coruscant```
``` 
{
    "id": "2",
    "nome": "Coruscant",
    "clima": "temperate",
    "terreno": "cityscape, mountains",
    "aparicoesEmFilmes": 4
}
```
- DELETE: ```http://localhost:8081/api/planetas/{id}``` (Deleta o planeta pertencente ao id informado)
  - Exemplo:  ```http://localhost:8081/api/planetas/13```
```
{
    "data": "Removido com sucesso!",
    "erros": null
}

```

## Links para requisições na API Externa do Star Wars
- GET: ```http://localhost:8081/starwars-api/planetas``` (Retorna todos os planetas com os dados de nome e os filmes em que o planeta é exibido da API Externa do StarWars)
   
``` 
{
    "data": {
        "results": [
            {
                "name": "Tatooine",
                "films": [
                    "http://swapi.dev/api/films/1/",
                    "http://swapi.dev/api/films/3/",
                    "http://swapi.dev/api/films/4/",
                    "http://swapi.dev/api/films/5/",
                    "http://swapi.dev/api/films/6/"
                ]
            },
            {
                "name": "Alderaan",
                "films": [
                    "http://swapi.dev/api/films/1/",
                    "http://swapi.dev/api/films/6/"
                ]
            },
            {
                "name": "Yavin IV",
                "films": [
                    "http://swapi.dev/api/films/1/"
                ]
            },
            {
                "name": "Hoth",
                "films": [
                    "http://swapi.dev/api/films/2/"
                ]
            },
            {
                "name": "Dagobah",
                "films": [
                    "http://swapi.dev/api/films/2/",
                    "http://swapi.dev/api/films/3/",
                    "http://swapi.dev/api/films/6/"
                ]
            },
            {
                "name": "Bespin",
                "films": [
                    "http://swapi.dev/api/films/2/"
                ]
            },
            {
                "name": "Endor",
                "films": [
                    "http://swapi.dev/api/films/3/"
                ]
            },
            {
                "name": "Naboo",
                "films": [
                    "http://swapi.dev/api/films/3/",
                    "http://swapi.dev/api/films/4/",
                    "http://swapi.dev/api/films/5/",
                    "http://swapi.dev/api/films/6/"
                ]
            },
            {
                "name": "Coruscant",
                "films": [
                    "http://swapi.dev/api/films/3/",
                    "http://swapi.dev/api/films/4/",
                    "http://swapi.dev/api/films/5/",
                    "http://swapi.dev/api/films/6/"
                ]
            },
            {
                "name": "Kamino",
                "films": [
                    "http://swapi.dev/api/films/5/"
                ]
            }
        ]
    },
    "erros": null
}
```
- GET: ```http://localhost:8081/starwars-api/planetas/{id}``` (Retorna o nome e os filmes em que o planeta é exibido da API Externa do StarWars pertencente ao id informado)
    - Exemplo:  ```http://localhost:8081/starwars-api/planeta/13```
``` 
{
    "data": {
        "name": "Mustafar",
        "films": [
            "http://swapi.dev/api/films/6/"
        ]
    },
    "erros": null
}
```
- GET: ```http://localhost:8081/starwars-api/planetas/?nome={nome}``` (Retorna o planeta pertencente ao nome informado)
  - Exemplo:  ```http://localhost:8081/starwars-api/planetas/?nome=Coruscant```
``` 
{
    "id": "2",
    "nome": "Coruscant",
    "clima": "temperate",
    "terreno": "cityscape, mountains",
    "aparicoesEmFilmes": 4
}
```