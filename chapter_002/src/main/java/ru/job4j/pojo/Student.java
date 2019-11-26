package ru.job4j.pojo;

import java.util.Date;

public class Student {

    private String name;
    private String group;
    private Date matriculationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getMatriculationDate() {
        return matriculationDate;
    }

    public void setMatriculationDate(Date matriculationDate) {
        this.matriculationDate = matriculationDate;
    }

}
