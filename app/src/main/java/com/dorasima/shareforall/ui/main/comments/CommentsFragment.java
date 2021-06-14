package com.dorasima.shareforall.ui.main.comments;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.model.AgoraMessage;
import com.dorasima.shareforall.data.model.CommentMessage;
import com.dorasima.shareforall.ui.main.agora.AgoraFragment;
import com.dorasima.shareforall.ui.main.agora.dummy.DummyContent;
import com.dorasima.shareforall.ui.main.comments.Comments.CommentsContent;

import java.util.ArrayList;

import static com.dorasima.shareforall.ui.main.agora.dummy.DummyContent.AGORA_ITEMS;
import static com.dorasima.shareforall.ui.main.comments.Comments.CommentsContent.COMMENTS;

/**
 * A fragment representing a list of Items.
 */
public class CommentsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private RecyclerView recyclerView;
    private MyCommentsRecyclerViewAdapter adapter;

    private static String[] name = {"Ace", "Barbie", "Champ", "Diallo", "Champ", "Favian", "Grace", "Harley"};
    private static Drawable[] profiles;
    private static String[] comments = {"I don't trust them", "that's taking too long",
                                        "my wife is better than you, Dude", "grandfather? I will defeat you cleanly.",
                                        "Haha, That's funny kids","Just for trailer", "Hmm...", "you're being fooled again"};

    private CommentMessage.ServerCommentsItem newServerComments = null;

    public CommentsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        GetCommentsList thread = new GetCommentsList();
        thread.start();
        try {
            synchronized(thread){
                thread.join();
        }
        }
        catch (InterruptedException e) {
            getActivity().finish();
        }
        COMMENTS = new ArrayList<>();
        if(CommentMessage.COMMENTS_ITEMS != null){
            for(CommentMessage.ServerCommentsItem item : CommentMessage.COMMENTS_ITEMS){
                Drawable profile = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(item.profile, 0, item.profile.length));
                COMMENTS.add(new CommentsContent.CommentsItem(profile,item.nickname,item.comments));
            }
        }

        profiles = new Drawable[8];
        profiles[0] = getResources().getDrawable(R.drawable.p1);
        profiles[1] = getResources().getDrawable(R.drawable.p2);
        profiles[2] = getResources().getDrawable(R.drawable.p6);
        profiles[3] = getResources().getDrawable(R.drawable.p4);
        profiles[4] = getResources().getDrawable(R.drawable.p6);
        profiles[5] = getResources().getDrawable(R.drawable.p3);
        profiles[6] = getResources().getDrawable(R.drawable.p7);
        profiles[7] = getResources().getDrawable(R.drawable.p8);
        for (int i = 0; i < 8; i++) {
            CommentsContent.CommentsItem item = new CommentsContent.CommentsItem(profiles[i],name[i],comments[i]);
            CommentsContent.COMMENTS.add(item);
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
            else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyCommentsRecyclerViewAdapter(COMMENTS);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public void refresh(){
        boolean isAdded =true;

        GetNewComment thread = new GetNewComment();
        thread.start();
        try {synchronized(thread){
            thread.join();
        }
        }
        catch (InterruptedException e) {
            isAdded = false;
        }
        if(isAdded && newServerComments!=null){
            Drawable profile = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(newServerComments.profile, 0, newServerComments.profile.length));
            COMMENTS.add(new CommentsContent.CommentsItem( profile,newServerComments.nickname,newServerComments.comments));
        }

        adapter = new MyCommentsRecyclerViewAdapter(COMMENTS);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    class GetCommentsList extends Thread{
        @Override
        public void run() {
            super.run();
            // todo: Server 에 있는 AgoraList 가져오기
            CommentMessage.COMMENTS_ITEMS = null;
        }
    }

    class GetNewComment extends Thread{
        @Override
        public void run() {
            super.run();
            // todo: 새로 들어온 agora article 가져오기
            newServerComments = null;
        }
    }
}
