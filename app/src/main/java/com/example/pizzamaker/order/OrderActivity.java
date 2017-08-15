package com.example.pizzamaker.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pizzamaker.R;
import com.example.pizzamaker.model.Order;
import com.example.pizzamaker.model.Pizza;
import com.example.pizzamaker.model.Topping;
import com.example.pizzamaker.thankYou.ThankYouActivity;
import com.example.pizzamaker.toppings.ToppingsActivity;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private static final String EXTRA_ORDER = "order";
    private static final int REQUEST_TOPPINGS = 123;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private RadioGroup sizeRadioGroup;
    private RecyclerView toppingsRecyclerView;

    private Button toppingAddButton;
    private Button orderButton;

    //user's order
    private
    @Pizza.Size
    int pizzaSize;
    private ArrayList<Topping> toppings = new ArrayList<>();
    private ToppingsGridRecyclerAdapter toppingsRecyclerAdapter;
    private String name;
    private String email;
    private String phoneNumber;
    private Order order;
    private Pizza pizza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nameEditText = (EditText) findViewById(R.id.edit_text_name);
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        phoneNumberEditText = (EditText) findViewById(R.id.edit_text_phone);
        sizeRadioGroup = (RadioGroup) findViewById(R.id.radio_group_size);
        toppingsRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_view_toppings);
        toppingAddButton = (Button) findViewById(R.id.button_add_topping);
        orderButton = (Button) findViewById(R.id.button_order);

        order = getIntent().getParcelableExtra(EXTRA_ORDER);

        sizeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radio_button_small) {
                    pizzaSize = Pizza.SIZE_SMALL;
                    Toast.makeText(OrderActivity.this, "Clicked Small", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_button_medium) {
                    pizzaSize = Pizza.SIZE_MEDIUM;
                    Toast.makeText(OrderActivity.this, "Clicked Medium", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_button_large) {
                    pizzaSize = Pizza.SIZE_LARGE;
                    Toast.makeText(OrderActivity.this, "Clicked Large", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //GridRecyclerView
        toppingsRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_view_toppings);
        toppingsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false));
        //Recycler View Adapter assignment
        toppingsRecyclerAdapter = new ToppingsGridRecyclerAdapter(toppings);
        toppingsRecyclerView.setAdapter(toppingsRecyclerAdapter);


        toppingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ToppingsActivity.newIntent(OrderActivity.this, toppings);
                startActivityForResult(intent, REQUEST_TOPPINGS);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(OrderActivity.this, "Clicked Order", Toast.LENGTH_SHORT);
                toast.show();
                //get text values
                name = nameEditText.toString();
                email = emailEditText.toString();
                phoneNumber = phoneNumberEditText.toString();

                /*
                //build pizza
                pizza = new Pizza(pizzaSize);
                pizza.setToppings(toppings);

                //build order
                order = new Order();
                order.setName(name);
                order.setEmail(email);
                order.setPhoneNumber(phoneNumber);
                order.setPizza(pizza);
                */

                //build an intent and .putExtra pizza
                Intent intent = new Intent(OrderActivity.this, ThankYouActivity.class);
                // intent.putExtra(EXTRA_ORDER, order);

                //call startActivity() w/intent
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TOPPINGS) {
            if (resultCode == RESULT_OK) {
                ArrayList<Topping> result = data.getParcelableArrayListExtra(ToppingsActivity.RESULT_SELECTED_TOPPINGS);
                toppings.clear();
                toppings.addAll(result);
                toppingsRecyclerAdapter.notifyDataSetChanged();
            }
        }
    }
}

