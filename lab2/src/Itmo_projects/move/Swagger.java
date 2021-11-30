package Itmo_projects.move;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Swagger extends StatusMove {
    public Swagger() {
        super(Type.NORMAL, 0, 85);
    }


    protected void applyOppEffects(Pokemon p) {
        p.confuse();
        p.setMod(Stat.ATTACK, 2);
    }

    protected java.lang.String describe() {

        return ("is using Swagger");
    }
}