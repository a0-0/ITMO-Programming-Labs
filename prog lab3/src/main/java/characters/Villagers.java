package characters;

import buildings.Village;
import resources.Antilunite;
import resources.Seeds;
import resources.WeightlessnessDevices;

import java.util.Objects;

public class Villagers implements GetHelpFromAstronaut {
    private final String nameOfVillagers = "Деревенские жители";
    private final String name = "лунтакам";
    private Seeds seeds;
    String instruction;
    WeightlessnessDevices weightlessnessDevices;
    Antilunite antilunite;

    public void goToAstronauts(String name) {
        System.out.print(nameOfVillagers + " могли беспрепятственно приходить к " + name + " ");
    }

    public void setWeightlessnessDevices(WeightlessnessDevices weightlessnessDevices) {
        this.weightlessnessDevices = weightlessnessDevices;
    }

    public void setAntilunite(Antilunite antilunite) {
        this.antilunite = antilunite;
    }

    public void setSeeds(Seeds seeds) {
        this.seeds = seeds;
    }

    @Override
    public void getWeightlessnessDevicesFromAstronaut(WeightlessnessDevices weightlessnessDevices) {
        setWeightlessnessDevices(weightlessnessDevices);
    }

    @Override
    public void getAntiluniteFromAstronaut(Antilunite antilunite) {
        setAntilunite(antilunite);
    }

    @Override
    public void getSeedsFromAstronaut(Seeds seeds) {
        setSeeds(seeds);
        System.out.println("и получать у них " + seeds.getName() + " гигантских растений.");
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void getExplainFromAstronaut(String instruction) {
        setInstruction(instruction);
    }

    public void tellAboutPlantingSites() {
        System.out.println("Теперь гигантские семена сажали не только в деревне Нееловке, но и в селе "
                + Village.GOLOPYATKINA.name() + ", "
                + Village.BESKHLEBNOV.name() + ", "
                + Village.GOLODAYEVKA.name() + ", "
                + Village.IMPASSABLE.name() + "и во многих других.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villagers villagers = (Villagers) o;
        return Objects.equals(nameOfVillagers, villagers.nameOfVillagers) && Objects.equals(name, villagers.name) && Objects.equals(seeds, villagers.seeds) && Objects.equals(instruction, villagers.instruction) && Objects.equals(weightlessnessDevices, villagers.weightlessnessDevices) && Objects.equals(antilunite, villagers.antilunite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfVillagers, name, seeds, instruction, weightlessnessDevices, antilunite);
    }

    @Override
    public String toString() {
        return "Villagers{" +
                "nameOfVillagers='" + nameOfVillagers + '\'' +
                ", name='" + name + '\'' +
                ", seeds=" + seeds +
                ", instruction='" + instruction + '\'' +
                ", weightlessnessDevices=" + weightlessnessDevices +
                ", antilunite=" + antilunite +
                '}';
    }
}
