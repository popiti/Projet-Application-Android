package com.example.project.GameOne;

import java.io.Serializable;

public class CommentaireEtNote implements Serializable {
    private String commentaire;
    private float note;

    public CommentaireEtNote(String commentaire, float note) {
        this.commentaire = commentaire;
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public float getNote() {
        return note;
    }
}
