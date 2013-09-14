package com.example.clipvidva;

/**
 * Created by Vee on 8/9/2556.
 */
public class Video {
    private int id;
    private String name;
    private String file;
    private int subject_id;

    public Video() {
    }

    public Video(int id, String name, String file, int subject_id) {
        this.id = id;
        this.name = name;
        this.file = file;
        this.subject_id = subject_id;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String toString() {
        return this.name;
    }
}
