import java.util.ArrayList;

public class HairProfile {
    private String hairType;
    private String scalpType;
    private ArrayList<String> concerns;
    private int[] answers;   // answer of each questionnaire question (1 = first option)
    private boolean questionnaireDone;
    private boolean diagnosed;

    public HairProfile() {
        hairType = "unknown";
        scalpType = "unknown";
        concerns = new ArrayList<String>();
        answers = new int[7];
        questionnaireDone = false;
        diagnosed = false;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getScalpType() {
        return scalpType;
    }

    public void setScalpType(String scalpType) {
        this.scalpType = scalpType;
    }

    public ArrayList<String> getConcerns() {
        return concerns;
    }

    public void addConcern(String concern) {
        // Keep the concern list free of duplicates.
        if (!concerns.contains(concern)) {
            concerns.add(concern);
        }
    }

    public boolean hasConcern(String concern) {
        return concerns.contains(concern);
    }

    public void clearConcerns() {
        concerns.clear();
    }

    public int[] getAnswers() {
        return answers;
    }

    public void setAnswer(int questionIndex, int answer) {
        answers[questionIndex] = answer;
    }

    public boolean isQuestionnaireDone() {
        return questionnaireDone;
    }

    public void setQuestionnaireDone(boolean questionnaireDone) {
        this.questionnaireDone = questionnaireDone;
    }

    public boolean isDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(boolean diagnosed) {
        this.diagnosed = diagnosed;
    }
}
