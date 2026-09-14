package br.com.lordsabino.cards_to_view.integration;

import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardRequest;
import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardResponse;
import br.com.lordsabino.cards_to_view.exception.ApiErrorResponse;
import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;
import br.com.lordsabino.cards_to_view.repository.MonsterCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MonsterCardIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MonsterCardRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    private MonsterCardRequest darkMagicianRequest() {
        return new MonsterCardRequest(
                "Dark Magician",
                "46986414",
                CardStatus.UNLIMITED,
                "The ultimate wizard in terms of attack and defense.",
                null,
                MonsterAttribute.DARK,
                MonsterRace.SPELLCASTER,
                Set.of(MonsterType.NORMAL),
                7,
                2500,
                2100
        );
    }

    @Test
    void deveCriarBuscarEListarUmaCartaDeMonstro() {
        ResponseEntity<MonsterCardResponse> createResponse = restTemplate.postForEntity(
                url("/monsters"), darkMagicianRequest(), MonsterCardResponse.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        MonsterCardResponse created = createResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.id()).isNotNull();
        assertThat(created.cardName()).isEqualTo("Dark Magician");
        assertThat(created.cardType().name()).isEqualTo("MONSTER");
        assertThat(created.createdAt()).isNotNull();

        ResponseEntity<MonsterCardResponse> getResponse = restTemplate.getForEntity(
                url("/monsters/" + created.id()), MonsterCardResponse.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().attack()).isEqualTo(2500);

        ResponseEntity<MonsterCardResponse[]> listResponse = restTemplate.getForEntity(
                url("/monsters"), MonsterCardResponse[].class);

        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody()).hasSize(1);
    }

    @Test
    void deveAtualizarUmaCartaExistente() {
        MonsterCardResponse created = restTemplate.postForEntity(
                url("/monsters"), darkMagicianRequest(), MonsterCardResponse.class).getBody();
        assertThat(created).isNotNull();

        MonsterCardRequest updatedRequest = new MonsterCardRequest(
                "Dark Magician Girl",
                "38033121",
                CardStatus.UNLIMITED,
                "A magical girl who protects the Dark Magician.",
                null,
                MonsterAttribute.DARK,
                MonsterRace.SPELLCASTER,
                Set.of(MonsterType.NORMAL),
                6,
                2000,
                1700
        );

        restTemplate.put(url("/monsters/" + created.id()), updatedRequest);

        ResponseEntity<MonsterCardResponse> getResponse = restTemplate.getForEntity(
                url("/monsters/" + created.id()), MonsterCardResponse.class);

        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().cardName()).isEqualTo("Dark Magician Girl");
        assertThat(getResponse.getBody().level()).isEqualTo(6);
    }

    @Test
    void deveDeletarUmaCartaExistente() {
        MonsterCardResponse created = restTemplate.postForEntity(
                url("/monsters"), darkMagicianRequest(), MonsterCardResponse.class).getBody();
        assertThat(created).isNotNull();

        restTemplate.delete(url("/monsters/" + created.id()));

        ResponseEntity<ApiErrorResponse> getResponse = restTemplate.getForEntity(
                url("/monsters/" + created.id()), ApiErrorResponse.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deveDevolver404AoBuscarCartaInexistente() {
        ResponseEntity<ApiErrorResponse> response = restTemplate.getForEntity(
                url("/monsters/999999"), ApiErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(404);
    }

    @Test
    void deveDevolver400AoCriarCartaComDadosInvalidos() {
        MonsterCardRequest invalidRequest = new MonsterCardRequest(
                "",
                "46986414",
                CardStatus.UNLIMITED,
                null,
                null,
                MonsterAttribute.DARK,
                MonsterRace.SPELLCASTER,
                Set.of(MonsterType.NORMAL),
                7,
                2500,
                2100
        );

        ResponseEntity<ApiErrorResponse> response = restTemplate.postForEntity(
                url("/monsters"), invalidRequest, ApiErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().fieldErrors()).containsKey("cardName");
    }
}