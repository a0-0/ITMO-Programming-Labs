import buildings.Factory;
import characters.*;
import exceptions.NoGunException;
import exceptions.NoResourcesException;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            Police police = new Police();
            if (police.getGun() == null) throw new NoGunException(" у полиции нет оружия");
            Villagers villagers = new Villagers();
            Astronaut astronaut = new Astronaut();
            Znayka znayka = new Znayka();
            FactoryWorkers factoryWorkers = new FactoryWorkers();
            Scooperfield scooperfield = new Scooperfield() {
                private String placeOfWork = "Директор макаронной фабрики";
                private String name = "Скуперфильд";

                @Override
                public void banishByWorkers() {
                    placeOfWork = "безработный";
                }

                @Override
                public String toString() {
                    return "Scooperfield{" +
                            "placeOfWork='" + placeOfWork + '\'' +
                            ", name='" + name + '\'' +
                            '}';
                }
            };
            Factory factory = new Factory();
            System.out.print("Нечего и говорить, что ");
            police.fearOfRocket();
            villagers.goToAstronauts(astronaut.getName());
            if (astronaut.getSeeds() == null) {
                throw new NoResourcesException("нет семян");
            }
            villagers.getSeedsFromAstronaut(astronaut.giveSeedsToVillagers());
            villagers.tellAboutPlantingSites();
            znayka.giveOrder();
            if (astronaut.getSeeds() == null || astronaut.getAntilunite() == null || astronaut.getWeightlessnessDevices() == null) {
                throw new NoResourcesException("нет ресурсов");
            }
            villagers.getWeightlessnessDevicesFromAstronaut(astronaut.giveWeightlessnessDevicesToVillagers());
            villagers.getAntiluniteFromAstronaut(astronaut.giveAntiluniteToVillagers());
            villagers.getExplainFromAstronaut(new Astronaut.Instruction().getInstruction());
            factoryWorkers.arriveAtTheAstronauts();
            factoryWorkers.tellWhatTheyDecided();
            factoryWorkers.thinkAboutTheyDecided();
        } catch (NoGunException e) {
            System.out.println("История не начнётся, т.к. у полиции нет пистолета, а значит рабочим он не страшны.");
        } catch (NoResourcesException e) {
            System.out.println("у космонавтов " + e.getMessage());
        }
    }
}
