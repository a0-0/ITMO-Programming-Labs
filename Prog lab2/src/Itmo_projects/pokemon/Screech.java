package Itmo_projects.pokemon;


import ru.ifmo.se.pokemon.*;

public class Screech extends StatusMove {
    public Screech() {
        super(Type.NORMAL, 0, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        double i = p.getStat(Stat.DEFENSE);
        if (i > -5)
            i -= 2;
        p.setMod(Stat.DEFENSE, (int) i);
    }

    @Override
    protected String describe() {
        return "Использовал Screetch!";
    }
}