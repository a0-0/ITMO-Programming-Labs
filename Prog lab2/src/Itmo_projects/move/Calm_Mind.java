package Itmo_projects.move;

import ru.ifmo.se.pokemon.*;

public class Calm_Mind extends StatusMove {
    public Calm_Mind() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        double i = p.getStat(Stat.SPECIAL_ATTACK);
        double j = p.getStat(Stat.SPECIAL_DEFENSE);
        if (i < 6)
            i++;
        if (j < 6)
            j++;
        p.setMod(Stat.SPECIAL_ATTACK, (int) i);
        p.setMod(Stat.SPECIAL_DEFENSE, (int) j);

    }

    @Override
    protected String describe() {
        return "Использует Calm Mind";
    }
}