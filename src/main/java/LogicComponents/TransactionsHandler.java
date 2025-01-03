package LogicComponents;

import DBConnection.DBTransactionComm;
import FunctionalComponents.Account;
import FunctionalComponents.Category;
import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class TransactionsHandler {

    public ArrayList<Transaction> listOfTransactions;
    private int selectedMinIndex;
    private int selectedMaxIndex;

    public TransactionsHandler() {
        this.listOfTransactions = DBTransactionComm.getAllTransactions();
    }

    public void addSyncedTransaction(Transaction transaction) {
        this.listOfTransactions.add(new Transaction(transaction));
        DBTransactionComm.addTransactionInDB(transaction);
    }

    public void refreshTransactionsFromDB() {
        this.listOfTransactions = DBTransactionComm.getAllTransactions();
    }

    public void orderTransactionByDate() {
        if( !areTransactionsOrdered())
            this.listOfTransactions.sort(Comparator.comparing(Transaction::getDate));
    }

    public boolean areTransactionsOrdered() {

        int numberOfTransactions = this.listOfTransactions.size();
        for (int i = 0; i < numberOfTransactions-1; i++) {

            Transaction t1 = this.listOfTransactions.get(i);
            Transaction t2 = this.listOfTransactions.get(i+1);
            if (t1.getDate().isAfter(t2.getDate()))
                return false;
        }
        return true;
    }

    public ArrayList<Transaction> getListOfTransactions() { return this.listOfTransactions; }
    public int getSelectedMinIndex() { return selectedMinIndex; }
    public int getSelectedMaxIndex() { return selectedMaxIndex; }
    public boolean indexIsSelected(int index) {
        return this.selectedMinIndex <= index && this.selectedMaxIndex >= index;
    }

    public int computeCurrentBalance() {

        orderTransactionByDate();
        int currentBalance = 0;
        for (Transaction t : listOfTransactions)
            currentBalance += getTransactionAmount(t);
        return currentBalance;
    }

    public ArrayList<Integer> getDailyBalancesBetweenDates(LocalDate startDate, LocalDate endDate, GlobalInfo globalInfo) {

        ArrayList<Integer> balanceList = new ArrayList<>();
        int balance = globalInfo.getTotalInitialAmount();
        if (this.listOfTransactions.isEmpty()) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            for (int i = 0; i < days; i++)
                balanceList.add(balance);
            return balanceList;
        }

        LocalDate balanceDateCursor = startDate;
        int transactionCursor = 0;
        this.orderTransactionByDate();
        while ( balanceDateCursor.isBefore(endDate.plusDays(1)) ) {

            while ( areTransactionsToProcess(this.listOfTransactions, transactionCursor, balanceDateCursor) ) {
                balance += getTransactionAmount( listOfTransactions.get(transactionCursor) );
                transactionCursor++;
            }
            balanceList.add(balance);
            balanceDateCursor = balanceDateCursor.plusDays(1);
        }
        return balanceList;
    }

    public PosNegTotals deriveMonthlyPosNegTotals(YearMonth startMonth, YearMonth endMonth) {

        PosNegTotals posNegTotals = new PosNegTotals();
        this.orderTransactionByDate();
        int transactionCursor = 0;
        YearMonth monthlyCursor = startMonth;
        while( YearMonth.from(this.listOfTransactions.get(transactionCursor).getDate()) .isBefore(monthlyCursor))  {
            transactionCursor++;
        }

        while( !monthlyCursor.isAfter(endMonth))  {

            String thisMonthLabel = monthLabel(monthlyCursor.getMonth());
            int thisMonthOutcome = 0;
            int thisMonthIncome = 0;
            while( this.transactionIsDuringMonth(transactionCursor, monthlyCursor) )  {

                Transaction t = this.listOfTransactions.get(transactionCursor);
                switch (t.getType()) {
                    case OUTCOME -> thisMonthOutcome += getTransactionAmount(t);
                    case INCOME -> thisMonthIncome += getTransactionAmount(t);
                }
                transactionCursor++;
            }
            posNegTotals.negativeTotal.add( ((float) thisMonthOutcome)/100);
            posNegTotals.positiveTotal.add( ((float) thisMonthIncome)/100);
            posNegTotals.labels.add(thisMonthLabel);
            monthlyCursor = monthlyCursor.plusMonths(1);
        }
        return posNegTotals;
    }

    private boolean transactionIsDuringMonth(int transactionCursor, YearMonth monthlyCursor) {
        if (transactionCursor >= this.listOfTransactions.size())
            return false;
        return this.listOfTransactions.get(transactionCursor).getDate().getMonth().equals(monthlyCursor.getMonth());
    }


    public static int getTransactionAmount(Transaction t) {
        int amount = 0;
        switch (t.getType()) {
            case OUTCOME -> amount -= t.getAmountInCents();
            case INCOME -> amount += t.getAmountInCents();
        }
        return amount;
    }

    private static LocalDate earliestDate(LocalDate d1, LocalDate d2) {
        if (d1.isBefore(d2))
            return d1;
        return d2;
    }

    private static boolean areTransactionsToProcess(ArrayList<Transaction> transactions, int transactionCursor, LocalDate balanceDateCursor) {
        if (transactionCursor >= transactions.size())
            return false;
        if (balanceDateCursor.isBefore(transactions.get(transactionCursor).getDate()))
            return false;
        return true;
    }

    public static String monthLabel(Month month) {
        String label = "";
        switch (month){
            case JANUARY -> label = "Jan";
            case FEBRUARY -> label = "Feb";
            case MARCH -> label = "Mar";
            case APRIL -> label = "Apr";
            case MAY -> label = "May";
            case JUNE -> label = "Jun";
            case JULY -> label = "Jul";
            case AUGUST -> label = "Aug";
            case SEPTEMBER -> label = "Sep";
            case OCTOBER -> label = "Oct";
            case NOVEMBER -> label = "Nov";
            case DECEMBER -> label = "Dec";
        }
        return label;
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

    public boolean oneTransactionIsSelected() {
        return this.selectedMinIndex==this.selectedMaxIndex;
    }

    public int numberOfTransactionsSelected() {
        return this.selectedMaxIndex - this.selectedMinIndex + 1;
    }

    public Transaction getSelectedTransaction() throws Exception{
        if (!oneTransactionIsSelected())
            throw new Exception("Zero or several transactions are selected");
        return listOfTransactions.get(this.selectedMinIndex);
    }

    public static void setupAccountChoiceComboBox(JComboBox<String> accountChoiceComboBox, GlobalInfo globalInfo, Transaction transaction) {

        ArrayList<Account> accountList = globalInfo.getAccounts();
        for (Account account : accountList) {
            accountChoiceComboBox.addItem(account.getName());
        }

        accountChoiceComboBox.addActionListener(e -> {
            int selectedAccountIndex = accountChoiceComboBox.getSelectedIndex();
            TypeOfTransaction localTransactionType = transaction.getType();
            switch (localTransactionType) {
                case OUTCOME:
                case INTERNAL:
                    transaction.setOutcomeAccountId(selectedAccountIndex); break;
                case INCOME:
                    transaction.setIncomeAccountId(selectedAccountIndex); break;
            }
        });
    }

    public static void setupTransactionTypeComboBox(JComboBox<String> transactionTypeComboBox,
                                              GlobalInfo globalInfo,
                                              Transaction transaction,
                                              JComboBox<String> adaptableComboBox) {

        transactionTypeComboBox.removeAllItems();
        for(TypeOfTransaction typeOfTransaction : TypeOfTransaction.values())
            transactionTypeComboBox.addItem(Transaction.transactionTypeToString(typeOfTransaction));

        transactionTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TypeOfTransaction localTransactionType = Transaction.intToTransactionType(transactionTypeComboBox.getSelectedIndex());
                transaction.setType(localTransactionType);
                TransactionsHandler.setupAdaptableComboBox(adaptableComboBox, globalInfo, transaction);
            }
        });
    }

    public static void setupAdaptableComboBox(JComboBox<String> adaptableComboBox, GlobalInfo globalInfo, Transaction transaction) {

        TransactionsHandler.resetComboBox(adaptableComboBox);
        TypeOfTransaction localTransactionType = transaction.getType();
        switch (localTransactionType){
            case OUTCOME:
            case INCOME:
                TransactionsHandler.setupCategoryComboBox(adaptableComboBox, globalInfo, transaction);break;
            case INTERNAL:
                TransactionsHandler.setupSecondAccountComboBox(adaptableComboBox, globalInfo, transaction);
        }
    }

    private static void setupCategoryComboBox(JComboBox<String> categoryComboBox, GlobalInfo globalInfo, Transaction transaction) {

        ArrayList<Category> categories = switch (transaction.getType()) {
            case OUTCOME -> globalInfo.getOutcomeCategories();
            case INCOME -> globalInfo.getIncomeCategories();
            case INTERNAL ->
                    throw new ExceptionInInitializerError("Cannot set up category comboBox for internal transfer");
        };
        for (Category category : categories) {
            categoryComboBox.addItem(category.getName());
        }

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = categoryComboBox.getSelectedIndex();
                int categoryIndex = categories.get(selectedIndex).getId();
                transaction.setCategoryId(categoryIndex);
            }
        });
    }

    private static void setupSecondAccountComboBox(JComboBox<String> secondAccountComboBox, GlobalInfo globalInfo, Transaction transaction) {
        ArrayList<Account> accounts = globalInfo.getAccounts();
        for (Account account : accounts) {
            secondAccountComboBox.addItem(account.getName());
        }
        secondAccountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = secondAccountComboBox.getSelectedIndex();
                int accountIndex = accounts.get(selectedIndex).getId();
                transaction.setIncomeAccountId(accountIndex);
            }
        });
    }

    public static void resetComboBox(JComboBox<String> comboBox) {
        ActionListener[] actionListeners = comboBox.getActionListeners();
        for (ActionListener actionListener : actionListeners)
            comboBox.removeActionListener(actionListener);
        comboBox.removeAllItems();
    }

    public static String printReadableAmount(int amountInCents) {
        String stringAmount = amountInCents/100 + ".";
        int cents = amountInCents%100;
        if (cents <10)
            stringAmount += "0";
        stringAmount += cents;
        return stringAmount;
    }

    public static String printReadableDate(LocalDate date) {
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        return day + " " + monthLabel(month) + " " + year;
    }
}
