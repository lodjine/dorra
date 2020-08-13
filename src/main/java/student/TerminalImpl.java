/*
 * Creation : 13 août 2020
 */
package student;

import ias.Terminal;

public class TerminalImpl implements Terminal {

    String input;

    @Override
    public void promptInput(String input) {
        this.input = input;

    }

    @Override
    public String[] readInput() {
        // TODO Auto-generated method stub
        return this.input.split(" ");
    }

}
