package br.com.lordsabino.cards_to_view.model.enums;

public enum MonsterType {

    NORMAL("NORMAL"),
    EFFECT("EFFECT"),
    FUSION("FUSION"),
    RITUAL("RITUAL"),
    SYNCHRO("SYNCHRO"),
    XYZ("XYZ"),
    PENDULUM("PENDULUM"),
    LINK("LINK"),
    TOKEN("TOKEN");

    private final String value;

    MonsterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
