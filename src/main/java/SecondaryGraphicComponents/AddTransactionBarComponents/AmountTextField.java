package SecondaryGraphicComponents.AddTransactionBarComponents;

import FunctionalComponents.Transaction;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AmountTextField extends JTextField {

    boolean textIsValid = false;

    public AmountTextField(Transaction localTransaction) {

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
        TextFieldExtraction textFieldExtraction = new TextFieldExtraction(this.getText());
        if( textFieldExtraction.valid){
            localTransaction.setAmountInCents(textFieldExtraction.amount);
        }
        this.textIsValid = false;
    }


    public void reset() {
        this.setText("");
    }

    class TextFieldExtraction{

        int amount;
        boolean valid;
        public TextFieldExtraction(String textFieldContent){

             this.amount = 0;
             this.valid = false;
             processEuroLoop(textFieldContent);
        }
        private void processEuroLoop(String textFieldContent){

            int stringLength = textFieldContent.length();
            int cursor = 0;
            while (cursor <= stringLength){
                if (cursor == stringLength) {
                    this.valid = true;
                    break;
                }
                if (textFieldContent.charAt(cursor) == ',' || textFieldContent.charAt(cursor) == '.'){
                    processCentsLoop(textFieldContent, cursor+1);
                }
                if (! Character.isDigit(textFieldContent.charAt(cursor)))
                    break;
                final int newDigit = Character.getNumericValue(textFieldContent.charAt(cursor));
                this.amount = this.amount*10 + newDigit*100;
                cursor += 1;
            }
        }

        private void processCentsLoop(String textFieldContent, int cursor){

            int stringLength = textFieldContent.length();
            if (cursor == stringLength)
                this.valid = true;
            if (cursor < stringLength){
                if (! Character.isDigit(textFieldContent.charAt(cursor)))
                    return;
                this.amount += Character.getNumericValue(textFieldContent.charAt(cursor))*10;
                cursor++;
            }
            if (cursor == stringLength)
                this.valid = true;
            if (cursor < stringLength){
                if (! Character.isDigit(textFieldContent.charAt(cursor)))
                    return;
                this.amount += Character.getNumericValue(textFieldContent.charAt(cursor));
                this.valid = true;
            }
        }
    }
}
