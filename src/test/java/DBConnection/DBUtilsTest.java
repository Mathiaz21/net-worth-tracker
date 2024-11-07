package DBConnection;

import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DBUtilsTest {

    @org.junit.Test
    @Test
    public void testTransactionInserting(){
        Transaction transaction = new Transaction(
                LocalDate.now(),
                0,
                TypeOfTransaction.OUTCOME,
                2,
                3,
                4,
                "Getting me some food"
        );
        DBUtils.addTransactionInDB(transaction);
    }

    @org.junit.Test
    @Test
    public void testTransactionDeleting(){
        int idOfDeletion = 2;
        DBUtils.deleteTransactionById(idOfDeletion);
    }

    @org.junit.Test
    @Test
    public void testTransactionUpdate(){
        int id = 3;
        Transaction transaction = new Transaction(
                LocalDate.now(),
                0,
                TypeOfTransaction.OUTCOME,
                2,
                3,
                4,
                "Okay I pull up"
        );
        DBUtils.updateTransactionInDB(id, transaction);
    }

    @org.junit.Test
    @Test
    public void testGetAllTransactions(){
        List<Transaction> transactions = DBUtils.getAllTransactions();
        for(Transaction transaction : transactions){
            transaction.print();
        }
    }
}