package Itmo_projects.move;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    protected void applyOppDamage(Pokemon def, double damage) {
        Status defStatus = def.getCondition();
        if (defStatus.equals(Status.BURN) || defStatus.equals(Status.POISON) || defStatus.equals(Status.PARALYZE)) {
            super.applyOppDamage(def, damage * 2);
        } else {
            super.applyOppDamage(def, damage);
        }
    }

    protected String describe() {
        return "is using Facade";
    }
}