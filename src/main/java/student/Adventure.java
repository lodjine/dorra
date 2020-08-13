/*
 * Creation : 13 août 2020
 */
package student;

import ias.Player;
import ias.TextAdventure;
import ias.TextAdventureException;
import student.mutation.Composition;
import student.mutation.Decomposition;
import student.mutation.Transformation;
import student.object.BoardObject;
import student.object.Item;

public class Adventure implements TextAdventure {

    private PlayerImpl player;
    private String name;

    private int boardWidth;
    private int boardHeight;

    public Adventure(String name, int boardWidth, int boardHeight) {
        super();
        this.name = name;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

    }

    @Override
    public void addItemType(String id, String description) throws TextAdventureException {
        if (player.getBoard().getSceneryTypes().get(id) != null || player.getBoard().getItemTypes().get(id) != null)
            throw new TextAdventureException("this object is allready exist");
        player.getBoard().getItemTypes().put(id, new Item(id, description));

    }

    @Override
    public void addSceneryType(String id, String description) throws TextAdventureException {
        if (player.getBoard().getSceneryTypes().get(id) != null || player.getBoard().getItemTypes().get(id) != null)
            throw new TextAdventureException("this object is allready exist");
        player.getBoard().getSceneryTypes().put(id, new BoardObject(id, description));
    }

    @Override
    public void placeItem(String type, int x, int y) throws TextAdventureException {
        if (player.getBoard().getSceneryTypes().get(type) != null)
            player.getBoard().placeBoardObject(x, y, player.getBoard().getSceneryTypes().get(type));

        if (player.getBoard().getItemTypes().get(type) != null)
            player.getBoard().placeItem(x, y, player.getBoard().getItemTypes().get(type));

    }

    @Override
    public void addComposition(String in1, String in2, String out, String description) throws TextAdventureException {

        BoardObject obin1 = null;
        BoardObject obin2 = null;
        BoardObject obout = null;
        if (!(player.getBoard().getSceneryTypes().containsKey(in1) || player.getBoard().getItemTypes().containsKey(in1)))
            throw new TextAdventureException("object not exist :" + in1);
        obin1 = player.getBoard().getSceneryTypes().containsKey(in1) ? player.getBoard().getSceneryTypes().get(in1)
                : player.getBoard().getItemTypes().get(in1);
        if (!(player.getBoard().getSceneryTypes().containsKey(in2) || player.getBoard().getItemTypes().containsKey(in2)))
            throw new TextAdventureException("object not exist :" + in2);
        obin2 = player.getBoard().getSceneryTypes().containsKey(in2) ? player.getBoard().getSceneryTypes().get(in2)
                : player.getBoard().getItemTypes().get(in2);
        if (!(player.getBoard().getSceneryTypes().containsKey(out) || player.getBoard().getItemTypes().containsKey(out)))
            throw new TextAdventureException("object not exist :" + out);
        obout = player.getBoard().getSceneryTypes().containsKey(out) ? player.getBoard().getSceneryTypes().get(out)
                : player.getBoard().getItemTypes().get(out);

        Composition comp = new Composition(obin1, obin2, obout, description);
        Decomposition decom = new Decomposition("reverse " + description, obout, obin1, obin2);
        player.getBoard().getCompositions().put(comp.getId(), comp);
        player.getBoard().getDecompositions().put(decom.getId(), decom);

    }

    @Override
    public void addDecomposition(String in, String out1, String out2, String description) throws TextAdventureException {
        BoardObject obIn = null;
        BoardObject obOut2 = null;
        BoardObject obOut1 = null;
        if (!(player.getBoard().getSceneryTypes().containsKey(in) || player.getBoard().getItemTypes().containsKey(in)))
            throw new TextAdventureException("object not exist :" + in);
        obIn = player.getBoard().getSceneryTypes().containsKey(in) ? player.getBoard().getSceneryTypes().get(in)
                : player.getBoard().getItemTypes().get(in);
        if (!(player.getBoard().getSceneryTypes().containsKey(out1) || player.getBoard().getItemTypes().containsKey(out1)))
            throw new TextAdventureException("object not exist :" + out1);
        obOut1 = player.getBoard().getSceneryTypes().containsKey(out1) ? player.getBoard().getSceneryTypes().get(out1)
                : player.getBoard().getItemTypes().get(out1);
        if (!(player.getBoard().getSceneryTypes().containsKey(out2) || player.getBoard().getItemTypes().containsKey(out2)))
            throw new TextAdventureException("object not exist :" + out2);
        obOut2 = player.getBoard().getSceneryTypes().containsKey(out2) ? player.getBoard().getSceneryTypes().get(out2)
                : player.getBoard().getItemTypes().get(out2);

        Decomposition decom = new Decomposition(description, obIn, obOut1, obOut2);

        Composition comp = new Composition(obOut1, obOut2, obIn, "reverse " + description);

        player.getBoard().getCompositions().put(comp.getId(), comp);
        player.getBoard().getDecompositions().put(decom.getId(), decom);

    }

    @Override
    public void addTransformation(String in1, String in2, String out1, String out2, String description) throws TextAdventureException {
        BoardObject obIn1 = null;
        BoardObject obIn2 = null;
        BoardObject obOut2 = null;
        BoardObject obOut1 = null;

        if (!(player.getBoard().getSceneryTypes().containsKey(out1) || player.getBoard().getItemTypes().containsKey(out1)))
            throw new TextAdventureException("object not exist :" + out1);
        obOut1 = player.getBoard().getSceneryTypes().containsKey(out1) ? player.getBoard().getSceneryTypes().get(out1)
                : player.getBoard().getItemTypes().get(out1);
        if (!(player.getBoard().getSceneryTypes().containsKey(out2) || player.getBoard().getItemTypes().containsKey(out2)))
            throw new TextAdventureException("object not exist :" + out2);
        obOut2 = player.getBoard().getSceneryTypes().containsKey(out2) ? player.getBoard().getSceneryTypes().get(out2)
                : player.getBoard().getItemTypes().get(out2);

        if (!(player.getBoard().getSceneryTypes().containsKey(in1) || player.getBoard().getItemTypes().containsKey(in1)))
            throw new TextAdventureException("object not exist :" + in1);
        obIn1 = player.getBoard().getSceneryTypes().containsKey(in1) ? player.getBoard().getSceneryTypes().get(in1)
                : player.getBoard().getItemTypes().get(in1);
        if (!(player.getBoard().getSceneryTypes().containsKey(in2) || player.getBoard().getItemTypes().containsKey(in2)))
            throw new TextAdventureException("object not exist :" + in2);
        obIn2 = player.getBoard().getSceneryTypes().containsKey(in2) ? player.getBoard().getSceneryTypes().get(in2)
                : player.getBoard().getItemTypes().get(in2);

        Transformation transformation1 = new Transformation(description, obIn1, obIn2, obOut1, obOut2);
        Transformation transformation2 = new Transformation("reverse " + description, obOut1, obOut2, obIn1, obIn2);
        player.getBoard().getTransformation().put(transformation1.getId(), transformation1);
        player.getBoard().getTransformation().put(transformation2.getId(), transformation2);

    }

    @Override
    public Player startGame(int x, int y) throws TextAdventureException {
        this.player = new PlayerImpl(x, y, this.boardWidth, this.boardHeight);
        return this.player;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
