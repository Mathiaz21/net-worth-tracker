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


    public Vector<Account> getAccounts() {
        return listOfAccounts;
    }
    public Vector<Category> getOutcomeCategories() {
        return listOfOutcomeCategories;
    }
    public Vector<Category> getIncomeCategories() {
        return listOfIncomeCategories;
    }


    public String AccountIndexToName(int index) {

        for (Account a : this.listOfAccounts)
            if (a.getId() == index)
                return a.getName();
        String ExceptionString = String.format("Account %d does not exist", index);
        throw new IndexOutOfBoundsException(ExceptionString);
    }

    public String categoryIndexToName(int index) {

        for (Category outcomeCategory : listOfOutcomeCategories)
            if (outcomeCategory.getId() == index)
                return outcomeCategory.getName();

        for (Category incomeCategory : listOfIncomeCategories)
            if (incomeCategory.getId() == index)
                return incomeCategory.getName();

        String ExceptionString = String.format("Category %d does not exist", index);
        throw new IndexOutOfBoundsException(ExceptionString);
    }
}
