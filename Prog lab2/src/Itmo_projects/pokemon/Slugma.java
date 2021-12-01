package Itmo_projects.pokemon;

import Itmo_projects.move.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Slugma extends Pokemon {
    public Slugma(String name, int level) {
        super(name, level);
        setStats(40, 40, 40, 70, 40, 20);
        setType(Type.FIRE);
        addMove(new RockSlide());
        addMove(new DoubleTeam());
        addMove(new Screech());
        addMove(new MegaDrain());

    }
}
