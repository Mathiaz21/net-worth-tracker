package DBConnection;

import FunctionalComponents.Account;
import FunctionalComponents.Category;
import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

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
        DBTransactionComm.addTransactionInDB(transaction);
    }

    @org.junit.Test
    @Test
    public void testTransactionDeleting(){
        int idOfDeletion = 2;
        DBTransactionComm.deleteTransactionById(idOfDeletion);
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
        DBTransactionComm.updateTransactionInDB(id, transaction);
    }

    @org.junit.Test
    @Test
    public void testGetAllTransactions(){
        List<Transaction> transactions = DBTransactionComm.getAllTransactions();
        for(Transaction transaction : transactions){
            transaction.print();
        }
    }

    @org.junit.Test
    @Test
    public void testGetCategories() {
        Vector<Category> categories = DBCategoryComm.getIncomeCategories();
        for(Category category : categories){ category.print(); }
    }

    @org.junit.Test
    @Test
    public void testGetAccounts() {
        Vector<Account> accounts = DBAccountComm.getAccounts();
        for(Account account : accounts){
            account.print();
        }
    }


    @org.junit.Test
    @Test
    public void testRenameAccount() {
        DBAccountComm.renameAccount(0, "Sumeria");
    }
}