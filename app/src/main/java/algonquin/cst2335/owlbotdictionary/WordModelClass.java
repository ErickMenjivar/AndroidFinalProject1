package algonquin.cst2335.owlbotdictionary;

/**
 * A class for the word model.
 */
public class WordModelClass {

    //String word;
    //String pronunciation;
    String definition;

    /**
     * The overloaded constructor for the WordModelClass.
     * @param definition The definition of the word.
     */
    public WordModelClass(String definition){
        this.definition = definition;
    }

    /**
     * The no-arg constructor for the WordModelClass.
     */
    public WordModelClass(){
    }

    /**
     * The getter method for the definition.
     * @return The definition.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * The setter method for the definition.
     * @param definition The definition.
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
