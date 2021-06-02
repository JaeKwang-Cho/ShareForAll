package com.dorasima.shareforall.ui.main.agora;

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
import android.widget.Toast;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.model.AgoraMessage;
import com.dorasima.shareforall.ui.main.MainActivity;
import com.dorasima.shareforall.ui.main.agora.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import static com.dorasima.shareforall.ui.main.agora.dummy.DummyContent.AGORA_ITEMS;

/**
 * A fragment representing a list of Items.
 */
public class AgoraFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public static Integer IDs;
    public Drawable defIcon;
    private Context context;

    private MyItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private RecyclerView recyclerView;

    private AgoraMessage.ServerAgoraItem newAgoraItem = null;


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AgoraFragment newInstance(int columnCount) {
        AgoraFragment fragment = new AgoraFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        defIcon = getResources().getDrawable(R.drawable.intro);
        IDs = 0;

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        GetAgoraList thread = new GetAgoraList();
        thread.start();
        try {
            synchronized(thread){
                thread.join();
            }
        }
        catch (InterruptedException e) {
            getActivity().finish();
        }
        AGORA_ITEMS = new ArrayList<>();
        if(AgoraMessage.SERVER_ITEMS != null){
            for(AgoraMessage.ServerAgoraItem item : AgoraMessage.SERVER_ITEMS){
                Drawable icon = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(item.getIcon(), 0, item.getProfile().length));
                Drawable profile = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(item.getProfile(), 0, item.getProfile().length));
                AGORA_ITEMS.add(new DummyContent.DummyItem(item.nickname,icon,profile,item.title,item.content,item.comments_index));
            }
        }
        /*
        for(int i = 0;i<10;i++){
            DummyContent.DummyItem item = new DummyContent.DummyItem(MainActivity.loggedInUser.getNickName(), defIcon, defIcon, "test", "test details");
            DummyContent.ITEMS.add(item);
            IDs++;
        }
        */
        context = getActivity();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_agora_list, container, false);

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
            itemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(AGORA_ITEMS);
            itemRecyclerViewAdapter.setOnItemClickListener(new MyItemRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int pos) {
                    Intent intent = new Intent(context,ArticleActivity.class);
                    intent.putExtra("article",DummyContent.getDummyItem(pos));
                    intent.putExtra("loggedUser",((MainActivity)getActivity()).getLoggedInUserInfo());
                    startActivity(intent);
                    //Toast toast = Toast.makeText(context,pos+ " clicked",Toast.LENGTH_SHORT);
                    //toast.show();
                    // todo: item click event
                }
            });
            recyclerView.setAdapter(itemRecyclerViewAdapter);
        }
        return view;
    }

    public void refresh(){
        boolean isAdded = true;
        GetNewAgoraArticle thread = new GetNewAgoraArticle();
        thread.start();
        try {
            synchronized(thread){
                thread.join();
            }
        }
        catch (InterruptedException e) {
            isAdded = false;
        }

        if(isAdded && newAgoraItem != null){
            Drawable icon = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(newAgoraItem.getIcon(), 0, newAgoraItem.getProfile().length));
            Drawable profile = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(newAgoraItem.getProfile(), 0, newAgoraItem.getProfile().length));
            AGORA_ITEMS.add(new DummyContent.DummyItem(newAgoraItem.nickname,icon,profile,newAgoraItem.title,newAgoraItem.content,newAgoraItem.comments_index));
        }

        itemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(AGORA_ITEMS);
        itemRecyclerViewAdapter.setOnItemClickListener(new MyItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(context,ArticleActivity.class);
                intent.putExtra("article",DummyContent.getDummyItem(pos));
                startActivity(intent);
                // todo: item click event
            }
        });
        recyclerView.setAdapter(itemRecyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    class GetAgoraList extends Thread{
        @Override
        public void run() {
            super.run();
            // todo: Server 에 있는 AgoraList 가져오기
            AgoraMessage.SERVER_ITEMS = null;
        }
    }

    class GetNewAgoraArticle extends Thread{
        @Override
        public void run() {
            super.run();
            // todo: 새로 들어온 agora article 가져오기
            newAgoraItem = null;
        }
    }
}