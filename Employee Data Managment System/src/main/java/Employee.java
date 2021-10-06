public class Employee {

    private int id, birthyear;
    private String name,surname, title;

    public Employee(int id, String name, String surname, String title, int birthyear){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.title=title;
        this.birthyear=birthyear;
    }

    public int getId() {
        return id;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("| Name=%-8s| Surname=%-10s| Title=%-10s",name,surname,title);
    }
}
