package br.com.lordsabino.cards_to_view.model.enums;

public enum MonsterRace {

    SPELLCASTER("SPELLCASTER"),
    WARRIOR("WARRIOR"),
    DRAGON("DRAGON"),
    FIEND("FIEND"),
    FAIRY("FAIRY"),
    BEAST("BEAST"),
    BEAST_WARRIOR("BEAST_WARRIOR"),
    WINGED_BEAST("WINGED_BEAST"),
    ZOMBIE("ZOMBIE"),
    MACHINE("MACHINE"),
    ROCK("ROCK"),
    PYRO("PYRO"),
    AQUA("AQUA"),
    FISH("FISH"),
    SEA_SERPENT("SEA_SERPENT"),
    REPTILE("REPTILE"),
    DINOSAUR("DINOSAUR"),
    INSECT("INSECT"),
    PLANT("PLANT"),
    THUNDER("THUNDER"),
    DIVINE_BEAST("DIVINE_BEAST"),
    WYRM("WYRM"),
    CYBERSE("CYBERSE"),
    PSYCHIC("PSYCHIC"),
    ILLUSION("ILLUSION");

    private final String value;

    MonsterRace(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
