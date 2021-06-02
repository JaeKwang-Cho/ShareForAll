package com.dorasima.shareforall.data.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgoraMessage implements Serializable{
    public static List<ServerAgoraItem> SERVER_ITEMS = new ArrayList<ServerAgoraItem>();

    public static void addItem(ServerAgoraItem item){
        SERVER_ITEMS.add(item);
    }

    public static class ServerAgoraItem{
        public String nickname;
        public byte[] icon;
        public byte[] profile;
        public String content;
        public String title;
        public int comments_index;

        public ServerAgoraItem(String nickname, byte[] icon, byte[] profile, String content, String title, int comments_index) {
            this.nickname = nickname;
            this.icon = icon;
            this.profile = profile;
            this.content = content;
            this.title = title;
            this.comments_index = comments_index;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public byte[] getIcon() {
            return icon;
        }

        public void setIcon(byte[] icon) {
            this.icon = icon;
        }

        public byte[] getProfile() {
            return profile;
        }

        public void setProfile(byte[] profile) {
            this.profile = profile;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getComments_index() {
            return comments_index;
        }

        public void setComments_index(int comments_index) {
            this.comments_index = comments_index;
        }
    }
}
