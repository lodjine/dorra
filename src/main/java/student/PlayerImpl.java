/*
 * Creation : 13 août 2020
 */
package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ias.Player;
import ias.TextAdventureException;
import student.mutation.Composition;
import student.mutation.Decomposition;
import student.mutation.Transformation;
import student.object.Board;
import student.object.BoardObject;
import student.object.Item;

public class PlayerImpl implements Player {

    private int posX;
    private int posY;

    private Board board;

    public PlayerImpl(int boardWidth, int boardHeight) {
        super();
        board = new Board(boardWidth, boardHeight);
    }

    public void startGame(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public String go(String direction) {
        return resolveCoor(direction);
    }

    @Override
    public String[] look() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.getBoardWidth(); i++) {

            for (int y = 0; y < board.getBoardHeight(); y++) {
                List<BoardObject> list = new ArrayList<>();
                if (board.getBoard()[i][y] != null && !board.getBoard()[i][y].getObjects().isEmpty())
                    list = new ArrayList<>(board.getBoard()[i][y].getObjects().values());
                for (BoardObject boardObject : list) {
                    result.add(boardObject.getId() + " - " + boardObject.getDescription());
                }

            }

        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String[] inventory() {
        List<String> result = new ArrayList<>();
        List<Item> list = new ArrayList<>(board.getInventery().values());
        for (Item item : list) {
            result.add(item.getId() + " - " + item.getDescription());
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String take(String item) {
        if (board.getBoard() != null && board.getBoard()[posX][posY] != null && board.getBoard()[posX][posY].getObjects() != null
                && board.getBoard()[posX][posY].getObjects().get(item) != null) {
            if (board.getBoard()[posX][posY].getObjects().get(item) instanceof Item)
                board.getInventery().put(item, (Item) board.getBoard()[posX][posY].getObjects().get(item));
            return "You pick up the " + item;

        }
        return ("Sorry, you cannot pick up the " + item);
    }

    @Override
    public String drop(String item) {
        if (board.getInventery().containsKey(item)) {
            board.getInventery().remove(item);
            return "You drop the " + item;
        }

        return ("Sorry, you cannot drop the " + item);
    }

    @Override
    public String convert(String item1, String item2) {
        BoardObject object1 = board.getInventery().containsKey(item1) ? board.getInventery().get(item1)
                : board.getBoard()[posX][posY].getObjects().get(item1);
        if (object1 == null)
            try {
                throw new TextAdventureException("object " + item1 + " not exist");
            } catch (TextAdventureException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        BoardObject object2 = board.getInventery().containsKey(item2) ? board.getInventery().get(item1)
                : board.getBoard()[posX][posY].getObjects().get(item2);
        if (object2 == null)
            try {
                throw new TextAdventureException("object " + item2 + " not exist");
            } catch (TextAdventureException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        Composition comp = board.getCompositions().get(Composition.createId(object1, object2));
        if (comp != null) {
            if (comp.getResult() instanceof Item)
                board.getInventery().put(comp.getResult().getId(), (Item) comp.getResult());
            else
                board.getBoard()[posX][posY].getObjects().put(comp.getResult().getId(), comp.getResult());

            board.getInventery().remove(item1);
            board.getInventery().remove(item2);
            board.getBoard()[posX][posY].getObjects().remove(item1);
            board.getBoard()[posX][posY].getObjects().remove(item2);

            return comp.getDescrip();

        }

        Transformation trans = board.getTransformation().get(Transformation.createId(object1, object2));
        if (trans != null) {
            if (trans.getResult1() instanceof Item)
                board.getInventery().put(trans.getResult1().getId(), (Item) trans.getResult1());
            else
                board.getBoard()[posX][posY].getObjects().put(trans.getResult1().getId(), trans.getResult1());

            if (trans.getResult2() instanceof Item)
                board.getInventery().put(trans.getResult2().getId(), (Item) trans.getResult2());
            else
                board.getBoard()[posX][posY].getObjects().put(trans.getResult2().getId(), trans.getResult2());

            board.getInventery().remove(item1);
            board.getInventery().remove(item2);
            board.getBoard()[posX][posY].getObjects().remove(item1);
            board.getBoard()[posX][posY].getObjects().remove(item2);

            return trans.getDescrip();

        }

        return null;
    }

    @Override
    public String decompose(String item) {

        BoardObject object1 = board.getInventery().containsKey(item) ? board.getInventery().get(item)
                : board.getBoard()[posX][posY].getObjects().get(item);
        if (object1 == null)
            try {
                throw new TextAdventureException("object " + item + " not exist");
            } catch (TextAdventureException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        Decomposition decom = board.getDecompositions().get(Decomposition.createId(object1));
        if (decom != null) {
            if (decom.getResult1() instanceof Item)
                board.getInventery().put(decom.getResult1().getId(), (Item) decom.getResult1());
            else
                board.getBoard()[posX][posY].getObjects().put(decom.getResult1().getId(), decom.getResult1());

            if (decom.getResult2() instanceof Item)
                board.getInventery().put(decom.getResult2().getId(), (Item) decom.getResult2());
            else
                board.getBoard()[posX][posY].getObjects().put(decom.getResult2().getId(), decom.getResult2());

            board.getInventery().remove(item);
            board.getBoard()[posX][posY].getObjects().remove(item);

            return decom.getDescrip();
        }
        return null;

    }

    public Board getBoard() {
        return board;
    }

    private static Map<String, String> direction = new HashMap<>();

    static {
        direction.put("NW", "northwest");
        direction.put("N", "nort");

        direction.put("NE", "northeast");

        direction.put("E", "east");

        direction.put("SE", "southeast");

        direction.put("SW", "southwest");

        direction.put("W", "west");

        direction.put("S", "south");

    }

    private String resolveCoor(String dir) {
        if (dir == null)
            return ("Sorry, cannot move to this direction");
        switch (dir) {
        case "NW":
            if (this.posX == 0 || this.posY == 0)
                return ("Sorry, cannot move to this direction");
            this.posX = this.posX - 1;
            this.posY = this.posY - 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);

        case "N":
            if (this.posY == 0)
                return ("Sorry, cannot move to this direction");
            this.posY = this.posY - 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);
        case "NE":
            if (this.posX == this.board.getBoardWidth() - 1 || this.posY == 0)
                return ("Sorry, cannot move to this direction");
            this.posX = this.posX + 1;
            this.posY = this.posY - 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);
        case "E":
            if (this.posX == this.board.getBoardWidth() - 1)
                return ("Sorry, cannot move to this direction");
            this.posX = this.posX + 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);
        case "SE":
            if (this.posX == this.board.getBoardWidth() - 1 || this.posY == this.board.getBoardHeight() - 1)
                return ("Sorry, cannot move to this direction");
            this.posX = this.posX + 1;
            this.posY = this.posY + 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);
        case "S":
            if (this.posY == this.board.getBoardHeight() - 1)
                return ("Sorry, cannot move to this direction");
            this.posY = this.posY + 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);
        case "SW":
            if (this.posX == 0 || this.posY == this.board.getBoardHeight() - 1)
                return ("Sorry, cannot move to this direction");
            this.posX = this.posX - 1;
            this.posY = this.posY + 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);
        case "W":
            if (this.posX == 0)
                return ("Sorry, cannot move to this direction");
            this.posX = this.posX - 1;
            System.out.println("X : " + posX + " Y : " + posY);
            return "You go " + direction.get(dir);

        default:
            System.out.println("wrong direction");
            return ("Sorry, cannot move to this direction");
        }

    }

}
