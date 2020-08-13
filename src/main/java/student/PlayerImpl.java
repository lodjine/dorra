/*
 * Creation : 13 août 2020
 */
package student;

import java.util.ArrayList;
import java.util.List;

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

    public PlayerImpl(int posX, int posY, int boardWidth, int boardHeight) {
        super();
        this.posX = posX;
        this.posY = posY;
        board = new Board(boardWidth, boardHeight);
    }

    @Override
    public String go(String direction) {
        String result = null;
        try {
            result = resolveCoor(direction);
        } catch (TextAdventureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String[] look() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < posX; i++) {

            for (int y = 0; y < posY; y++) {
                List<BoardObject> list = new ArrayList<>();
                if (board.getBoard()[i][y] != null && !board.getBoard()[i][y].getObjects().isEmpty())
                    list = (List<BoardObject>) board.getBoard()[i][y].getObjects().values();
                for (BoardObject boardObject : list) {
                    result.add(boardObject.getId() + "-" + boardObject.getDescription());
                }

            }

        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String[] inventory() {
        List<String> result = new ArrayList<>();
        List<Item> list = (List<Item>) board.getInventery().values();
        for (Item item : list) {
            result.add(item.getId() + "-" + item.getDescription());
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String take(String item) {
        if (board.getBoard()[posX][posY].getObjects().get(item) != null) {
            if (board.getBoard()[posX][posY].getObjects().get(item) instanceof Item)
                board.getInventery().put(item, (Item) board.getBoard()[posX][posY].getObjects().get(item));
            return " You pick up the " + item;

        }
        try {
            throw new TextAdventureException("Sorry you cannot pick up the " + item);
        } catch (TextAdventureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String drop(String item) {
        if (board.getInventery().containsKey(item)) {
            board.getInventery().remove(item);
            return " You drop the " + item;
        }

        try {
            throw new TextAdventureException("Sorry you cannot drop the " + item);
        } catch (TextAdventureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
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

    private String resolveCoor(String dir) throws TextAdventureException {
        switch (dir) {
        case "northwest":
            if (this.posX == 0 || this.posY == 0)
                throw new TextAdventureException("cannot move to this direction");
            this.posX = this.posX - 1;
            this.posY = this.posY - 1;

            break;

        case "north":
            if (this.posY == 0)
                throw new TextAdventureException("cannot move to this direction");
            this.posY = this.posY - 1;

            break;

        case "northeast":
            if (this.posX == this.board.getBoardWidth() || this.posY == 0)
                throw new TextAdventureException("cannot move to this direction");
            this.posX = this.posX + 1;
            this.posY = this.posY - 1;

            break;

        case "east":
            if (this.posX == this.board.getBoardWidth())
                throw new TextAdventureException("cannot move to this direction");
            this.posX = this.posX + 1;

            break;

        case "southeast":
            if (this.posX == this.board.getBoardWidth() || this.posY == this.board.getBoardHeight())
                throw new TextAdventureException("cannot move to this direction");
            this.posX = this.posX + 1;
            this.posY = this.posY + 1;

            break;

        case "”south":
            if (this.posY == this.board.getBoardHeight())
                throw new TextAdventureException("cannot move to this direction");
            this.posY = this.posY + 1;

            break;

        case "southwest":
            if (this.posX == 0 || this.posY == this.board.getBoardHeight())
                throw new TextAdventureException("cannot move to this direction");
            this.posX = this.posX + 1;
            this.posY = this.posY + 1;

            break;

        case "west":
            if (this.posX == 0)
                throw new TextAdventureException("cannot move to this direction");
            this.posX = this.posX - 1;

            break;

        default:
            break;
        }

        return "(" + posX + "," + posY + ")";

    }

}
