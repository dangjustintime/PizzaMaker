package com.example.pizzamaker.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import java.util.ArrayList;

/**
 * Created by Justin Dang on 8/9/2017.
 */

public class Pizza implements Parcelable {
    //define list of accepted constants and declare Size annotation
    @IntDef({SIZE_SMALL, SIZE_MEDIUM, SIZE_LARGE})
    public @interface Size {}
    //declare constants
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_LARGE = 2;

    @Pizza.Size private int size;
    private ArrayList<Topping> toppings;

    public Pizza() {
        size = SIZE_SMALL;
        toppings = new ArrayList<>();
    }

    protected Pizza(Parcel in) {
        //noinspection WrongConstant
        size = in.readInt();
        toppings = in.createTypedArrayList(Topping.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size);
        dest.writeTypedList(toppings);
    }

    public @Pizza.Size int getSize() { return size; }

    public void setSize(@Pizza.Size int size) { this.size = size; }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public ArrayList<Topping> getToppings() { return toppings; }

    public void addToppings(Topping toppings) { this.toppings.add(toppings); }

    public void removeToppings(Topping toppings) { this.toppings.remove(toppings); }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

}
