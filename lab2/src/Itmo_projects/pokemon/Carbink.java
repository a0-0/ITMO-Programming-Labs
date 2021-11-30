package Itmo_projects.pokemon;

import Itmo_projects.move.DoubleTeam;

import Itmo_projects.move.Calm_Mind;
import Itmo_projects.move.Facade;
import Itmo_projects.move.Tackle;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Carbink extends Pokemon {
    public Carbink(String name, int level) {
        super(name, level);
        setStats(50, 50, 150, 50, 150, 50);
       setType(Type.ROCK,Type.FAIRY);
        addMove(new DoubleTeam());
        addMove(new Calm_Mind());
        addMove(new Tackle());
        addMove(new Facade());

    }
}
