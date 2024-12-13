package LogicComponents;

import DBConnection.DBAccountComm;
import DBConnection.DBCategoryComm;
import DBConnection.DBTransactionComm;
import FunctionalComponents.Account;
import FunctionalComponents.Category;
import FunctionalComponents.Transaction;

import java.util.ArrayList;

public class GlobalInfo {

    private ArrayList<Account> listOfAccounts;
    private ArrayList<Category> listOfOutcomeCategories;
    private ArrayList<Category> listOfIncomeCategories;
    private ArrayList<Transaction> listOfTransactions;

    public GlobalInfo() {
        this.listOfAccounts = new ArrayList<>();
        this.listOfOutcomeCategories = new ArrayList<>();
        this.listOfIncomeCategories = new ArrayList<>();
        this.listOfTransactions = new ArrayList<>();
        this.loadGlobalInfoFromDB();
    }

    public void loadGlobalInfoFromDB() {
        this.listOfAccounts = DBAccountComm.getAccounts();
        this.listOfIncomeCategories = DBCategoryComm.getIncomeCategories();
        this.listOfOutcomeCategories = DBCategoryComm.getOutcomeCategories();
        this.listOfTransactions = DBTransactionComm.getAllTransactions();
    }


    public ArrayList<Account> getAccounts() {
        return listOfAccounts;
    }
    public ArrayList<Category> getOutcomeCategories() {
        return listOfOutcomeCategories;
    }
    public ArrayList<Category> getIncomeCategories() {
        return listOfIncomeCategories;
    }
    public ArrayList<Transaction> getTransactions() {
        return listOfTransactions;
    }


    public String accountIndexToName(int index) {

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


    public void addSyncedTransaction(Transaction transaction) {
        this.listOfTransactions.add(new Transaction(transaction));
        DBTransactionComm.addTransactionInDB(transaction);
    }
}
