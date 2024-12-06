package LogicComponents;

import FunctionalComponents.Transaction;

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
            switch (t.getType()) {
                case OUTCOME -> currentBalance -= t.getAmountInCents();
                case INCOME -> currentBalance += t.getAmountInCents();
            }
        return currentBalance;
    }
}
