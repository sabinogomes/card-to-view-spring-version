# Cards to View

API em Spring Boot para cadastro e consulta de cartas de um jogo de cartas colecionável no estilo Yu-Gi-Oh!, com suporte a cartas de Monstro, Magia e Armadilha.

## Status do projeto

Projeto em desenvolvimento inicial. Até o momento existem:

- Modelagem de domínio (cartas base e cartas de monstro)
- Repository e service para cartas de monstro
- Enums de domínio (status, tipo, atributo, raça, propriedades de magia e armadilha)

Ainda não existem:

- Anotações JPA nas entidades (`@Entity`, `@Id`, etc.)
- Configuração de banco de dados no `application.yaml`
- Scripts de migration do Flyway
- Camada web (controllers REST)
- Cartas de Magia e Armadilha (`SpellCard`, `TrapCard`)

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Data JPA
- Flyway
- H2 (banco em memória, para desenvolvimento)
- PostgreSQL (banco de produção)
- Maven

## Estrutura do projeto

```
src/main/java/br/com/lordsabino/cards_to_view/
├── CardsToViewApplication.java   # classe principal
├── model/
│   ├── base/
│   │   └── BaseEntity.java       # id, createdAt, updatedAt
│   ├── card/
│   │   ├── Card.java             # carta genérica (abstrata)
│   │   └── MonsterCard.java      # carta de monstro
│   └── enums/
│       ├── CardStatus.java       # unlimited, limited, semi-limited, forbidden
│       ├── CardType.java         # monster, spell, trap
│       ├── MonsterAttribute.java # dark, light, earth, water, fire, wind, divine
│       ├── MonsterRace.java      # raças de monstro (dragon, warrior, etc.)
│       ├── MonsterType.java      # normal, effect, fusion, synchro, etc.
│       ├── SpellProperty.java    # normal, quick-play, continuous, equip, field, ritual
│       └── TrapProperty.java     # normal, continuous, counter
├── repository/
│   └── MonsterCardRepository.java
└── service/
    └── MonsterCardService.java
```

## Modelo de domínio

`Card` é a classe base abstrata de qualquer carta, com nome, passcode, status, descrição e URL de imagem. Cada tipo específico de carta (por enquanto, só `MonsterCard`) estende `Card` e implementa o método `getCardType()`.

`MonsterCard` adiciona os campos específicos de monstro: atributo, raça, tipos, nível, ataque e defesa.

## Pré-requisitos

- Java 25
- Maven (ou usar o wrapper `mvnw` incluído no projeto)

## Como rodar

Com o wrapper do Maven, sem precisar instalar o Maven na máquina:

```bash
# Linux ou macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Observação: no estado atual do projeto, a aplicação pode não subir corretamente, pois falta configurar o datasource no `application.yaml` e adicionar as anotações JPA nas entidades (veja a seção "Status do projeto").

## Rodando os testes

```bash
./mvnw test
```

## Configuração

O arquivo de configuração fica em `src/main/resources/application.yaml`. Hoje ele só define o nome da aplicação:

```yaml
spring:
  application:
    name: cards-to-view
```

Para rodar localmente, será necessário adicionar a configuração de conexão com o banco (H2 ou PostgreSQL).

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Autor

Desenvolvido por lordsabino.
