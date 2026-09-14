package br.com.lordsabino.cards_to_view.model.card;

import br.com.lordsabino.cards_to_view.model.base.BaseEntity;
import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.CardType;

import java.time.LocalDateTime;

public abstract class Card extends BaseEntity {

    private String cardName;

    private String passcode;

    private CardStatus cardStatus;

    private String description;

    private String imageUrl;

    protected Card() {
    }

    public Card(
            Long id,
            String cardName,
            String passcode,
            CardStatus cardStatus,
            String description,
            String imageUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.cardName = cardName;
        this.passcode = passcode;
        this.cardStatus = cardStatus;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public abstract CardType getCardType();

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
