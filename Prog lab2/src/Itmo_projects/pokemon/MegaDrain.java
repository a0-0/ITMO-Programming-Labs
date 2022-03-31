package Itmo_projects.pokemon;

import ru.ifmo.se.pokemon.*;


public class MegaDrain extends SpecialMove {
    public MegaDrain() {
        super(Type.GRASS, 40, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        long new_hp = Math.round((p.getStat(Stat.HP) - p.getHP()) * 0.5);
        Effect e = new Effect().turns(1).stat(Stat.HP, -1 * (int) new_hp);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "uses Mega Drain";
    }
}