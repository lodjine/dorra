/*
 * Creation : 13 août 2020
 */
package student.mutation;

import student.object.BoardObject;

public class Composition {
    private String id;

    public Composition(BoardObject in1, BoardObject in2, BoardObject out, String descrip) {
        super();
        this.in1 = in1;
        this.in2 = in2;
        this.out = out;
        this.descrip = descrip;

        id = createId(in1, in2);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private BoardObject in1;
    private BoardObject in2;

    private BoardObject out;
    private String descrip;

    public BoardObject getIn1() {
        return in1;
    }

    public void setIn1(BoardObject in1) {
        this.in1 = in1;
    }

    public BoardObject getIn2() {
        return in2;
    }

    public void setIn2(BoardObject in2) {
        this.in2 = in2;
    }

    public BoardObject getOut() {
        return out;
    }

    public void setOut(BoardObject out) {
        this.out = out;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public static String createId(BoardObject in1, BoardObject in2) {
        return in1.getId() + "+" + in2.getId();
    }

    public BoardObject getResult() {
        return out;
    }
}
