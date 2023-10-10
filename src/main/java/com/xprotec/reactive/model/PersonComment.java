package com.xprotec.reactive.model;

public class PersonComment {
    private Person person;
    private Comment comment;

    public PersonComment(Person person, Comment comment) {
        this.person = person;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PersonComment{" +
                "person=" + person +
                ", comment=" + comment +
                '}';
    }
}
