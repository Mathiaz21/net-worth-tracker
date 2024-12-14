package LogicComponents;

import DBConnection.DBAccountComm;
import DBConnection.DBCategoryComm;
import DBConnection.DBTransactionComm;
import FunctionalComponents.Account;
import FunctionalComponents.Category;
import FunctionalComponents.Transaction;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class GlobalInfo {

    private ArrayList<Account> listOfAccounts;
    private ArrayList<Category> listOfOutcomeCategories;
    private ArrayList<Category> listOfIncomeCategories;
    private ArrayList<Transaction> listOfTransactions;
    private int selectedMinIndex;
    private int selectedMaxIndex;

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
    public int getSelectedMinIndex() { return selectedMinIndex; }
    public int getSelectedMaxIndex() { return selectedMaxIndex; }
    public boolean indexIsSelected(int index) {
        return this.selectedMinIndex <= index && this.selectedMaxIndex >= index;
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

    public void selectOneItem(int selectedIndex) {
        if(selectedIndex < 0 || selectedIndex > this.listOfTransactions.size())
            throw new InvalidParameterException("Selected index out of bounds");
        if(this.selectedMinIndex == selectedIndex && this.selectedMaxIndex == selectedIndex){
            this.selectedMinIndex = -1;
            this.selectedMaxIndex = -1;
            return;
        }
        this.selectedMinIndex = selectedIndex;
        this.selectedMaxIndex = selectedIndex;
    }

    public void selectRangeOfItems(int newIndex) {
        if(this.selectedMaxIndex == -1){
            selectedMinIndex = newIndex;
            return;
        }
        if (newIndex < this.selectedMinIndex)
            this.selectedMinIndex = newIndex;
        if (newIndex > this.selectedMaxIndex)
            this.selectedMaxIndex = newIndex;
    }


    public void refreshTransactionsFromDB() {
        this.listOfTransactions = DBTransactionComm.getAllTransactions();
    }

    public int getTotalInitialAmount() {
        int initialAmount = 0;
        for (Account account: this.getAccounts())
            initialAmount += account.getInitialAmount();
        return initialAmount;
    }

}
