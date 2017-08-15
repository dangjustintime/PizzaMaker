package com.example.pizzamaker.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;

/**
 * Created by Justin Dang on 8/8/2017.
 */

public class Topping implements Parcelable {
    private int id;
    private String name;
    private String imageUrl;
    private @ColorInt int color;

    public Topping(int id, String name, String imageUrl,@ColorInt int color) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.color = color;
    }

    protected Topping(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.readString();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Topping> CREATOR = new Creator<Topping>() {
        @Override
        public Topping createFromParcel(Parcel in) {
            return new Topping(in);
        }

        @Override
        public Topping[] newArray(int size) {
            return new Topping[size];
        }
    };

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public @ColorInt int getColor() {
        return color;
    }

    public void setColor(@ColorInt int color) { this.color = color; }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Topping) {
            Topping other = (Topping) obj;
            return this.id == other.id
                    && this.name.equals(other.name)
                    && this.color == other.color;
        }
        return false;
    }
}
