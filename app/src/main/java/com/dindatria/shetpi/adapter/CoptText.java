package com.dindatria.shetpi.adapter;

import android.content.ClipboardManager;
import android.content.Context;

public class CoptText {
    public  String clipboardManager(Context context, String text) {

        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            clipboardManager.setText(text);
        }

        return text;
    }

}



