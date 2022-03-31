package Itmo_projects.pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Dusclops extends Pokemon {
    public Dusclops(String name, int level) {
        super(name, level);
        setStats(40, 70, 130, 60, 130, 25);
        setType(Type.GHOST);
        addMove(new Swagger());
        addMove(new DoubleTeam());
        addMove(new Screech());
    }
}
