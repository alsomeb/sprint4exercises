package org.example.uppgift9;

public class RiddleProtocol {

    enum State {
        WAITING,
        SENTINTRO,
        SENTRIDDLE,
        ANOTHER
    }

    //private static final int NUMRIDDLES = 3;
    // Vi har en try catch index out of bounds

    // State actual
    private int currentRiddle = 0;
    private State currentState = State.WAITING;

    private final String[] answers = {"Egg",
            "Candle",
            "Sponge"
    };

    private final String[] riddles = {"What has to be broken before you can use it?",
            "I’m tall when I’m young, and I’m short when I’m old. What am I?",
            "What is full of holes but still holds water?"
    };

    // Ta höjd på att efter 3 gåtor (eller så många som finns i array)
    // så blir output: "du har gjort alla gåtor, Spela igen ?"
    public String processInput(String input) {
        String output = null; // inget i början sedan matas från client pga vi har WAITING state

        if (currentState.equals(State.WAITING)) {
            output = "Welcome to Riddler. Are you ready for your first trial Y/N?";
            currentState = State.SENTINTRO;

        } else if (currentState.equals(State.SENTINTRO)) {
            if(input.equalsIgnoreCase("Y")) {
                output = riddles[currentRiddle];
                currentState = State.SENTRIDDLE;
            } else {
                output = "Bye."; // Avslutar spelet
            }

        } else if (currentState.equals(State.SENTRIDDLE)) {
            if (input.equalsIgnoreCase(answers[currentRiddle])) {
                output = "Correct! Want another riddle ? Y/N";
                currentState = State.ANOTHER;
            } else {
                output = "Wrong, try again !";
            }

        } else if (currentState.equals(State.ANOTHER)) {
            if (input.equalsIgnoreCase("Y")) {
                try {
                    currentRiddle++;
                    output = riddles[currentRiddle];
                    currentState = State.SENTRIDDLE;
                } catch (IndexOutOfBoundsException e) {
                    output = "You have solved all riddles, try again ? Y/N";
                    currentRiddle = 0;
                    currentState = State.SENTINTRO; // Skippar INTRO Scene
                }
            } else {
                output = "Bye."; // Avslutar spelet
            }

        }
        return output;
    }
}
