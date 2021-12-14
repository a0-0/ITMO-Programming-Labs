package characters;

import java.util.Objects;

public class FactoryWorkers extends Villagers {
    private final String placeOfWork = "скуперфильдовской макаронной фабрики.";

    public void arriveAtTheAstronauts() {
        System.out.println("Вскоре к космонавтам прибыли несколько рабочих со " + placeOfWork);
    }

    public String sayPlan() {
        return "прогнать с фабрики Скуперфильда, а макароны будут делать сами без всяких хозяев";
    }

    public void tellWhatTheyDecided() {
        System.out.println("Они сказали, что решили " + sayPlan() + ".");
    }

    public void thinkAboutTheyDecided() {
        System.out.println("Чтоб осуществить этот план, им нужно устроить на фабрике невесомость, " +
                "так как в противном случае полицейские могут помешать им и даже вовсе прогонят их с фабрики.");
    }

    public boolean arrangeWeightlessness() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactoryWorkers that = (FactoryWorkers) o;
        return Objects.equals(placeOfWork, that.placeOfWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeOfWork);
    }

    @Override
    public String toString() {
        return "FactoryWorkers{" +
                "placeOfWork='" + placeOfWork + '\'' +
                '}';
    }
}
