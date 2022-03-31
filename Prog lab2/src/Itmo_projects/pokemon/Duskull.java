package Itmo_projects.pokemon;

import ru.ifmo.se.pokemon.Type;

public class Duskull extends Dusclops {
    public Duskull(String name, int level) {
        super(name, level);
        setType(Type.GHOST);
        addMove(new Swagger());
        addMove(new DoubleTeam());
    }
}
