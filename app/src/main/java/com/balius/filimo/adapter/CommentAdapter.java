package com.balius.filimo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.model.singelvideo.comment.UserComment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentVH> {

    Context context;
    List<UserComment> userCommentList;
    LayoutInflater inflater;

    public CommentAdapter(Context context ,List<UserComment> userCommentList) {
        this.context=context;
        this.userCommentList=userCommentList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.comment_row,null);

        return new CommentVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentVH holder, int position) {
        UserComment userComment = userCommentList.get(position);

        holder.txt_username.setText(userComment.getUserName());
        holder.txt_comment.setText(userComment.getCommentText());

    }

    @Override
    public int getItemCount() {
        return userCommentList.size();
    }

    class CommentVH extends RecyclerView.ViewHolder{
        AppCompatTextView txt_username;
        AppCompatTextView txt_comment;
        public CommentVH(@NonNull View itemView) {
            super(itemView);

            txt_username = itemView.findViewById(R.id.txt_user_name);
            txt_comment = itemView.findViewById(R.id.txt_comment);

        }
    }
}
