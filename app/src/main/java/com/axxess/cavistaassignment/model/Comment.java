package com.axxess.cavistaassignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Comment")
public class Comment {
    @PrimaryKey
    @NotNull
    String id;

    String comment;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public String getId() {
        return id;
    }
}
