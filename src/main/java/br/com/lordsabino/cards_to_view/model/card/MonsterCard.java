package br.com.lordsabino.cards_to_view.model.card;

import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.CardType;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;


import java.time.LocalDateTime;
import java.util.Set;

public class MonsterCard extends Card {

    private MonsterAttribute attribute;

    private MonsterRace race;

    private Set<MonsterType> types;

    private Integer level;

    private Integer attack;

    private Integer defense;

    public MonsterCard() {
    }

    public MonsterCard(
            Long id,
            String cardName,
            MonsterAttribute attribute,
            MonsterRace race,
            Set<MonsterType> types,
            Integer level,
            Integer attack,
            Integer defense,
            String passcode,
            CardStatus cardStatus,
            String description,
            String imageUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(
                id,
                cardName,
                passcode,
                cardStatus,
                description,
                imageUrl,
                createdAt,
                updatedAt
        );
        this.attribute = attribute;
        this.race = race;
        this.types = types;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public CardType getCardType() {
        return CardType.MONSTER;
    }

    public MonsterAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(MonsterAttribute attribute) {
        this.attribute = attribute;
    }

    public MonsterRace getRace() {
        return race;
    }

    public void setRace(MonsterRace race) {
        this.race = race;
    }

    public Set<MonsterType> getTypes() {
        return types;
    }

    public void setTypes(Set<MonsterType> types) {
        this.types = types;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
