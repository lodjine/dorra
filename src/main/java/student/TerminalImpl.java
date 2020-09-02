/*
 * Creation : 13 août 2020
 */
package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ias.Terminal;

public class TerminalImpl implements Terminal {

    String input;

    @Override
    public void promptInput(String input) {
        if (input.contains("Play (")) {
            input = input.substring("Play".length() + 2, input.length() - 1);
            input = input.replaceAll("\\|", " ");
        }
        this.input = input;
        System.out.println(this.input);
        if (input.contains("play>")) {
            Scanner sc = new Scanner(System.in);
            System.out.println(input);
            this.input = sc.nextLine();
        }

    }

    @Override
    public String[] readInput() {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return this.input.split(" ");
    }

}
