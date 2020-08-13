/*
 * Creation : 13 août 2020
 */
package student.mutation;

import student.object.BoardObject;

public class Transformation {
    private String id;
    private String descrip;
    private BoardObject in1;
    private BoardObject in2;

    private BoardObject out1;
    private BoardObject out2;

    public Transformation(String descrip, BoardObject in1, BoardObject in2, BoardObject out1, BoardObject out2) {
        super();
        this.descrip = descrip;
        this.in1 = in1;
        this.in2 = in2;
        this.out1 = out1;
        this.out2 = out2;
        this.id = createId(in1, in2);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

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

    public BoardObject getOut1() {
        return out1;
    }

    public void setOut1(BoardObject out1) {
        this.out1 = out1;
    }

    public BoardObject getOut2() {
        return out2;
    }

    public void setOut2(BoardObject out2) {
        this.out2 = out2;
    }

    public static String createId(BoardObject in1, BoardObject in2) {
        return in1.getId() + "+" + in2.getId();
    }

    public BoardObject getResult1() {
        return out1;
    }

    public BoardObject getResult2() {
        return out2;
    }
}
