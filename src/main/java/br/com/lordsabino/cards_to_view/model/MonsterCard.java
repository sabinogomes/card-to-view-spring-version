package br.com.lordsabino.cards_to_view.model;

import java.util.Objects;

public class MonsterCard {

    private Long id;

    private String name;

    private String attribute;

    private String types;

    private Integer level;

    private Integer attack;

    private Integer defense;

    private String passcode;

    private String status;

    private String description;

    public MonsterCard() {
    }

    public MonsterCard(
            Long id,
            String name,
            String attribute,
            String types,
            Integer level,
            Integer attack,
            Integer defense,
            String passcode,
            String status,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.attribute = attribute;
        this.types = types;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.passcode = passcode;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
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

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MonsterCard that = (MonsterCard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
