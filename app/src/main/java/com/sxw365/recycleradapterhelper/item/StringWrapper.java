package com.sxw365.recycleradapterhelper.item;

import com.sxw365.recyclerhelper.adapter.RecyclerData;

/**
 * Created by cai on 2017/9/29.
 */

public class StringWrapper implements RecyclerData {

    private String content;

    public StringWrapper(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int getType() {
        return 0;
    }
}
