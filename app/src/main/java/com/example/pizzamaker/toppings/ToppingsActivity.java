package com.example.pizzamaker.toppings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.pizzamaker.R;
import com.example.pizzamaker.data.DataManager;
import com.example.pizzamaker.model.Topping;

import java.util.ArrayList;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class ToppingsActivity extends AppCompatActivity {
    public static final String RESULT_SELECTED_TOPPINGS = "selectedToppings";
    private static final String EXTRA_TOPPINGS = "selectedToppings";
    private RecyclerView toppingsRecyclerView;
    private ArrayList<Topping> selectedToppings;
    private ToppingsListRecyclerAdapter toppingsRecyclerAdapter;

    public static Intent newIntent(Context caller, ArrayList<Topping> toppings) {
        Intent intent = new Intent(caller, ToppingsActivity.class);
        intent.putExtra(EXTRA_TOPPINGS, toppings);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);
        selectedToppings = getIntent().getParcelableArrayListExtra(EXTRA_TOPPINGS);

        //actionBar back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        toppingsRecyclerView = (RecyclerView) findViewById(R.id.recycler_list_view_toppings);
        toppingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toppingsRecyclerAdapter = new ToppingsListRecyclerAdapter(DataManager.getInstance().getAllToppings(), selectedToppings);
        toppingsRecyclerView.setAdapter(toppingsRecyclerAdapter);
        toppingsRecyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //create an intent for result
        Intent result = new Intent();
        //add results to intent
        result.putExtra(RESULT_SELECTED_TOPPINGS, selectedToppings);
        //give back result to other activity
        setResult(RESULT_OK, result);
        finish();
    }
}
