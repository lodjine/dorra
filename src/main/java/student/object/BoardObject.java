/*
 * Creation : 13 août 2020
 */
package student.object;

public class BoardObject {

    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BoardObject(String id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

}
