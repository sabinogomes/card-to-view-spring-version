package br.com.lordsabino.cards_to_view.model.enums;

public enum CardStatus {

    UNLIMITED("UNLIMITED"),
    LIMITED("LIMITED"),
    SEMI_LIMITED("SEMI_LIMITED"),
    FORBIDDEN("FORBIDDEN");

    private final String value;

    CardStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
