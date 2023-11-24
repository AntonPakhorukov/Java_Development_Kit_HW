public class Employee {
    private int personellNumber;
    private long phoneNumber;
    private String name;
    private int experience;
    public Employee(int personellNumber, long phoneNumber, String name, int experience) {
        this.personellNumber = personellNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }
    public int getPersonellNumber() {
        return personellNumber;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public String getName() {
        return name;
    }
    public int getExperience() {
        return experience;
    }

    public void setPersonellNumber(int personellNumber) {
        this.personellNumber = personellNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Код сотрудника: " + personellNumber +
                ", имя сотрудника: " + name;
    }
}
