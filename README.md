[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MONGO_BADGE]:https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white
[DOCKER_BADGE]:https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[SWAGGER_BADGE]:https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white
<p align="center">
<img src="src/main/resources/static/darth-vader.svg" alt="darth vader helmet image">
</p>

<h1 align="center" style="font-weight: bold;">STAR WARS REST API<?xml version="1.0" ?></h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![mongo][MONGO_BADGE]
![docker][DOCKER_BADGE]
![swagger][SWAGGER_BADGE]

<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Collaborators</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
  <b>Created to register Star Wars planets by name, climate and terrain. And to consume an external API being the <a href="https://swapi.dev/"> StarWars public API </a> to obtain the number of times the registered planet appeared in the franchise's films. Java 8, Spring Boot, Mongodb and Docker were used for development.</b>
</p>


<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

- [Docker](https://docs.docker.com/install/)
- [Git](https://git-scm.com/downloads)

<h3>Cloning</h3>

- After installing docker and git, clone the project with the following command:
    ```
    git clone https://github.com/tarikwesley/starwars-api.git
    ```

<h3>Starting</h3>

- Access the cloned project directory.
- Run the command ```docker-compose up -d``` to have docker start the project services in the background.
- When you obtain the following status below, you will be able to test the application.

    ```bash
    Creating starwars-mongodb ... done
    Creating starwars-rest-api ... done
    ```
If the Mongo process ```starwars-mongodb``` gives an error. Try disabling the mongo service on the local machine and try the previous command again.
Example: to disable the Mongo service on the local Linux machine:
Just run the command:
```sudo systemctl stop mongod```

Once everything is ok, continue the process.

- After this process, the API can be tested at the link ```http://localhost:8081/api/planets``` to display all planets with data from the internal API or at the link ```http:// localhost:8081/starwars-api/planets``` to display data from the external API containing the names and films that each planet is present in.
- To test API requests, you can use [Postman](https://www.getpostman.com/downloads/).
- To close the application use the command ``` docker-compose down```.

<h2 id="routes">📍 API Endpoints</h2>

<h3> 📍 Doc Swagger</h3>
  
``` http://localhost:8081/swagger-ui.html```

<h3>📍 Internal API</h2>

| Route                                          | Description                                                                                          |
|------------------------------------------------|------------------------------------------------------------------------------------------------------|
| <kbd>POST /api/planets</kbd>                   | Create a new planet. [Request details](#post-planet-detail)                                          |
| <kbd>GET /api/planets</kbd>                    | Returns all planets info. See [response details](#get-planets-detail)                                |
| <kbd>GET /api/planets/{id}</kbd>               | Returns the planet belonging to the given id info. See [response details](#get-planet-id-detail)     |              |
| <kbd>GET /api/planets/name?search={name}</kbd> | Returns the planet belonging to the given name info. See [response details](#get-planet-name-detail) |


- <h3 id="post-planet-detail">POST /api/planets</h3>

**REQUEST**
```json
    {
        "name": "Mustafar",
        "climate": "warm",
        "terrain": "volcanoes, lava rivers, mountains, caves"
    }
``` 

**RESPONSE**
```json
{
    "data": {
        "id": "663aa97246ab9c7530885e72",
        "name": "Mustafar",
        "climate": "warm",
        "terrain": "volcanoes, lava rivers, mountains, caves",
        "filmsAppearances": 1
    },
    "errors": null
}
```

- <h3 id="get-planets-detail">GET /api/planets</h3>

**RESPONSE**
```json
[
    {
        "id": "663aa9be46ab9c7530885e73",
        "name": "Alderaan",
        "climate": "temperate",
        "terrain": "grasslands, mountains",
        "filmsAppearances": 2
    }
    ,
    {
        "id": "663aa9d246ab9c7530885e74",
        "name": "Coruscant",
        "climate": "temperate",
        "terrain": "cityscape, mountains",
        "filmsAppearances": 4
    }
]
```

- <h3 id="get-planet-id-detail">GET /api/planets/663aa9be46ab9c7530885e73</h3>

**RESPONSE**
```json
{
    "id": "663aa9be46ab9c7530885e73",
    "name": "Alderaan",
    "climate": "temperate",
    "terrain": "grasslands, mountains",
    "filmsAppearances": 2
}
```

- <h3 id="get-planet-name-detail">GET /api/planets/name?search=Coruscant</h3>

**RESPONSE**
```json
{
    "id": "663aa9d246ab9c7530885e74",
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "FilmsAppearances": 4
}
```

<h2>📍 External API</h2>

| Route                                                   | Description                                                                                                                                                                      |
|---------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <kbd>GET /starwars-api/planets</kbd>                    | Returns all planets with the name data and the movies the planet is shown in from the StarWars External API. See [response details](#get-planets-detail-starwars-api)            |
| <kbd>GET /starwars-api/planets/{id}</kbd>               | Returns the name and films in which the planet is shown from the StarWars External API belonging to the given id. See [response details](#get-planet-id-detail-starwars-api)     |
| <kbd>GET /starwars-api/planets/name?search={name}</kbd> | Returns the name and films in which the planet is shown from the StarWars External API belonging to the given name. See [response details](#get-planet-name-detail-starwars-api) |


- <h3 id="get-planets-detail-starwars-api">GET /starwars-api/planets</h3>

**RESPONSE**
```json
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
    "errors": null
}
```

- <h3 id="get-planet-id-detail-starwars-api">GET /starwars-api/planets/13</h3>

**RESPONSE**
```json
{
    "data": {
        "name": "Mustafar",
        "films": [
            "http://swapi.dev/api/films/6/"
        ]
    },
    "errors": null
}
```

- <h3 id="get-planet-name-detail-starwars-api">GET /starwars-api/planets/name?search=Bespin</h3>

**RESPONSE**
```json
    {
        "name": "Bespin",
        "films": [
            "http://swapi.dev/api/films/2/"
        ]
    }
```

<h2 id="colab">🤝 Collaborators</h2>

Special thank you for all people that contributed for this project.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/47906316?v=4" width="100px;" alt="Tarik Wesley Profile Picture"/><br>
        <sub>
          <b>Tarik Wesley</b>
        </sub>
      </a>
    </td>

  </tr>
</table>

<h2 id="contribute">📫 Contribute</h2>

1. `git clone https://github.com/tarikwesley/starwars-api.git`
2. `git checkout -b feature/NAME`
3. Follow commit patterns
4. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://github.com/iuricode/padroes-de-commits)