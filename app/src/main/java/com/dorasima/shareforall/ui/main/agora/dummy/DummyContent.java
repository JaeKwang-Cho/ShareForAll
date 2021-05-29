package com.dorasima.shareforall.ui.main.agora.dummy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.dorasima.shareforall.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.nickname, item);
    }
    public static DummyItem getDummyItem(int pos){
        return ITEMS.get(pos);
    }

    private DummyItem createDummyItem(String id, Drawable profile, Drawable icon, String title, String content){
        return new DummyItem(id,profile,icon,title,content);
    }

    private String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class DummyItem implements Parcelable {
        public static final Creator<DummyItem> CREATOR = new Creator<DummyItem>() {
            @Override
            public DummyItem createFromParcel(Parcel in) {
                return new DummyItem(in);
            }

            @Override
            public DummyItem[] newArray(int size) {
                return new DummyItem[size];
            }
        };
        public final String nickname;
        public final Drawable icon;
        public final Drawable profile;
        public final String content;
        public final String title;

        public DummyItem(String nickname, Drawable profile, Drawable icon, String title, String content) {
            this.nickname = nickname;
            this.profile = profile;
            this.icon = icon;
            this.title = title;
            this.content = content;
        }

        protected DummyItem(Parcel in) {
            nickname = in.readString();
            title = in.readString();
            content = in.readString();
            Bitmap profileBit = (Bitmap)in.readParcelable(getClass().getClassLoader());
            if(profileBit !=null){
                profile = new BitmapDrawable(profileBit);
            }else{
                profile = null;
            }
            Bitmap iconBit = (Bitmap)in.readParcelable(getClass().getClassLoader());
            if(iconBit!=null){
                icon = new BitmapDrawable(iconBit);
            }else{
                icon = null;
            }
        }

        public Drawable getProfile(){return profile;}

        public Drawable getIcon() {
            return icon;
        }

        public String getNickname() {
            return nickname;
        }

        public String getContent() {
            return content;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(nickname);
            parcel.writeString(title);
            parcel.writeString(content);
            if(profile!=null){
                Bitmap profileBit = (Bitmap)((BitmapDrawable) profile).getBitmap();
                parcel.writeParcelable(profileBit, i);
            }else{
                parcel.writeParcelable(null, i);
            }

            if(icon != null){
                Bitmap iconBit = (Bitmap)((BitmapDrawable) icon).getBitmap();
                parcel.writeParcelable(iconBit, i);
            }else{
                parcel.writeParcelable(null, i);
            }


        }
    }
}