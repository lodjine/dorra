/*
 * Creation : 13 août 2020
 */
package student;

import java.util.Scanner;

import ias.Terminal;

public class TerminalImpl implements Terminal {

    String input;

    @Override
    public void promptInput(String input) {
        if (input.contains("Play (")) {
            input = input.substring("Play".length() + 2, input.length() - 1);
            input = input.replaceAll("\\|", " ");
            this.input = input;
        }
        if (input.contains("play>")) {
            Scanner sc = new Scanner(System.in);
            System.out.println(input);
            this.input = sc.nextLine();
        }

    }

    @Override
    public String[] readInput() {
        // TODO Auto-generated method stub
        return this.input.split(" ");
    }

}
