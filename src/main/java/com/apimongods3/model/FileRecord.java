package com.apimongods3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FileRecord {
    @Id
    private String id;
    private String filed1;
    private String filed2;

    public FileRecord(String filed1, String filed2) {
        this.filed1 = filed1;
        this.filed2 = filed2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFiled1() {
        return filed1;
    }

    public void setFiled1(String filed1) {
        this.filed1 = filed1;
    }

    public String getFiled2() {
        return filed2;
    }

    public void setFiled2(String filed2) {
        this.filed2 = filed2;
    }
}
