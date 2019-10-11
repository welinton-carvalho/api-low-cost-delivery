## Tecnologias e Frameoworks empregados

- Java 8
- Base de dados H2
- Spring Data
- Spring Cache
- Spring Web
- JUnit

## Instruções para rodar a aplicação

- Classe de arranque: br.com.low.cost.delivery.api.ApplicationStart;
- Execute o comando `mvn spring-boot:run` na pasta raiz do projeto;
- Utilize a coleção de requisições do Postman para interagir com a API Restful localizada na pasta `/postman/` do repositório;
- Como passo inicial utilizar o endpoint `localhost:8080/load/initial/data/load` para dar carga na base de dados H2 e Spring Cache com os dados iniciais propostos no desafio;
- Utlizar o endpoint `localhost:8080/delivery/calculateRoute/` para realizar o cálculo da rota.
- Sera criado a base do H2 no path `~/low_cost_delivery_h2_database.mv.db`

## Instruções para rodar os testes

- Execute o comando `mvn test` na pasta raiz do projeto.
