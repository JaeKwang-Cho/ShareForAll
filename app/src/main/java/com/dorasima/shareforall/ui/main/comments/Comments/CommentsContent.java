package com.dorasima.shareforall.ui.main.comments.Comments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsContent {

    public static final List<CommentsItem> ITEMS = new ArrayList<CommentsItem>();
    public static final Map<String, CommentsItem> ITEM_MAP = new HashMap<String, CommentsItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createCommentsItem(i));
        }
    }

    private static void addItem(CommentsItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static CommentsItem createCommentsItem(int position) {
        return new CommentsItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class CommentsItem {
        public final String id;
        public final String content;
        public final String details;

        public CommentsItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}