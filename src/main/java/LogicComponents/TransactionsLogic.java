package LogicComponents;

import FunctionalComponents.Account;
import FunctionalComponents.Transaction;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class TransactionsLogic {

    ArrayList<Transaction> transactions;
    public TransactionsLogic(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void orderTransactionByDate() {
        if( !areTransactionsOrdered())
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

        orderTransactionByDate();
        int currentBalance = 0;
        for (Transaction t : transactions)
            currentBalance += getTransactionAmount(t);
        return currentBalance;
    }

    public ArrayList<Integer> getDailyBalancesBetweenDates(LocalDate startDate, LocalDate endDate, GlobalInfo globalInfo) {

        ArrayList<Integer> balanceList = new ArrayList<>();
        int balance = globalInfo.getTotalInitialAmount();
        if (this.transactions.isEmpty()) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            for (int i = 0; i < days; i++)
                balanceList.add(balance);
            return balanceList;
        }

        LocalDate balanceDateCursor = startDate;
        int transactionCursor = 0;
        this.orderTransactionByDate();
        while ( balanceDateCursor.isBefore(endDate.plusDays(1)) ) {

            while ( areTransactionsToProcess(this.transactions, transactionCursor, balanceDateCursor) ) {
                balance += getTransactionAmount( transactions.get(transactionCursor) );
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
        while( YearMonth.from(this.transactions.get(transactionCursor).getDate()) .isBefore(monthlyCursor))  {
            transactionCursor++;
        }

        while( !monthlyCursor.isAfter(endMonth))  {

            String thisMonthLabel = monthLabel(monthlyCursor);
            int thisMonthOutcome = 0;
            int thisMonthIncome = 0;
            while( this.transactionIsDuringMonth(transactionCursor, monthlyCursor) )  {

                Transaction t = this.transactions.get(transactionCursor);
                switch (t.getType()) {
                    case OUTCOME -> thisMonthOutcome -= getTransactionAmount(t);
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
        if (transactionCursor >= this.transactions.size())
            return false;
        return this.transactions.get(transactionCursor).getDate().getMonth().equals(monthlyCursor.getMonth());
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

    private static String monthLabel(YearMonth yearMonth) {
        String label = "";
        switch (yearMonth.getMonth()){
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
}
