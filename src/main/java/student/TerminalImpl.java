/*
 * Creation : 13 août 2020
 */
package student;

import ias.Terminal;

public class TerminalImpl implements Terminal {

    String input;

    @Override
    public void promptInput(String input) {
        if (input.contains("Play ("))
            input = input.substring("Play".length() + 2, input.length() - 1);
        input = input.replaceAll("\\|", " ");
        this.input = input;

    }

    @Override
    public String[] readInput() {
        // TODO Auto-generated method stub
        return this.input.split(" ");
    }

}
