package Itmo_projects.pokemon;

import Itmo_projects.move.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Dusknoir extends Pokemon {

    public Dusknoir(String name, int level) {
        super(name, level);
        setStats(45, 100, 135, 65, 135, 45);
        setType(Type.GHOST);
        addMove(new Swagger());
        addMove(new MegaDrain());
        addMove(new RockSlide());
        addMove(new Screech());
    }
}
