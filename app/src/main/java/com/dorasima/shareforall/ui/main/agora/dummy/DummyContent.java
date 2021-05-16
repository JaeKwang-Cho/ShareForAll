package com.dorasima.shareforall.ui.main.agora.dummy;

import android.content.Context;
import android.graphics.drawable.Drawable;

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

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private DummyItem createDummyItem(int position, Drawable icon) {
        return new DummyItem(icon,String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class DummyItem {
        public final Drawable icon;
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(Drawable icon, String id, String content, String details) {
            this.icon = icon;
            this.id = id;
            this.content = content;
            this.details = details;
        }

        public Drawable getIcon() {
            return icon;
        }

        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getDetails() {
            return details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}