package FunctionalComponents;

public class Category {

    private int id;
    private String name;
    private TypeOfTransaction typeOfCategory;

    public Category(int id, String name, TypeOfTransaction typeOfCategory) {
        this.id = id;
        this.name = name;
        this.typeOfCategory = typeOfCategory;
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public TypeOfTransaction getTypeOfCategory() {return typeOfCategory;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setTypeOfCategory(TypeOfTransaction typeOfCategory) {
        switch(typeOfCategory) {
            case OUTCOME -> this.typeOfCategory = TypeOfTransaction.OUTCOME;
            case INCOME -> this.typeOfCategory = TypeOfTransaction.INCOME;
            case INTERNAL -> throw new IllegalStateException("There is no category for an internal transfer");
        }
    }

    public void print() {
        System.out.println(this.name);
        System.out.println(typeOfCategory);
        System.out.println(id);
    }
}
