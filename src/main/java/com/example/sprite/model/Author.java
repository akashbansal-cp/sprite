package com.example.sprite.model;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "authors")
public class Author {


    private ObjectId _id;


    private String name;
    private String addL1;
    private String addL2;

    public Author(){};

    public Author(String name, String addL1, String addL2) {
        this.name=name;
        this.addL1=addL1;
        this.addL2=addL2;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        if (name != null && addL1 != null && addL2 != null) return true;
        return false;
    }

    public ObjectId getAuthorId() {
        return _id;
    }

    public void setAuthorId(ObjectId _id) {
        this._id = _id;
    }


    public String getAddL1() {
        return addL1;
    }

    public void setAddL1(String addL1) {
        this.addL1 = addL1;
    }


    public String getAddL2() {
        return addL2;
    }

    public void setAddL2(String addL2) {
        this.addL2 = addL2;
    }

    List<String> books = new ArrayList<String>(); // book id only

    public void print() {
        System.out.println("Name : " + name);
        System.out.println("addL1 : " + addL1);
        System.out.println("addL2 : " + addL2);
        System.out.print("Books : ");
        books.forEach(e -> {
            System.out.print(e);
        });
        System.out.println();
    }
}
