package com.dorasima.shareforall.ui.main.comments.Comments;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsContent {

    public static final List<CommentsItem> ITEMS = new ArrayList<CommentsItem>();
    public static final Map<String, CommentsItem> ITEM_MAP = new HashMap<String, CommentsItem>();


    public static void addItem(CommentsItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.nickname, item);
    }

    public static CommentsItem createCommentsItem(Drawable profile ,final String nickName, final String comments) {
        return new CommentsItem(profile, nickName, comments);
    }

    public static class CommentsItem {
        public final Drawable profile;
        public final String nickname;
        public final String comments;

        public CommentsItem(Drawable profile, String nickname, String comments) {
            this.profile = profile;
            this.nickname = nickname;
            this.comments = comments;
        }

        @Override
        public String toString() {
            return comments;
        }
    }
}