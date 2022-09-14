package ogpave12;

public class Person {
    private int id;
    private String navn, by;

    public Person(int id, String navn, String by){
        this.id = id;
        this.navn = navn;
        this.by = by;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", navn='" + navn + '\'' +
                ", by='" + by + '\'' +
                '}';
    }
}
