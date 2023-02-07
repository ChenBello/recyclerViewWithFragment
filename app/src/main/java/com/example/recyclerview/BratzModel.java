package com.example.recyclerview;

import android.graphics.Bitmap;

public class BratzModel {
    String bratzName;
    String bratzNickname;
    String bratzCharacterType;
    Bitmap image;
    String description;

    public BratzModel(String bratzName, String bratzCharacterType, String bratzNickname, Bitmap image, String description) {
        this.bratzName = bratzName;
        this.bratzCharacterType = bratzCharacterType;
        this.bratzNickname = bratzNickname;
        this.image = image;
    }

    public String getBratzName() {
        return bratzName;
    }

    public String getBratzCharacterType() {
        return bratzCharacterType;
    }

    public String getBratzNickname() {
        return bratzNickname;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
