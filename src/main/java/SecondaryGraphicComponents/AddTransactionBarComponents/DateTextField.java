package SecondaryGraphicComponents.AddTransactionBarComponents;

import FunctionalComponents.Transaction;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.time.DateTimeException;
import java.time.LocalDate;

public class DateTextField extends JTextField {
    boolean textIsValid = false;

    public DateTextField(Transaction localTransaction) {

        super("");
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tryToUpdateLocalTransaction(localTransaction);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                tryToUpdateLocalTransaction(localTransaction);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                tryToUpdateLocalTransaction(localTransaction);
            }
        });
    }


    public void tryToUpdateLocalTransaction(Transaction localTransaction) {
        DateTextField.TextFieldExtraction textFieldExtraction = new TextFieldExtraction(this.getText());
        System.out.println(this.getText());
        System.out.println(textFieldExtraction.valid);
        if( textFieldExtraction.valid){
            localTransaction.setDate(textFieldExtraction.date);
        }
        this.textIsValid = false;
    }


    static class TextFieldExtraction{

        LocalDate date;
        boolean valid;
        public TextFieldExtraction(String textFieldContent){

            this.date = LocalDate.now();
            this.valid = false;
            processDate(textFieldContent);
        }

        private void processDate(String textFieldContent) {
            int stringLength = textFieldContent.length();
            if (stringLength != 10)
                return;
            if (textFieldContent.charAt(2) != '/' || textFieldContent.charAt(5) != '/')
                return;
            int[] digitIndexArray = {0, 1, 3, 4, 6, 7, 8, 9};
            for (int digitIndex : digitIndexArray) {
                if (!Character.isDigit(textFieldContent.charAt(digitIndex)))
                    return;
            }
            LocalDate localDate;
            try {
                int day = Integer.parseInt(textFieldContent.substring(0, 2));
                int month = Integer.parseInt(textFieldContent.substring(3, 5));
                int year = Integer.parseInt(textFieldContent.substring(6, 10));
                localDate = LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                return;
            }
            this.date = localDate;
            this.valid = true;
        }

    }
}
