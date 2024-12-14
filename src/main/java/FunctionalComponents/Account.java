package FunctionalComponents;

public class Account {

    private int id;
    private String name;
    private int initialAmount;

    public Account(int id, String name, int initialAmount) {
        this.id = id;
        this.name = name;
        this.initialAmount = initialAmount;
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public int getInitialAmount() {return initialAmount;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setInitialAmount(int initialAmount) {this.initialAmount = initialAmount;}

    public void print(){
        System.out.println(this.name);
        System.out.println(this.id);
        System.out.println(this.initialAmount);
    }
}
