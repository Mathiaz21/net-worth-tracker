package FunctionalComponents;

import java.time.LocalDate;




public class Transaction {

    private int transactionID;
    private LocalDate date;
    private int amountInCents;
    private TypeOfTransaction type;
    private int outcomeAccountId;
    private int incomeAccountId;
    private int categoryId;
    private String description;

    public Transaction(LocalDate date,
                       int amountInCents,
                       TypeOfTransaction type,
                       int outcomeAccount,
                       int incomeAccount,
                       int category,
                       String description) {
        this.transactionID = -1;
        this.date = date;
        this.amountInCents = amountInCents;
        this.type = type;
        this.outcomeAccountId = outcomeAccount;
        this.incomeAccountId = incomeAccount;
        this.categoryId = category;
        this.description = description;
    }

    public Transaction(int transactionID,
                       LocalDate date,
                       int amountInCents,
                       TypeOfTransaction type,
                       int outcomeAccount,
                       int incomeAccount,
                       int category,
                       String description) {
        this.transactionID = transactionID;
        this.date = date;
        this.amountInCents = amountInCents;
        this.type = type;
        this.outcomeAccountId = outcomeAccount;
        this.incomeAccountId = incomeAccount;
        this.categoryId = category;
        this.description = description;
    }

    public int getTransactionID() { return transactionID; }
    public LocalDate getDate() { return date; }
    public int getAmountInCents() { return amountInCents; }
    public TypeOfTransaction getType() { return type; }
    public int getOutcomeAccountId() { return outcomeAccountId; }
    public int getIncomeAccountId() { return incomeAccountId; }
    public int getCategoryId() { return categoryId; }
    public String getDescription() { return description; }

    public void setTransactionID(int transactionID) { this.transactionID = transactionID; }
    public void setDate(LocalDate newDate) { this.date = newDate; }
    public void setAmountInCents(int newAmountInCents) { this.amountInCents = newAmountInCents; }
    public void setType(TypeOfTransaction newType) { this.type = newType; }
    public void setOutcomeAccountId(int newOutcomeAccount) { this.outcomeAccountId = newOutcomeAccount; }
    public void setIncomeAccountId(int newIncomeAccount) { this.incomeAccountId = newIncomeAccount; }
    public void setCategoryId(int newCategory) { this.categoryId = newCategory; }
    public void setDescription(String newDescription) { this.description = newDescription; }

    public static int transactionTypeToInt(TypeOfTransaction type) {
        return switch (type) {
            case INCOME -> 0;
            case OUTCOME -> 1;
            case INTERNAL -> 2;
        };
    }

    public void print(){
        System.out.printf("Transaction ID: %d%n", transactionID);
        System.out.printf("Amount : %d%n", this.amountInCents);
        System.out.printf("Type : %s%n", this.type.toString());
        System.out.printf("Outcome Account : %d%n", this.outcomeAccountId);
        System.out.printf("Income Account : %d%n", this.incomeAccountId);
        System.out.printf("Category : %d%n", this.categoryId);
        System.out.printf("Description : %s%n%n", this.description);
    }
}
