package command;

public class HelpCommand implements Command{
    public void execute() {
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} : добавить новый элемент в коллекцию");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("insert_at index {element} : добавить новый элемент в заданную позицию");
        System.out.println("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный");
        System.out.println("sort : отсортировать коллекцию в естественном порядке");
        System.out.println("remove_any_by_unit_of_measure unitOfMeasure : удалить из коллекции один элемент, значение поля unitOfMeasure которого эквивалентно заданному");
        System.out.println("min_by_manufacturer : вывести любой объект из коллекции, значение поля manufacturer которого является минимальным");
        System.out.println("print_field_ascending_manufacturer : вывести значения поля manufacturer всех элементов в порядке возрастания");
    }
}
