package buildings;

import java.util.Objects;

public class Factory {
    private String nameOfFactory="макаронная фабрика";
    private boolean weightlessness=false;

    public boolean isWeightlessness() {
        return weightlessness;
    }

    public void setWeightlessness(boolean weightlessness) {
        this.weightlessness = weightlessness;
    }

    public String getNameOfFactory() {
        return nameOfFactory;
    }

    public void setNameOfFactory(String nameOfFactory) {
        this.nameOfFactory = nameOfFactory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factory factory = (Factory) o;
        return weightlessness == factory.weightlessness && Objects.equals(nameOfFactory, factory.nameOfFactory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfFactory, weightlessness);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "nameOfFactory='" + nameOfFactory + '\'' +
                ", weightlessness=" + weightlessness +
                '}';
    }
}
