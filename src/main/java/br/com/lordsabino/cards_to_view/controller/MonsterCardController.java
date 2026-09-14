package br.com.lordsabino.cards_to_view.controller;

import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardRequest;
import br.com.lordsabino.cards_to_view.dto.monster.MonsterCardResponse;
import br.com.lordsabino.cards_to_view.mapper.MonsterCardMapper;
import br.com.lordsabino.cards_to_view.model.card.MonsterCard;
import br.com.lordsabino.cards_to_view.service.MonsterCardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monsters")
public class MonsterCardController {

    private final MonsterCardService service;
    private final MonsterCardMapper mapper;

    public MonsterCardController(MonsterCardService service, MonsterCardMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MonsterCardResponse> findAll() {
        return service.findAllMonsters()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public MonsterCardResponse findById(@PathVariable Long id) {
        MonsterCard monsterCard = service.getMonsterOrThrow(id);
        return mapper.toResponse(monsterCard);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MonsterCardResponse create(@Valid @RequestBody MonsterCardRequest request) {
        MonsterCard monsterCard = mapper.toEntity(request);
        MonsterCard saved = service.saveMonster(monsterCard);
        return mapper.toResponse(saved);
    }

    @PutMapping("/{id}")
    public MonsterCardResponse update(@PathVariable Long id, @Valid @RequestBody MonsterCardRequest request) {
        MonsterCard existing = service.getMonsterOrThrow(id);
        mapper.updateEntityFromRequest(existing, request);
        MonsterCard updated = service.saveMonster(existing);
        return mapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteMonster(id);
    }
}