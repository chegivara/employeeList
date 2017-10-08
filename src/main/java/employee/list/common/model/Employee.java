package employee.list.common.model;

import java.util.Date;

/**
 * Created by m.kuznecov on 02.10.2017.
 */
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Employee(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Employee() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public static Builder newBuilder() {
        return (new Employee()).new Builder();
    }

    public class Builder {
        private Builder() {
        }
        public Builder setId(Integer id) {
            Employee.this.setId(id);
            return this;
        }
        public Builder setLastName(String lastName) {
            Employee.this.setLastName(lastName);
            return this;
        }

        public Builder setFirstName(String firstName) {
            Employee.this.setFirstName(firstName);
            return this;
        }
        public Builder setBirthDate(Date birthDate) {
            Employee.this.setBirthDate(birthDate);
            return this;
        }
        public Employee build() {
            return Employee.this;
        }
    }
}
