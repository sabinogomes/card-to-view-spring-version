package br.com.lordsabino.cards_to_view.model.enums;

public enum MonsterAttribute {

    DARK("DARK"),
    LIGHT("LIGHT"),
    EARTH("EARTH"),
    WATER("WATER"),
    FIRE("FIRE"),
    WIND("WIND"),
    DIVINE("DIVINE");

    private final String value;

    MonsterAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
