package resources;

import java.util.Objects;

public class WeightlessnessDevices extends Resource {
    private String name = "прибор невесомости";

    public WeightlessnessDevices() {
        super("приборами невесомости");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightlessnessDevices that = (WeightlessnessDevices) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "WeightlessnessDevices{" +
                "name='" + name + '\'' +
                '}';
    }
}
