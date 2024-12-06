package DBConnection;

import FunctionalComponents.Account;
import FunctionalComponents.Category;
import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

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
        ArrayList<Transaction> transactions = DBTransactionComm.getAllTransactions();
        for(Transaction transaction : transactions){
            transaction.print();
        }
    }

    @org.junit.Test
    @Test
    public void testGetCategories() {
        ArrayList<Category> categories = DBCategoryComm.getIncomeCategories();
        for(Category category : categories){ category.print(); }
    }

    @org.junit.Test
    @Test
    public void testGetAccounts() {
        ArrayList<Account> accounts = DBAccountComm.getAccounts();
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