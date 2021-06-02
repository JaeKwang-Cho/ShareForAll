package com.dorasima.shareforall.ui.main.comments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.ui.main.comments.Comments.CommentsContent.CommentsItem;

import java.util.List;


public class MyCommentsRecyclerViewAdapter extends RecyclerView.Adapter<MyCommentsRecyclerViewAdapter.ViewHolder> {

    private final List<CommentsItem> mValues;

    public MyCommentsRecyclerViewAdapter(List<CommentsItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mProfile.setImageDrawable(mValues.get(position).profile);
        holder.mNickName.setText(mValues.get(position).nickname);
        holder.mComments.setText(mValues.get(position).comments);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mProfile;
        public final TextView mNickName;
        public final TextView mComments;
        public CommentsItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mProfile = (ImageView) view.findViewById(R.id.comment_profile);
            mNickName = (TextView) view.findViewById(R.id.comment_nickname);
            mComments = (TextView) view.findViewById(R.id.comment_comments);
        }
    }
}