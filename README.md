# Payment API

### Requisitos

O caso de uso consiste em desenvolver uma funcionalidade que recebe um objeto contendo o código do vendedor e uma lista de pagamentos realizados. Cada pagamento é identificado pelo código da cobrança a que ele se refere. O sistema deve validar se o vendedor e o código da cobrança existem na base de dados. Além disso, ele deve verificar se o pagamento é parcial, total ou excedente em comparação com o valor original cobrado. Para cada situação de pagamento, o sistema deve enviar o objeto para uma fila SQS (Simple Queue Service) distinta e retornar o mesmo objeto recebido com a informação do status de pagamento preenchida.

### Requisitos Funcionais
[ x ] Receber um objeto contendo o código do vendedor e uma lista de pagamentos realizados.

[ x ] Validar se o vendedor e o código da cobrança existem na base de dados.

[ x ] Verificar se o pagamento é parcial, total ou excedente em comparação com o valor original cobrado.

[ x ] Enviar o objeto para uma fila SQS (Simple Queue Service) distinta.

[ x ] Retornar o mesmo objeto recebido com a informação do status de pagamento preenchida.

### Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Cloud AWS

### Execução
#### Docker Compose
```shell
$ docker-compose up
```

#### Gradle
```shell
$ ./gradlew bootRun
```

#### Testes
```shell
$ ./gradlew test
```