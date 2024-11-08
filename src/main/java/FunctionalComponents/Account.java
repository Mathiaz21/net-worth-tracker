package FunctionalComponents;

public class Account {

    private int id;
    private String name;

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}


    public void print(){
        System.out.println(this.name);
        System.out.println(this.id);
    }
}
