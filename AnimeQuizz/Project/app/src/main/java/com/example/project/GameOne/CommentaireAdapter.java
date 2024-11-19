package com.example.project.GameOne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.ArrayList;

public class CommentaireAdapter extends RecyclerView.Adapter<CommentaireAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CommentaireEtNote> commentaires;

    public CommentaireAdapter(Context context, ArrayList<CommentaireEtNote> commentaires) {
        this.context = context;
        this.commentaires = commentaires;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commentaire, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentaireEtNote commentaireEtNote = commentaires.get(position);
        holder.tvCommentaire.setText(commentaireEtNote.getCommentaire());
        holder.ratingBar.setRating(commentaireEtNote.getNote());
    }

    @Override
    public int getItemCount() {
        return commentaires.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCommentaire;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCommentaire = itemView.findViewById(R.id.tvCommentaire);
            ratingBar = itemView.findViewById(R.id.itemRatingBar);
        }
    }
}
