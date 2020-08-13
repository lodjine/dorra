/*
 * Creation : 13 août 2020
 */
package student.mutation;

import student.object.BoardObject;

public class Decomposition {

    private String id;
    private String descrip;
    private BoardObject in;
    private BoardObject out1;
    private BoardObject out2;

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

    public BoardObject getIn() {
        return in;
    }

    public void setIn(BoardObject in) {
        this.in = in;
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

    public Decomposition(String descrip, BoardObject in, BoardObject out1, BoardObject out2) {
        super();
        this.descrip = descrip;
        this.in = in;
        this.out1 = out1;
        this.out2 = out2;
        this.id = createId(in);
    }

    public static String createId(BoardObject in) {
        return in.getId();
    }

    public BoardObject getResult1() {
        return out1;
    }

    public BoardObject getResult2() {
        return out2;
    }

}
