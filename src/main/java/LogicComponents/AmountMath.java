package LogicComponents;

import FunctionalComponents.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class AmountMath {

    GlobalInfo globalInfo;
    ArrayList<Transaction> transactions;
    public AmountMath(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
        this.transactions = globalInfo.getTransactions();
    }

    public void orderTransactionByDate() {
        this.transactions.sort(Comparator.comparing(Transaction::getDate));
    }

    public boolean areTransactionsOrdered() {

        int numberOfTransactions = this.transactions.size();
        for (int i = 0; i < numberOfTransactions-1; i++) {

            Transaction t1 = this.transactions.get(i);
            Transaction t2 = this.transactions.get(i+1);
            if (t1.getDate().isAfter(t2.getDate()))
                return false;
        }
        return true;
    }

    public int computeCurrentBalance() {

        if ( !areTransactionsOrdered() )
            orderTransactionByDate();
        int currentBalance = 0;
        for (Transaction t : transactions)
            currentBalance += getTransactionAmount(t);
        return currentBalance;
    }

    public ArrayList<Integer> getDailyBalancesBetweenDates(LocalDate startDate, LocalDate endDate) {

        ArrayList<Integer> balanceList = new ArrayList<>();
        int balance = 0;
        if (this.transactions.isEmpty()) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            for (int i = 0; i < days; i++)
                balanceList.add(balance);
            return balanceList;
        }

        LocalDate balanceDateCursor = startDate;
        int transactionCursor = 0;
        while ( endDate.isAfter(balanceDateCursor) ){

            while ( areTransactionsToProcess(this.transactions, transactionCursor, balanceDateCursor) ) {
                balance += getTransactionAmount( transactions.get(transactionCursor) );
                transactionCursor++;
            }
            balanceList.add(balance);
            balanceDateCursor = balanceDateCursor.plusDays(1);
        }
        return balanceList;
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
        if (transactions.get(transactionCursor).getDate() .isAfter(balanceDateCursor))
            return false;
        return true;
    }
}
