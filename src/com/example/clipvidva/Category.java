package com.example.clipvidva;

/**
 * Created by Vee on 7/9/2556.
 */
public class Category {
    private int id;
    private String name;
    private String img;

    public Category() {
    }

    public Category(int id, String name, String img) {
        this.img = img;
        this.name = name;
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
