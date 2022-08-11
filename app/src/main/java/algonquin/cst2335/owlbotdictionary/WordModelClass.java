package algonquin.cst2335.owlbotdictionary;

public class WordModelClass {

    //String word;
    //String pronunciation;
    String definition;

    public WordModelClass(String definition){
        this.definition = definition;
    }

    public WordModelClass(){
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
