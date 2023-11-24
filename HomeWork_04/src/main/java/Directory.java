import java.util.*;
import java.util.stream.Collectors;
public class Directory {
    private static Map<Integer ,Employee> directory;

    public Directory() {
        this.directory = new HashMap<>();
    }

    /**
     * Метод добавления сотрудника
     */
    public void addEmployeeToDirectory(Employee employee){
        if (employee.getName() != null && !employee.getName().isEmpty()
                && employee.getPersonellNumber() >= 0 && employee.getExperience() >= 0) {
            directory.put(employee.getPersonellNumber(), employee);
        }
    }

    /**
     * Метод поиска сотрудника с указанным опытом
     * @param experience требуемый опыт сотрудника
     * @return Список сотрудников с указанным опытом
     */
    public List<Employee> findEmployeeByExperience(int experience){
        if (experience < 0) throw new RuntimeException("Сотрудник не может быть с отрицательным опытом");
        return directory.values().stream()
                .filter(employee -> employee.getExperience() == experience).collect(Collectors.toList());
    }

    /**
     * Поиск номера телефона по имени
     * @param name Имя работника, чей номер телефона необходимо найти
     * @return Возвращает список номеров
     */
    public List<Long> findPhoneNumberByName(String name) {
        return directory.values().stream().filter(employee -> employee.getName()
                .toLowerCase().equals(name.toLowerCase())).map(Employee :: getPhoneNumber).toList();
    }

    /**
     * Метод поиска сотрудника по табельному номеру
     * @param number табельный номер
     * @return возвращает сотрудника
     */
    public Employee findEmployeeByPersonellNumber(int number){
        if(!directory.containsKey(number)) throw new RuntimeException("Нет сотрудника с табельным номером " + number);
        return directory.get(number);
    }
    public void print(){
        directory.forEach((K, V) -> System.out.println("Табельный номер: " + K + " имя сотрудника: " + V.getName()));
    }

}
