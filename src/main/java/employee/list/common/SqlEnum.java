package employee.list.common;

/**
 * Created by m.kuznecov on 04.10.2017.
 */
public enum  SqlEnum {
    SELECTALL("SELECT * FROM employee"),
    SELECTBIID("SELECT * FROM employee WHERE id=?"),
    INSERT("INSERT INTO employee( firstname,lastname,birthdate) VALUES(?,?,?)"),
    DELETEBYID("DELETE FROM employee WHERE id = ?"),
    UPDATE("UPDATE employee SET firstname = ?, lastname = ?,birthdate = ? where id =?");
    private final String text;


    private SqlEnum(final String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
