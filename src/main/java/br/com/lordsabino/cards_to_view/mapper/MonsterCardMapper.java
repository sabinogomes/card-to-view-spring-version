package br.com.lordsabino.cards_to_view.mapper;

import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardRequest;
import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardResponse;
import br.com.lordsabino.cards_to_view.model.card.MonsterCard;
import org.springframework.stereotype.Component;

@Component
public class MonsterCardMapper {

    public MonsterCard toEntity(MonsterCardRequest request) {
        MonsterCard monsterCard = new MonsterCard();
        applyRequest(monsterCard, request);
        return monsterCard;
    }

    public void updateEntityFromRequest(MonsterCard monsterCard, MonsterCardRequest request) {
        applyRequest(monsterCard, request);
    }

    private void applyRequest(MonsterCard monsterCard, MonsterCardRequest request) {
        monsterCard.setCardName(request.cardName());
        monsterCard.setPasscode(request.passcode());
        monsterCard.setCardStatus(request.cardStatus());
        monsterCard.setDescription(request.description());
        monsterCard.setImageUrl(request.imageUrl());
        monsterCard.setAttribute(request.attribute());
        monsterCard.setRace(request.race());
        monsterCard.setTypes(request.types());
        monsterCard.setLevel(request.level());
        monsterCard.setAttack(request.attack());
        monsterCard.setDefense(request.defense());
    }

    public MonsterCardResponse toResponse(MonsterCard monsterCard) {
        return new MonsterCardResponse(
                monsterCard.getId(),
                monsterCard.getCardName(),
                monsterCard.getPasscode(),
                monsterCard.getCardStatus(),
                monsterCard.getDescription(),
                monsterCard.getImageUrl(),
                monsterCard.getCardType(),
                monsterCard.getAttribute(),
                monsterCard.getRace(),
                monsterCard.getTypes(),
                monsterCard.getLevel(),
                monsterCard.getAttack(),
                monsterCard.getDefense(),
                monsterCard.getCreatedAt(),
                monsterCard.getUpdatedAt()
        );
    }
}