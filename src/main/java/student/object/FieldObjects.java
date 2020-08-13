/*
 * Creation : 13 août 2020
 */
package student.object;

import java.util.HashMap;
import java.util.Map;

public class FieldObjects {

    private Map<String, BoardObject> objects = new HashMap<>();

    public Map<String, BoardObject> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, BoardObject> objects) {
        this.objects = objects;
    }

}
