package br.com.lordsabino.cards_to_view.model.enums;

public enum TrapProperty {

    NORMAL("NORMAL"),
    CONTINUOUS("CONTINUOUS"),
    COUNTER("COUNTER");

    private final String value;

    TrapProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
