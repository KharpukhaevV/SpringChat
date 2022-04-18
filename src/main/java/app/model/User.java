package app.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class User {
    @NotEmpty
    private String name;
    @Min(12)
    @NotEmpty
    private String age;

    public User() {
    }

    public User(String age, String name) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "age=" + age + " name=" + name;
    }
}
