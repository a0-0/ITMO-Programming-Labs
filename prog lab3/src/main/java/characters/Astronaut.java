package characters;

import resources.Antilunite;
import resources.Seeds;
import resources.WeightlessnessDevices;

import java.util.Objects;

public class Astronaut implements AstronautHelp {
    private final String name = "космонавтам";
    private final String instruction = "Пользуйтесь так!";
    private final Seeds seeds = new Seeds();
    private final WeightlessnessDevices weightlessnessDevices = new WeightlessnessDevices();
    private final Antilunite antilunite = new Antilunite();

    public String getName() {
        return name;
    }

    @Override
    public WeightlessnessDevices giveWeightlessnessDevicesToVillagers() {
        return weightlessnessDevices;
    }

    @Override
    public Seeds giveSeedsToVillagers() {
        return seeds;
    }

    @Override
    public Antilunite giveAntiluniteToVillagers() {
        return antilunite;
    }

    public String explainHowToUse() {
        return instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astronaut astronaut = (Astronaut) o;
        return Objects.equals(name, astronaut.name) && Objects.equals(seeds, astronaut.seeds) && Objects.equals(weightlessnessDevices, astronaut.weightlessnessDevices) && Objects.equals(antilunite, astronaut.antilunite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, seeds, weightlessnessDevices, antilunite);
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "name='" + name + '\'' +
                ", seeds=" + seeds +
                ", weightlessnessDevices=" + weightlessnessDevices +
                ", antilunite=" + antilunite +
                '}';
    }
}
