# Anyflix REST API

![Programação-Anyflix REST API](https://user-images.githubusercontent.com/8989346/224768169-287d5f36-a934-4b4b-bf70-b27676b67945.png)

REST API em Spring Boot para consumir filmes

## 🔨 Funcionalidades do projeto

A API oferece as seguintes funcionalidades:

- Listagem, registro, alteração e remoção de filmes.
- Busca de filme por id.
- Listagem, registro e remoção da minha lista (filmes favoritados)

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

## ✔️ Técnicas e tecnologias utilizadas

A aplicação foi desenvolvida com o Spring Boot utilizando Kotlin e foram utilizadas as seguintes técnicas:

- `Controllers`: mapear os endpoints 
- `Services`: realizar as ações esperadas pelo controller
- `Repositories`: oferecer e realizar os comportamentos de persistência de banco de dados
- `Aspect`: apresentar logs de execução dos services e controllers indicando as classes e métodos chamados
- `DTO`: padrão para indicar quais informações devem ser enviada/recebidas via requisição
- `JPA` com `Hibernate`: solução para se comunicar com o banco de dados
- `H2 database`: banco de dados que pode ser persistido em um arquivo local sem a necessidade de um serviço de SGBD

Bibliotecas do Spring Framework que foram utilizadas:

- `devtools`: ferramenta para agilizar o processo de desenvolvimento sem reiniciar a aplicação para atualizar
- `starter-web`: suporte para aplicação web em geral
- `starter-data-jpa`: suporte para abstrair a implementação de repositórios e reutilizar comportamentos de CRUD com base na configuração da JPA

## 🛠️ Abrir e rodar o projeto

O projeto pode ser acesso de duas maneiras diferente:

- Via código fonte
- Rodando o arquivo JAR executável

### Código fonte

Para código fonte, você precisa de uma IDE compatível com o Gradle e com o Spring Boot utilizando Kotlin. Para esse projeto foi utilizado o IntelliJ IDEA 2022.3.3, você pode usar essa versão da IDE para garantir a compatibilidade ou uma versão mais recente ou outra ferramenta de sua preferência. 

Caso opte pelo IntelliJ, na tela de launcher, acesse a opção **Open** e procure o projeto, selecione-o e clique em **OK**. (Caso baixar o arquivo zip, lembre-se de descompactá-lo antes de procurar)

Após o IntelliJ finalizar todas as tasks, acesse o arquivo `src\main\kotlin\br\com\alura\anyflix\AnyflixApplication.kt` e rode a função `main()`.

### JAR executável

Com o executável, você precisa ter acesso a um cliente do Java 17 ou superior. Acesse o arquivo JAR via terminal, e então, execute o seguinte comando: `java -jar nomeDoArquivo.jar` (provavelmente o arquivo foi nomeado como `anyflix-rest-api.jar`). 

Após executar em alguma das possibilidades, deve apresentar a seguinte mensagem via console:

```
o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
b.c.a.a.AnyflixApplicationKt          : Started AnyflixApplicationKt in 6.279 seconds (JVM running for 6.949)
```

A partir desse momento, é só acessar a baseUrl (por padrão "http://localhost:8080") via navegador que apresentará a página inicial da aplicação!

> **Observações**: evite rodar o JAR em algum local que exija mais privilégios para executar ou criar arquivos.

## 📁 Acesso ao projeto

Você pode acessar o projeto de diferentes maneiras:
- [baixar o zip do código fonte](https://github.com/alura-cursos/anyflix-rest-api/archive/refs/heads/dev.zip)
- [acessar o código fonte](https://github.com/alura-cursos/anyflix-rest-api/tree/dev)
- [baixar o arquivo jar executável](https://github.com/alura-cursos/anyflix-rest-api/releases/download/v1.0.0/anyflix-rest-api.jar)

## 🧪 Testando a API com o Postman

Você pode testar a aplicação utilizando o Postman também, você pode [baixar o zip com a collection com todos os exemplos](https://github.com/alura-cursos/anyflix-rest-api/archive/refs/heads/postman.zip). 

> Para fazer o teste, você precisa acessar o Postman, de preferência o cliente desktop, e fazer o [processo de importação](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman).

## 📝 Modificando propriedades da aplicação

Por padrão o Spring Boot vai rodar a aplicação na porta `8080` e a quantidade de filmes gerados é `20`. Porém, é possível modificar os valores dessas propriedades:

> As informações dos filmes (id, título, imagem, ano e trama) são geradas aleatoriamente com exceção da indicação para minha lista que é sempre `false`. Também, todas são fictícias e sem nenhuma ligação.

Caso queria modificar ambos valores, edite os valores das propriedades:


Na amostra acima, a aplicação vai operar na porta `8081` e serão registrados até 30 filmes `30`.

### Código fonte

No arquivo `application-dev.yml`, modifique o valor da `port: ${port:8080}` e `amount: ${amount:30}` para um valor esperado:

```yaml
server:
  port: ${port:8081}
movies:
  amount: ${amount:30}
```

> Vamos considerar a modificação para a porta nas amostras `8081` e quantidade de filmes `30`.

### Task `bootRun` do Gradle

Também é possível executar o projeto via task `bootRun` do Gradle. Além de rodar o projeto, é possível modificar a porta de execução:

```
./gradlew bootRun --args='--server.port=8081 --movies.amount=30'
```

> Neste exemplo foi utilizado o Wrapper para Linux, porém, você pode utilizar a versão para Windows também (`gradlew.bat`)

### JAR executável

Há também a possibilidade de modificar a porta via command line pelo arquivo jar:

```
java -jar nomeDoArquivo.jar --server.port=8081 --movies.amount=30
```

