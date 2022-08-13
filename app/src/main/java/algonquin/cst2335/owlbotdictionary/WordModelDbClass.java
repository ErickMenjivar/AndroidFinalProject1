package algonquin.cst2335.owlbotdictionary;

/**
 * A class for a word model in the database.
 */
public class WordModelDbClass {

    String word;
    String pronunciation;
    String definition;

    /**
     * The overloaded constructor for the WordModelDbClass class.
     * @param word The word.
     * @param pronunciation The pronunciation of the word.
     * @param definition The definition of the word.
     */
    public WordModelDbClass(String word, String pronunciation, String definition) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.definition = definition;
    }

    /**
     * The no-arg constructor for the WordModelDbClass class.
     */
    public WordModelDbClass(){
    }

    /**
     * A getter method for the word.
     * @return The word.
     */
    public String getWord() {
        return word;
    }

    /**
     * A setter method for the word.
     * @param word The word.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * A getter method for the pronunciation.
     * @return The pronunciation.
     */
    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * A setter method for the pronunciation.
     * @param pronunciation The pronunciation.
     */
    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    /**
     * A getter method for the definition.
     * @return The definition.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * A setter method for the definition.
     * @param definition The definition.
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
