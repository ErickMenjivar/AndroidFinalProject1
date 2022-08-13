package algonquin.cst2335.owlbotdictionary;

/**
 * An interface for selection listening.
 */
public interface SelectListener {
    /**
     * An on item click listener method.
     * @param position The position.
     */
    void onItemClicked(int position);

    /**
     * A method to save the variable values.
     * @param val1 The first value.
     * @param val2 The second value.
     * @param val3 The third value.
     */
    void saveVarValues(String val1, String val2, String val3);
}
