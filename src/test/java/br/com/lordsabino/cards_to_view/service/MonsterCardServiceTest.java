package br.com.lordsabino.cards_to_view.service;

import br.com.lordsabino.cards_to_view.exception.ResourceNotFoundException;
import br.com.lordsabino.cards_to_view.model.card.MonsterCard;
import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;
import br.com.lordsabino.cards_to_view.repository.MonsterCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MonsterCardServiceTest {

    @Mock
    private MonsterCardRepository repository;

    @InjectMocks
    private MonsterCardService service;

    private MonsterCard darkMagician;

    @BeforeEach
    void setUp() {
        darkMagician = new MonsterCard();
        darkMagician.setId(1L);
        darkMagician.setCardName("Dark Magician");
        darkMagician.setPasscode("46986414");
        darkMagician.setCardStatus(CardStatus.UNLIMITED);
        darkMagician.setAttribute(MonsterAttribute.DARK);
        darkMagician.setRace(MonsterRace.SPELLCASTER);
        darkMagician.setTypes(Set.of(MonsterType.NORMAL));
        darkMagician.setLevel(7);
        darkMagician.setAttack(2500);
        darkMagician.setDefense(2100);
    }

    @Test
    void saveMonster_devePersistirEDevolverACartaSalva() {
        when(repository.save(darkMagician)).thenReturn(darkMagician);

        MonsterCard saved = service.saveMonster(darkMagician);

        assertThat(saved).isEqualTo(darkMagician);
        verify(repository, times(1)).save(darkMagician);
    }

    @Test
    void findAllMonsters_deveDevolverTodasAsCartas() {
        when(repository.findAll()).thenReturn(List.of(darkMagician));

        List<MonsterCard> result = service.findAllMonsters();

        assertThat(result).hasSize(1).containsExactly(darkMagician);
    }

    @Test
    void findMonsterById_quandoExiste_deveDevolverOptionalComACarta() {
        when(repository.findById(1L)).thenReturn(Optional.of(darkMagician));

        Optional<MonsterCard> result = service.findMonsterById(1L);

        assertThat(result).isPresent().contains(darkMagician);
    }

    @Test
    void findMonsterById_quandoNaoExiste_deveDevolverOptionalVazio() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<MonsterCard> result = service.findMonsterById(99L);

        assertThat(result).isEmpty();
    }

    @Test
    void getMonsterOrThrow_quandoExiste_deveDevolverACarta() {
        when(repository.findById(1L)).thenReturn(Optional.of(darkMagician));

        MonsterCard result = service.getMonsterOrThrow(1L);

        assertThat(result).isEqualTo(darkMagician);
    }

    @Test
    void getMonsterOrThrow_quandoNaoExiste_deveLancarResourceNotFoundException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getMonsterOrThrow(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deleteMonster_quandoIdExiste_deveDeletar() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteMonster(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteMonster_quandoIdNaoExiste_deveLancarResourceNotFoundExceptionENaoDeletar() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> service.deleteMonster(99L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void deleteMonster_quandoIdEhNulo_deveLancarIllegalArgumentException() {
        assertThatThrownBy(() -> service.deleteMonster(null))
                .isInstanceOf(IllegalArgumentException.class);

        verify(repository, never()).existsById(any());
        verify(repository, never()).deleteById(anyLong());
    }
}