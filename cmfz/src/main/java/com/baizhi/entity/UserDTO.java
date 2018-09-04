package com.baizhi.entity;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String name;
    private Integer value;

    public UserDTO() {
    }

    public UserDTO(String name, Integer value) {

        this.name = name;
        this.value = value;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
