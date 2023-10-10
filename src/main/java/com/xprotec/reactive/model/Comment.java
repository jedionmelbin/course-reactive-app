package com.xprotec.reactive.model;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    List<String> comments;

    public Comment() {
        this.comments = new ArrayList<>();
    }

    public void addComments(String comment) {
        this.comments.add(comment);
    }
}
