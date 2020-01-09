package ru.job4j.generics;

public class Role extends Base {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public Role(String id, String roleName) {
        super(id);
        this.roleName = roleName;
    }
}
