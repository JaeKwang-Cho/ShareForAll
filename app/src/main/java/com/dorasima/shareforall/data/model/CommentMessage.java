package com.dorasima.shareforall.data.model;

import android.graphics.drawable.Drawable;

import com.dorasima.shareforall.ui.main.comments.Comments.CommentsContent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommentMessage implements Serializable {
    public static final List<ServerCommentsItem> COMMENTS_ITEMS = new ArrayList<ServerCommentsItem>();

    public static void addItem(ServerCommentsItem item){
        COMMENTS_ITEMS.add(item);
    }

    public static class ServerCommentsItem {
        public final Drawable profile;
        public final String nickname;
        public final String comments;

        public ServerCommentsItem(Drawable profile, String nickname, String comments) {
            this.profile = profile;
            this.nickname = nickname;
            this.comments = comments;
        }

        public ServerCommentsItem(ServerCommentsItem serverCommentsItem){
            this.profile = serverCommentsItem.profile;
            this.comments = serverCommentsItem.nickname;
            this.nickname = serverCommentsItem.nickname;
        }
    }
}
