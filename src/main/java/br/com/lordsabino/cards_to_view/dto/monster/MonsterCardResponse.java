package br.com.lordsabino.cards_to_view.dto.monster;

import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.CardType;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;

import java.time.LocalDateTime;
import java.util.Set;

public record MonsterCardResponse(
        Long id,
        String cardName,
        String passcode,
        CardStatus cardStatus,
        String description,
        String imageUrl,
        CardType cardType,
        MonsterAttribute attribute,
        MonsterRace race,
        Set<MonsterType> types,
        Integer level,
        Integer attack,
        Integer defense,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}