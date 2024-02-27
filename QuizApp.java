import java.util.Scanner;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private Question[] questions;
    private int score;

    public Quiz(Question[] questions) {
        this.questions = questions;
    }

    public void conductQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print("Your answer (enter the option number): ");
            int userAnswerIndex = scanner.nextInt();
            scanner.nextLine(); 
            
            if (userAnswerIndex - 1 == question.getCorrectAnswerIndex()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + options[question.getCorrectAnswerIndex()]);
            }
            System.out.println();
        }
        scanner.close();
    }

    public int getScore() {
        return score;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is Java?", new String[]{"Markup language", "Programming language", "Database management System", "None of above"}, 1),
            new Question("Which keyword is used to define a class in Java?", new String[]{"System", "Void", "Class", "New"}, 2),
            new Question("Which method is called automatically when an object is created in Java?", new String[]{"destroy()", "finalize()", "init()", "constructor()"}, 3),
            new Question("What is an interface in Java?", new String[]{"It defines a blueprint of a class.", "It is a collection of abstract methods and constants.", " It is used to define common behavior that classes must implement.", "All of the above."}, 3),
            new Question("Which loop is guaranteed to execute at least once in Java?", new String[]{"while loop", "for loop", "do while loop", "switch loop"}, 2),
            new Question("Which of the following is used to create an object in Java?", new String[]{"new", "this", "super", "create"}, 0),
            new Question("What is the correct syntax for declaring a method in Java?", new String[]{"method myMethod() {}", "myMethod() {}", " void myMethod() {}", "myMethod void() {}"}, 2),
            new Question("Which of the following is not a valid Java identifier?", new String[]{" _variableName", "1variableName", "variableName", "variable_Name"}, 1),
            new Question("Which data type is used to store a single character in Java?", new String[]{"String", "char", "character", "bool"}, 1),
            new Question("Which keyword is used to prevent a method from being overridden in a subclass?", new String[]{"static", "private", "abstract", "final"}, 3)
        };

        Quiz quiz = new Quiz(questions);
        quiz.conductQuiz();

        System.out.println("Your score: " + quiz.getScore() + " out of " + questions.length);
    }
}