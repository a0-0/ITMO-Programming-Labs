package Itmo_projects.pokemon;

import Itmo_projects.move.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Magcargo extends Pokemon {

    public Magcargo(String name, int level) {
        super(name, level);
        setStats(60, 50, 120, 90, 80, 30);
        setType(Type.FIRE , Type.ROCK);
        addMove(new Thunder_Wave());
        addMove(new DoubleTeam());
        addMove(new Calm_Mind());
}
}
