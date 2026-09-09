package br.com.lordsabino.cards_to_view.repository;

import br.com.lordsabino.cards_to_view.model.MonsterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterCardRepository extends JpaRepository<MonsterCard, Long> {
}
