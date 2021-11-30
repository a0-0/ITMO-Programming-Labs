package Itmo_projects.move;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        int currentStat = (int) p.getStat(Stat.EVASION);
        if (currentStat < 6) {
            p.setMod(Stat.EVASION, ++currentStat);
        }
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {

        return true;
    }

    @Override
    protected String describe() {

        return "использует Double Team";
    }
}
