/**
 * Создать справочник сотрудников
 * Необходимо:
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер.
 * Номер телефона.
 * Имя.
 * Стаж.
 * Добавить метод, который ищет сотрудника по стажу (может быть список).
 * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список).
 * Добавить метод, который ищет сотрудника по табельному номеру.
 * Добавить метод добавление нового сотрудника в справочник.
 */
public class Main {
    public static void main(String[] args) {
        Directory directory = new Directory();
        directory.addEmployeeToDirectory(new Employee(1, 89123456456L, "Alex", 5));
        directory.addEmployeeToDirectory(new Employee(2, 89014563289L, "Egor", 8));
        directory.addEmployeeToDirectory(new Employee(3, 89274545824L, "Svetlana", 7));
        directory.addEmployeeToDirectory(new Employee(4, 89326553871L, "Kristina", 5));
        directory.addEmployeeToDirectory(new Employee(5, 89459651234L, "Max", 3));
        directory.addEmployeeToDirectory(new Employee(6, 89839547887L, "Tom", 2));
        directory.addEmployeeToDirectory(new Employee(7, 89958001234L, "Egor", 10));
        directory.print();
        System.out.println();
        System.out.println(directory.findEmployeeByExperience(5));
        System.out.println();
        System.out.println(directory.findPhoneNumberByName("Egor"));
        System.out.println();
        System.out.println(directory.findEmployeeByPersonellNumber(3));

    }
}
