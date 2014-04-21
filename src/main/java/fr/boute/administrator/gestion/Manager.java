package fr.boute.administrator.gestion;

import java.util.Scanner;

/**
 * <b>Class Manager</b>
 * <p/>
 * This class manages all the entry given by the user.
 * <p/>
 *
 * @author Aurelien COLOMBET
 */
public class Manager {
    /**
     * The scanner to scan an entry
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * <b>Ask for a integer between min and max.</b>
     * <p>
     * This method analyzes the user's entry and parses it into an Integer. If
     * the value is between min and max, it returns it.
     * </p>
     *
     * @param min
     * @param max
     * @return the user's answer
     */
    public int requestInt(int min, int max) {
        int value = 0;
        while (true) {
            try {
                value = Integer.parseInt(requestString());
                if (value < min || value > max) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.err.println("Votre entree doit etre comprise entre "
                        + min + " et " + max + ", veuillez reessayer.");
            }
        }

        return value;
    }

    /**
     * <b>scan the entry and return it</b>
     * <p>
     * If the line enter by the user is too long, the method asks for an other
     * entry.
     * </p>
     *
     * @return the user's entry
     */
    public String requestString() {
        String text = new String();
        while (true) {
            try {
                text = sc.nextLine();
                if (text.length() > 30) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.err
                        .println("Chaine trop longue, veuillez reessayer (max 30 caracteres)");
            }
        }
        return text;
    }
}
