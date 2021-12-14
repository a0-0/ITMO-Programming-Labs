package characters;

import java.util.Objects;

public class Znayka {
    private String name = "Знайка";
    private String order = "чтоб лунатикам давали не только нужные им семена, но снабжали их приборами невесомости," +
            " а также антилунитом и объясняли им, как всем этим пользоваться, чтоб защититься от полицейских.";

    public void giveOrder() {
        System.out.println(name + " распорядился, " + order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Znayka znayka = (Znayka) o;
        return Objects.equals(name, znayka.name) && Objects.equals(order, znayka.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, order);
    }

    @Override
    public String toString() {
        return "Znayka{" +
                "name='" + name + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
