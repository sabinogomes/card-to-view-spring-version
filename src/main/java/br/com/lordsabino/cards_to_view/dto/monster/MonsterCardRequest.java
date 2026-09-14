package br.com.lordsabino.cards_to_view.dto.monster;

import br.com.lordsabino.cards_to_view.model.enums.CardStatus;
import br.com.lordsabino.cards_to_view.model.enums.MonsterAttribute;
import br.com.lordsabino.cards_to_view.model.enums.MonsterRace;
import br.com.lordsabino.cards_to_view.model.enums.MonsterType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record MonsterCardRequest(

        @NotBlank(message = "O nome da carta é obrigatório")
        @Size(max = 255, message = "O nome da carta deve ter no máximo 255 caracteres")
        String cardName,

        @NotBlank(message = "O passcode é obrigatório")
        @Size(max = 20, message = "O passcode deve ter no máximo 20 caracteres")
        String passcode,

        @NotNull(message = "O status da carta é obrigatório")
        CardStatus cardStatus,

        @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
        String description,

        @Size(max = 500, message = "A URL da imagem deve ter no máximo 500 caracteres")
        String imageUrl,

        @NotNull(message = "O atributo do monstro é obrigatório")
        MonsterAttribute attribute,

        @NotNull(message = "A raça do monstro é obrigatória")
        MonsterRace race,

        @NotEmpty(message = "Informe ao menos um tipo de monstro")
        Set<MonsterType> types,

        @NotNull(message = "O nível é obrigatório")
        @Min(value = 0, message = "O nível não pode ser negativo")
        @Max(value = 12, message = "O nível não pode ser maior que 12")
        Integer level,

        @NotNull(message = "O ataque é obrigatório")
        @Min(value = 0, message = "O ataque não pode ser negativo")
        Integer attack,

        @NotNull(message = "A defesa é obrigatória")
        @Min(value = 0, message = "A defesa não pode ser negativa")
        Integer defense
) {
}