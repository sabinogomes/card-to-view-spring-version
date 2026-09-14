package br.com.lordsabino.cards_to_view.service;

import br.com.lordsabino.cards_to_view.exception.ResourceNotFoundException;
import br.com.lordsabino.cards_to_view.model.card.MonsterCard;
import br.com.lordsabino.cards_to_view.repository.MonsterCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<MonsterCard> findMonsterById(Long id) {
        return repository.findById(id);
    }

    public MonsterCard getMonsterOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monster card não encontrado com id " + id));
    }

    public void deleteMonster(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Monster card não encontrado com id " + id);
        }
        repository.deleteById(id);
    }
}