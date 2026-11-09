import java.util.Scanner;
import java.util.Stack;

public class Diagnosis {
    private Stack<Integer> history = new Stack<Integer>();

    private String[] questions = {
        "What is your hair type?",
        "How does your scalp feel one day after washing?",
        "Do you see white flakes on your scalp or shoulders?",
        "Do you lose a lot of hair while combing or washing?",
        "Does your hair feel dry and rough?",
        "Does your hair get frizzy, especially in humid weather?",
        "Do you have split ends or heat / colour damaged hair?"
    };

    private String[][] options = {
        {"Straight", "Wavy", "Curly"},
        {"Oily", "Dry and tight", "Normal"},
        {"Yes", "No"},
        {"Yes", "No"},
        {"Yes", "No"},
        {"Yes", "No"},
        {"Yes", "No"}
    };

    // Stack keeps the questions already answered, so the user can go back
    public void askQuestions(Scanner sc, HairProfile profile) {
        history.clear();
        int current = 0;
        System.out.println("\n--- Hair Care Questionnaire ---");
        System.out.println("Type the option number to answer. Type 0 to go back.");

        while (current < questions.length) {
            System.out.println("\nQuestion " + (current + 1) + " of " + questions.length);
            System.out.println(questions[current]);
            for (int i = 0; i < options[current].length; i++) {
                System.out.println("  " + (i + 1) + ". " + options[current][i]);
            }
            System.out.print("Your answer: ");
            int answer = Main.readNumber(sc, 0, options[current].length);

            if (answer == 0) {
                if (history.isEmpty()) {
                    System.out.println("This is the first question, cannot go back.");
                } else {
                    current = history.pop();
                }
            } else {
                profile.setAnswer(current, answer);
                history.push(current);
                current++;
            }
        }

        profile.setQuestionnaireDone(true);
        profile.setDiagnosed(false);
        System.out.println("\nQuestionnaire completed. Choose option 3 to see your diagnosis.");
    }

    // rule based diagnosis: answers -> hair type, scalp type and concerns
    public void diagnose(HairProfile profile) {
        int[] a = profile.getAnswers();
        profile.clearConcerns();

        if (a[0] == 1) {
            profile.setHairType("straight");
        } else if (a[0] == 2) {
            profile.setHairType("wavy");
        } else {
            profile.setHairType("curly");
        }

        if (a[1] == 1) {
            profile.setScalpType("oily");
        } else if (a[1] == 2) {
            profile.setScalpType("dry");
        } else {
            profile.setScalpType("normal");
        }

        if (a[2] == 1) {
            profile.addConcern("dandruff");
        }
        if (a[3] == 1) {
            profile.addConcern("hairfall");
        }
        if (a[4] == 1) {
            profile.addConcern("dryness");
        }
        if (a[5] == 1) {
            profile.addConcern("frizz");
        }
        if (a[6] == 1) {
            profile.addConcern("damage");
        }

        // extra rules
        // flakes on a dry scalp usually mean the scalp is dry
        if (profile.getScalpType().equals("dry") && profile.hasConcern("dandruff")) {
            profile.addConcern("dryness");
        }
        // dry curly hair is very likely to turn frizzy
        if (profile.getHairType().equals("curly") && profile.hasConcern("dryness")) {
            profile.addConcern("frizz");
        }

        profile.setDiagnosed(true);
    }

    public void showDiagnosis(HairProfile profile) {
        System.out.println("\n--- Your Hair Diagnosis ---");
        System.out.println("Hair type  : " + profile.getHairType());
        System.out.println("Scalp type : " + profile.getScalpType());

        if (profile.getConcerns().isEmpty()) {
            System.out.println("Concerns   : none");
            System.out.println("Your hair looks healthy. Just keep up a regular hair care routine.");
            return;
        }

        System.out.println("Concerns   : " + profile.getConcerns());
        System.out.println("\nTips:");
        if (profile.hasConcern("dandruff")) {
            System.out.println("- Use an anti-dandruff shampoo 2 to 3 times a week.");
        }
        if (profile.hasConcern("hairfall")) {
            System.out.println("- Oil your scalp gently once a week and avoid tight hairstyles.");
        }
        if (profile.hasConcern("dryness")) {
            System.out.println("- Use a conditioner after every wash and avoid very hot water.");
        }
        if (profile.hasConcern("frizz")) {
            System.out.println("- Use a serum on damp hair and do not rub with a towel.");
        }
        if (profile.hasConcern("damage")) {
            System.out.println("- Use a heat protectant and trim split ends regularly.");
        }
    }
}
