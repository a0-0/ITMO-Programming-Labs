import buildings.Factory;
import characters.*;

public class Main {
    public static void main(String[] args) {
        Police police = new Police();
        Villagers villagers = new Villagers();
        Astronaut astronaut = new Astronaut();
        Znayka znayka = new Znayka();
        FactoryWorkers factoryWorkers = new FactoryWorkers();
        Scooperfield scooperfield = new Scooperfield();
        Factory factory = new Factory();
        System.out.print("Нечего и говорить, что ");
        police.fearOfRocket();
        villagers.goToAstronauts(astronaut.getName());
        villagers.getSeedsFromAstronaut(astronaut.giveSeedsToVillagers());
        villagers.tellAboutPlantingSites();
        znayka.giveOrder();
        villagers.getWeightlessnessDevicesFromAstronaut(astronaut.giveWeightlessnessDevicesToVillagers());
        villagers.getAntiluniteFromAstronaut(astronaut.giveAntiluniteToVillagers());
        villagers.getExplainFromAstronaut(astronaut.explainHowToUse());
        factoryWorkers.arriveAtTheAstronauts();
        factoryWorkers.tellWhatTheyDecided();
        factoryWorkers.thinkAboutTheyDecided();
    }
}
