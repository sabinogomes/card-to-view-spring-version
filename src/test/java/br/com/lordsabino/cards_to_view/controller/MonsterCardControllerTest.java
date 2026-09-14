package br.com.lordsabino.cards_to_view.controller;

import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardResponse;
import br.com.lordsabino.cards_to_view.exception.ResourceNotFoundException;
import br.com.lordsabino.cards_to_view.mapper.MonsterCardMapper;
import br.com.lordsabino.cards_to_view.model.card.MonsterCard;
import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;
import br.com.lordsabino.cards_to_view.service.MonsterCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonsterCardController.class)
class MonsterCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MonsterCardService service;

    @MockitoBean
    private MonsterCardMapper mapper;

    private MonsterCard darkMagician() {
        MonsterCard monsterCard = new MonsterCard();
        monsterCard.setId(1L);
        monsterCard.setCardName("Dark Magician");
        monsterCard.setPasscode("46986414");
        monsterCard.setCardStatus(CardStatus.UNLIMITED);
        monsterCard.setAttribute(MonsterAttribute.DARK);
        monsterCard.setRace(MonsterRace.SPELLCASTER);
        monsterCard.setTypes(Set.of(MonsterType.NORMAL));
        monsterCard.setLevel(7);
        monsterCard.setAttack(2500);
        monsterCard.setDefense(2100);
        return monsterCard;
    }

    private String validRequestJson() {
        return """
                {
                  "cardName": "Dark Magician",
                  "passcode": "46986414",
                  "cardStatus": "UNLIMITED",
                  "attribute": "DARK",
                  "race": "SPELLCASTER",
                  "types": ["NORMAL"],
                  "level": 7,
                  "attack": 2500,
                  "defense": 2100
                }
                """;
    }

    @Test
    void findAll_deveDevolverListaDeCartas() throws Exception {
        MonsterCard monsterCard = darkMagician();
        when(service.findAllMonsters()).thenReturn(List.of(monsterCard));
        when(mapper.toResponse(monsterCard)).thenReturn(toResponse(monsterCard));

        mockMvc.perform(get("/monsters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cardName").value("Dark Magician"))
                .andExpect(jsonPath("$[0].cardType").value("MONSTER"));
    }

    @Test
    void findById_quandoExiste_deveDevolver200ComACarta() throws Exception {
        MonsterCard monsterCard = darkMagician();
        when(service.getMonsterOrThrow(1L)).thenReturn(monsterCard);
        when(mapper.toResponse(monsterCard)).thenReturn(toResponse(monsterCard));

        mockMvc.perform(get("/monsters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardName").value("Dark Magician"))
                .andExpect(jsonPath("$.attack").value(2500));
    }

    @Test
    void findById_quandoNaoExiste_deveDevolver404() throws Exception {
        when(service.getMonsterOrThrow(99L))
                .thenThrow(new ResourceNotFoundException("Monster card não encontrado com id 99"));

        mockMvc.perform(get("/monsters/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Monster card não encontrado com id 99"));
    }

    @Test
    void create_comDadosValidos_deveDevolver201() throws Exception {
        MonsterCard monsterCard = darkMagician();
        when(mapper.toEntity(any())).thenReturn(monsterCard);
        when(service.saveMonster(monsterCard)).thenReturn(monsterCard);
        when(mapper.toResponse(monsterCard)).thenReturn(toResponse(monsterCard));

        mockMvc.perform(post("/monsters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardName").value("Dark Magician"));
    }

    @Test
    void create_semCardName_deveDevolver400ComErroDeValidacao() throws Exception {
        String invalidJson = """
                {
                  "passcode": "46986414",
                  "cardStatus": "UNLIMITED",
                  "attribute": "DARK",
                  "race": "SPELLCASTER",
                  "types": ["NORMAL"],
                  "level": 7,
                  "attack": 2500,
                  "defense": 2100
                }
                """;

        mockMvc.perform(post("/monsters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.cardName").exists());
    }

    @Test
    void create_comNivelAcimaDoLimite_deveDevolver400() throws Exception {
        String invalidJson = """
                {
                  "cardName": "Dark Magician",
                  "passcode": "46986414",
                  "cardStatus": "UNLIMITED",
                  "attribute": "DARK",
                  "race": "SPELLCASTER",
                  "types": ["NORMAL"],
                  "level": 99,
                  "attack": 2500,
                  "defense": 2100
                }
                """;

        mockMvc.perform(post("/monsters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.level").exists());
    }

    @Test
    void delete_quandoExiste_deveDevolver204() throws Exception {
        mockMvc.perform(delete("/monsters/1"))
                .andExpect(status().isNoContent());

        verify(service).deleteMonster(eq(1L));
    }

    @Test
    void delete_quandoNaoExiste_deveDevolver404() throws Exception {
        org.mockito.Mockito.doThrow(new ResourceNotFoundException("Monster card não encontrado com id 99"))
                .when(service).deleteMonster(99L);

        mockMvc.perform(delete("/monsters/99"))
                .andExpect(status().isNotFound());
    }

    private MonsterCardResponse toResponse(MonsterCard monsterCard) {
        return new MonsterCardResponse(
                monsterCard.getId(),
                monsterCard.getCardName(),
                monsterCard.getPasscode(),
                monsterCard.getCardStatus(),
                monsterCard.getDescription(),
                monsterCard.getImageUrl(),
                monsterCard.getCardType(),
                monsterCard.getAttribute(),
                monsterCard.getRace(),
                monsterCard.getTypes(),
                monsterCard.getLevel(),
                monsterCard.getAttack(),
                monsterCard.getDefense(),
                monsterCard.getCreatedAt(),
                monsterCard.getUpdatedAt()
        );
    }
}