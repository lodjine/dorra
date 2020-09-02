package student;

import ias.TextAdventure;
import ias.TextAdventureException;

/**
 * Factory class with functions to generate a game and terminal.
 */
public final class Factory {

    private Factory() {
    }

    /**
     * Creates a new game-instance with an empty board of the given size.
     * 
     * @param name The name of the game
     * @param boardWidth the width of the board
     * @param boardHeight the height of the board
     * @return a fresh game instance
     * @throws ias.TextAdventureException Think about the cases in which an exception is useful and implement it.
     */
    public static ias.TextAdventure getGame(String name, int boardWidth, int boardHeight) throws ias.TextAdventureException {
        if (boardWidth <= 0 || boardHeight <= 0) {
            throw new TextAdventureException("Error, cannot set negative value in boardWidth and/or boardHeight");
        }
        TextAdventure ta = new Adventure(name, boardWidth, boardHeight);
        return ta;
    }

    /**
     * Creates a new terminal-instance to read user input and prompt messages.
     * 
     * @return a terminal instance
     */
    public static ias.Terminal getTerminal() {
        // placeholder
        return new TerminalImpl();
    }
}
