package Itmo_projects.pokemon;

import ru.ifmo.se.pokemon.*;

public class Thunder_Wave extends StatusMove {
    public Thunder_Wave() {
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "Использует Thunder Wave";
    }
}
