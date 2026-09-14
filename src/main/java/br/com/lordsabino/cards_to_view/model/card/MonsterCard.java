package br.com.lordsabino.cards_to_view.model.card;

import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.CardType;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "monster_card")
@PrimaryKeyJoinColumn(name = "card_id")
@DiscriminatorValue("MONSTER")
public class MonsterCard extends Card {

    @Enumerated(EnumType.STRING)
    @Column(name = "attribute", nullable = false, length = 20)
    private MonsterAttribute attribute;

    @Enumerated(EnumType.STRING)
    @Column(name = "race", nullable = false, length = 30)
    private MonsterRace race;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "monster_card_type", joinColumns = @JoinColumn(name = "monster_card_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private Set<MonsterType> types;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "attack", nullable = false)
    private Integer attack;

    @Column(name = "defense", nullable = false)
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