package br.com.lordsabino.cards_to_view.model.enums;

public enum SpellProperty {

    NORMAL("NORMAL"),
    QUICK_PLAY("QUICK_PLAY"),
    CONTINUOUS("CONTINUOUS"),
    EQUIP("EQUIP"),
    FIELD("FIELD"),
    RITUAL("RITUAL");

    private final String value;

    SpellProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
