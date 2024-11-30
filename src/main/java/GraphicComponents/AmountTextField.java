package GraphicComponents;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountTextField extends JTextField {

    Pattern validContentPattern = Pattern.compile("(\\d+),(\\d{0,2})");

    public AmountTextField() {
        super("1,00");
    }

    private boolean contentIsValid() {
        Matcher matcher = this.validContentPattern.matcher(this.getText());
        return matcher.find();
    }

    public int textToCents() {
        Matcher matcher = this.validContentPattern.matcher(this.getText());
        if(this.contentIsValid()) {
            String amountOfEuros = matcher.group(1);
            String amountOfCents = matcher.group(2);
            int amountInCents = 100*Integer.parseInt(amountOfEuros) + Integer.parseInt(amountOfCents);
            System.out.println(amountInCents);
            return amountInCents;
        }
        else
            return 0;
    }

    
}
