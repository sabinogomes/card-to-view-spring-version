package br.com.lordsabino.cards_to_view.service;

import br.com.lordsabino.cards_to_view.model.MonsterCard;
import br.com.lordsabino.cards_to_view.repository.MonsterCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterCardService {

    private final MonsterCardRepository repository;

    public MonsterCardService(MonsterCardRepository repository) {
        this.repository = repository;
    }

    public MonsterCard saveMonster(MonsterCard monsterCard) {
        return repository.save(monsterCard);
    }

    public List<MonsterCard> findAllMonsters() {
        return repository.findAll();
    }


}
