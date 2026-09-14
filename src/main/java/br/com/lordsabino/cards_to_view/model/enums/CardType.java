package br.com.lordsabino.cards_to_view.model.enums;

public enum CardType {

    MONSTER("MONSTER"),
    SPELL("SPELL"),
    TRAP("TRAP");

    private final String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
