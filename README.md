💳 Payment Service API

API REST desenvolvida com Java 17 + Spring Boot 3 para gerenciamento de produtos e processamento de pagamentos com regras de negócio específicas por método de pagamento.

🚀 Tecnologias Utilizadas

Java 17

Spring Boot 3

Maven

MySQL

Spring Data JPA

Hibernate

Jakarta Validation

Apache Kafka (em fase de integração)

JUnit 5 (para testes unitários)

Mockito (para testes e mocks de dependências)

Postman

📌 Funcionalidades
🛍️ CRUD de Produtos

Criar produto

Listar produtos (com paginação)

Buscar produto por UUID

Atualizar produto

Remover produto

💳 Processamento de Pagamentos

A API aplica regras de negócio conforme o método de pagamento:

PIX

Aplica 5% de desconto sobre o valor total.

CREDIT_CARD

Concede 3% de cashback.

Valor final permanece o original.

DEBIT_CARD

Sem regras especiais.

🧠 Regras de Negócio

O valor total é calculado com base no preço do produto × quantidade.

UUID é utilizado como identificador público dos pagamentos.

Validação para impedir processamento quando não houver produtos cadastrados.

Cálculos realizados utilizando BigDecimal para evitar problemas de precisão.

Estrutura preparada para futura integração com mensageria (Kafka).

🧪 Testes (em implementação)

O projeto contará com:

Testes unitários utilizando JUnit 5

Mocks de dependências com Mockito

Cobertura de regras de negócio no PaymentsService

📫 Postman Collection

A collection com todos os endpoints está disponível na pasta postman/

👩🏻‍💻 Autora

Amanda Carolina
Desenvolvedora Backend Java

----