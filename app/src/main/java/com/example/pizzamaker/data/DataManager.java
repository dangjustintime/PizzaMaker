package com.example.pizzamaker.data;

import android.content.Context;
import android.util.Log;

import com.example.pizzamaker.R;
import com.example.pizzamaker.model.Topping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin Dang on 8/10/2017.
 */

public class DataManager {
    private static final String TAG = DataManager.class.getSimpleName();

    private static DataManager instance;
    private List<Topping> allToppings = new ArrayList<>();

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private DataManager() {
    }

    public void init(Context context) {
        String[] toppingsNames = context.getResources().getStringArray(R.array.topping_names);
        String[] imageUrls = context.getResources().getStringArray(R.array.toppings_urls);
        int[] toppingsColors = context.getResources().getIntArray(R.array.toppings_colors);

        if (toppingsNames.length != toppingsColors.length) {
            Log.e(TAG, "Toppings names and colors list sizes unequal. Failed to parse all toppings");
            return;
        }

        for (int i = 0; i < toppingsNames.length; i++) {
            String name = toppingsNames[i];
            String imageUrl = imageUrls[i];
            int color = toppingsColors[i];
            Topping topping = new Topping(i, name, imageUrl, color);
            allToppings.add(topping);
        }
    }

    public List<Topping> getAllToppings() {
        return allToppings;
    }
}
