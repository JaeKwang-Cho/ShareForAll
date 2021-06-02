package com.dorasima.shareforall.data.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgoraMessage implements Serializable{
    public static final List<serverAgoraItem> SERVER_ITEMS = new ArrayList<serverAgoraItem>();

    public static void addItem(serverAgoraItem item){
        SERVER_ITEMS.add(item);
    }

    public static class serverAgoraItem{
        public String nickname;
        public byte[] icon;
        public byte[] profile;
        public String content;
        public String title;
        public int comments_index;

        public serverAgoraItem(String nickname, byte[] icon, byte[] profile, String content, String title, int comments_index) {
            this.nickname = nickname;
            this.icon = icon;
            this.profile = profile;
            this.content = content;
            this.title = title;
            this.comments_index = comments_index;
        }
    }
}
