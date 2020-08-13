/*
 * Creation : 13 août 2020
 */
package student.object;

import java.util.HashMap;
import java.util.Map;

import student.mutation.Composition;
import student.mutation.Decomposition;
import student.mutation.Transformation;

public class Board {

    private int boardWidth;
    private int boardHeight;
    private FieldObjects[][] board;

    private Map<String, BoardObject> sceneryTypes = new HashMap<>();
    private Map<String, Item> itemTypes = new HashMap<>();
    private Map<String, Item> inventery = new HashMap<>();

    private Map<String, Composition> compositions = new HashMap<>();
    private Map<String, Decomposition> decompositions = new HashMap<>();
    private Map<String, Transformation> transformation = new HashMap<>();

    public Board(int boardWidth, int boardHeight) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        initBoard();

    }

    private void initBoard() {
        this.board = new FieldObjects[boardWidth][boardHeight];
    }

    public void placeItem(int x, int y, Item item) {
        this.board[x][y].getObjects().put(item.getId(), item);
    }

    public void placeBoardObject(int x, int y, BoardObject boardObject) {
        this.board[x][y].getObjects().put(boardObject.getId(), boardObject);
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public FieldObjects[][] getBoard() {
        return board;
    }

    public void setBoard(FieldObjects[][] board) {
        this.board = board;
    }

    public Map<String, BoardObject> getSceneryTypes() {
        return sceneryTypes;
    }

    public void setSceneryTypes(Map<String, BoardObject> sceneryTypes) {
        this.sceneryTypes = sceneryTypes;
    }

    public Map<String, Item> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(Map<String, Item> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public Map<String, Item> getInventery() {
        return inventery;
    }

    public void setInventery(Map<String, Item> inventery) {
        this.inventery = inventery;
    }

    public Map<String, Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(Map<String, Composition> compositions) {
        this.compositions = compositions;
    }

    public Map<String, Decomposition> getDecompositions() {
        return decompositions;
    }

    public void setDecompositions(Map<String, Decomposition> decompositions) {
        this.decompositions = decompositions;
    }

    public Map<String, Transformation> getTransformation() {
        return transformation;
    }

    public void setTransformation(Map<String, Transformation> transformation) {
        this.transformation = transformation;
    }

}
