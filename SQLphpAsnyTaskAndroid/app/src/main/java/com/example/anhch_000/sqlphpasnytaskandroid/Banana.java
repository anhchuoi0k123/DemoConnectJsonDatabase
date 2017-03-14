package com.example.anhch_000.sqlphpasnytaskandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by anhch_000 on 04/03/2017.
 */

public class Banana {
    private int id;
    private String name;
    private String content;
    private Bitmap iamge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getIamge() {
        if (iamge == null) return BitmapFactory.decodeResource(App.getContext().getResources() ,R.drawable.img_default);
        return iamge;
    }

    public void setIamge(Bitmap iamge) {
        this.iamge = iamge;
    }
}
