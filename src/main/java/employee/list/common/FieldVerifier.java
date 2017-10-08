package employee.list.common;

/**
 * Created by m.kuznecov on 02.10.2017.
 */
public class FieldVerifier {

    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() > 3;
    }
}
