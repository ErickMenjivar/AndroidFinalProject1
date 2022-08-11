package algonquin.cst2335.owlbotdictionary;

public class WordModelDbClass {

    String word;
    String pronunciation;
    String definition;

    public WordModelDbClass(String word, String pronunciation, String definition) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.definition = definition;
    }

    public WordModelDbClass(){
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
