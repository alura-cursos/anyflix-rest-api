# Anyflix REST API

![Programação-Anyflix REST API](https://user-images.githubusercontent.com/8989346/224768169-287d5f36-a934-4b4b-bf70-b27676b67945.png)

REST API em Spring Boot para consumir filmes

## 🔨 Funcionalidades do projeto

A API oferece as seguintes funcionalidades:

- Listagem, registro, alteração e remoção de filmes.
- Busca de filme por id.
- Listagem, registro e remoção da minha lista (filmes favoritados)

## Como baixar

Você pode [baixar o arquivo executável](https://github.com/alura-cursos/anyflix-rest-api/releases/download/v0.0.1/anyflix-rest-api.jar) ou [acessar o código fonte via GitHub](https://github.com/alura-cursos/anyflix-rest-api/tree/dev).

## Como executar

É possível rodar a API a partir do projeto ou arquivo jar.

### Projeto

Para rodar via projeto, baixe ou faça clone do repositório do GitHub. Dentro do diretório raiz, execute a task `bootRun` do Gradle. A primeira execução vai realizar o download de todas dependências necessárias.
9
> O uso do Wrapper, `gradlew` (Linux)  ou `gradlew.bat` (Windows), é recomendado para garantir a compatibilidade. A partir do projeto, você também pode gerar o arquivo jar se preferir, para isso rode a task `build`. Ao finalizar a task, o jar gerado será armazenado no diretório **build/libs/file-name.jar**.

### Arquivo jar

Para rodar o arquivo jar, vá até o arquivo a partir de um terminal (prompt no Windows) e rode o seguinte comando:

```
java -jar file-name.jar
```

> A build do projeto foi feita com Java 17, portanto, utilize a mesma versão ou uma superior. Para garantir o funcionamento, tente rodar com a 17.

## Modificando properties de inicialização

Por padrão o Spring Boot vai rodar a aplicação na porta `8080` e a quantidade de filmes gerados é `20`.

> As informações dos filmes (id, título, imagem, ano e trama) são geradas aleatoriamente com exceção da indicação para minha lista que é sempre `false`. Também, todas são fictícias e sem nenhuma ligação.

Caso queria modificar ambos valores, edite os valores das propriedades:

```yaml
server:
  port: ${port:8081}
movies:
  amount: ${amount:30}
```

Na amostra acima, a aplicação vai operar na porta `8081` e serão registrados até 30 filmes `30`.

## Modificando propriedades durante a execução

Também é possível modificar as propriedades via command line durante a execução.

### Via task `bootRun` do Gradle

Com o Gradle você pode alterar os valores das properties por meio do comando `-args`:

```
./gradlew bootRun --args='--server.port=8081 --movies.amount=30'
```

### Via arquivo jar gerado

Caso execute pelo arquivo jar,

```
java -jar file-name.jar --server.port=8081 --movies.amount=30
```

## 🗺️ Mapeamento de end-points

Para acessar as funcionalidades foram disponibilizados os seguintes end-points:

- `/movies`:
    - **GET**: lista todos os filmes salvos:

  ```json
  // response example
  [
      {
          "id": "06c1e492-64b9-4177-93b7-c17e7ac107e6",
          "title": "putent malesuada",
          "image": "https://picsum.photos/2528/1294",
          "year": 2022,
          "plot": "ipsum tempus id consectetuer felis ac definitiones quaerendum graece dolore deterruisset movet te dicat auctor vocent adipiscing vestibulum indoctum accusata voluptaria suscipit fugit mediocritatem",
          "inMyList": false
      },
      {
          "id": "e6ed59e8-c516-4d8b-b90d-2121588c2be6",
          "title": "sed ex",
          "image": "https://picsum.photos/2154/1144",
          "year": 1961,
          "plot": "orci pulvinar audire varius definiebas viderer a veniam etiam doctus inciderint mediocrem postulant nulla expetendis veritus sanctus lorem mollis senserit decore dis appareat viderer",
          "inMyList": false
      }
  ]
  ```

    - **POST**: insere um filme:
  
  ```json
  // request body example
  {
    "title" : "test title",
    "image" : "test image",
    "year" : "1994",
    "plot" : "test plot"
  }

  // response example
  {
    "id": "31bc2c5c-3c37-4ddf-9c20-18d3b4a8be63",
    "title": "test title",
    "image": "test image",
    "year": 1994,
    "plot": "test plot",
    "inMyList": false
  }
  ```

- `/movies/{id}`:
  - `id` deve ser um UUID v4 (Random) e precisa ser enviado no caminho do endereço, por exemplo: `/movies/31bc2c5c-3c37-4ddf-9c20-18d3b4a8be63`.

    - **GET**: busca um filme pelo id:
      - Retorna `404` caso o filme não existir

  ```json
  // response example
  {
    "id": "31bc2c5c-3c37-4ddf-9c20-18d3b4a8be63",
    "title" : "test title",
    "image" : "test image",
    "year" : "1994",
    "plot" : "test plot",
    "inMyList": false
  }
  ```
  
    - **PUT**: salva ou atualiza filme com id específico:
      - Retorna `404` caso o filme não existir

  ```json
  // request body example
  {
    "title" : "new test title",
    "image" : "new test image",
    "year" : "1995",
    "plot" : "new test plot"
  }

  // response example
  {
    "id": "31bc2c5c-3c37-4ddf-9c20-18d3b4a8be63",
    "title": "test title",
    "image": "test image",
    "year": 1994,
    "plot": "test plot",
    "inMyList": false
  }
  ```

    - **DELETE**: remove o filme pelo id
      - Retorna `404` caso o filme não existir

- `/movies/addToMyList/{id}`
  - exemplo: `/movies/addToMyList/31bc2c5c-3c37-4ddf-9c20-18d3b4a8be63`. 

    - **PUT**: adiciona um filme específico na minha lista:
      - Retorna `404` caso o filme não existir

- `/movies/removeFromMyList/{id}`
  - exemplo: `id` deve ser um UUID v4 (Random) e precisa ser enviado no caminho do endereço, por exemplo: `/movies/addToMyList/31bc2c5c-3c37-4ddf-9c20-18d3b4a8be63`. 

    - **PUT**: remove um filme específico na minha lista:
      - Retorna `404` caso o filme não existir

- `/movies/myList`

    - **GET**: devolve todos os filmes na minha lista:

  ```json
  // response example
  [
      {
          "id": "367b7c14-0201-4b03-9478-f7626fd5469b",
          "title": "te mucius",
          "image": "https://picsum.photos/2062/1287",
          "year": 1905,
          "plot": "ridens alterum utroque dicant ignota turpis potenti ex vestibulum quisque instructior aliquam nam sapien liber graeco pericula semper similique repudiandae sodales neglegentur praesent vitae gubergren dico",
          "inMyList": true
      },
      {
          "id": "f0fd1630-cb89-47f1-993d-01b764a2e59f",
          "title": "test title 2",
          "image": "test image 3",
          "year": 1995,
          "plot": "test plot 4",
          "inMyList": true
      }
  ]
  ```
