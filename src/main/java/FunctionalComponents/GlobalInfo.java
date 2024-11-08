package FunctionalComponents;

import DBConnection.DBAccountComm;
import DBConnection.DBCategoryComm;

import java.util.Vector;

public class GlobalInfo {

    private Vector<Account> listOfAccounts;
    private Vector<Category> listOfOutcomeCategories;
    private Vector<Category> listOfIncomeCategories;

    public GlobalInfo() {
        this.listOfAccounts = new Vector<>();
        this.listOfOutcomeCategories = new Vector<>();
        this.listOfIncomeCategories = new Vector<>();
        this.loadGlobalInfoFromDB();
    }

    public void loadGlobalInfoFromDB() {
        this.listOfAccounts = DBAccountComm.getAccounts();
        this.listOfIncomeCategories = DBCategoryComm.getIncomeCategories();
        this.listOfOutcomeCategories = DBCategoryComm.getOutcomeCategories();
    }

    public Vector<String> getAccountNames() {
        Vector<String> accountNames = new Vector<>();
        for(Account a : this.listOfAccounts)
            accountNames.add(a.getName());
        return accountNames;
    }

    public Vector<String> getOutcomeCategoriesNames() {
        Vector<String> outcomeCategoriesNames = new Vector<>();
        for(Category c : this.listOfOutcomeCategories)
            outcomeCategoriesNames.add(c.getName());
        return outcomeCategoriesNames;
    }

    public Vector<String> getIncomeCategoriesNames() {
        Vector<String> incomeCategoriesNames = new Vector<>();
        for(Category c : this.listOfIncomeCategories)
            incomeCategoriesNames.add(c.getName());
        return incomeCategoriesNames;
    }
}
