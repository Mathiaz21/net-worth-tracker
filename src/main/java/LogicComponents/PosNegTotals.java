package LogicComponents;

import java.util.ArrayList;

public class PosNegTotals {
    public ArrayList<Float> negativeTotal;
    public ArrayList<Float> positiveTotal;
    public ArrayList<String> labels;

    public PosNegTotals() {
        this.negativeTotal = new ArrayList<>();
        this.positiveTotal = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    public PosNegTotals(ArrayList<Float> negativeTotal, ArrayList<Float> positiveTotal, ArrayList<String> labels) {
        this.negativeTotal = negativeTotal;
        this.positiveTotal = positiveTotal;
        this.labels = labels;
    }
}
