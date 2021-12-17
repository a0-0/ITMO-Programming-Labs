package characters;

public class Znayka {

    public void giveOrder() {
        class Order {
            private String name = "Знайка";
            private String order = "чтоб лунатикам давали не только нужные им семена, но снабжали их приборами невесомости," +
                    " а также антилунитом и объясняли им, как всем этим пользоваться, чтоб защититься от полицейских.";

            public String createOrder() {
                return name + " распорядился, " + order;
            }
        }
        System.out.println(new Order().createOrder());
    }

}
