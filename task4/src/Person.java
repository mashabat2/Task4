public class Person {
    private String name;
    private String  birthday;

    Person (String name, String bday){
        this.name = name;
        this.birthday = bday;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String toString(){
        return name + ", " + birthday;
    }
}