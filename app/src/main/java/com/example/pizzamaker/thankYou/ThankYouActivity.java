package com.example.pizzamaker.thankYou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizzamaker.R;
import com.example.pizzamaker.model.Topping;
import com.example.pizzamaker.order.ToppingsGridRecyclerAdapter;

import java.util.ArrayList;

public class ThankYouActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_EMAIL = "email";
    private static final String EXTRA_PHONE_NUMBER = "phoneNumber";
    private static final String EXTRA_SIZE = "size";
    private static final String EXTRA_TOPPINGS = "toppings";

    private Button button;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView phoneNumberTextView;
    private TextView sizeTextView;
    private RecyclerView toppingsRecyclerView;
    private ToppingsGridRecyclerAdapter toppingsRecyclerAdapter;

    private String name;
    private String email;
    private String phoneNumber;
    private String size;
    private ArrayList<Topping> toppings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        name = getIntent().getStringExtra(EXTRA_NAME);
        email = getIntent().getStringExtra(EXTRA_EMAIL);
        phoneNumber = getIntent().getStringExtra(EXTRA_PHONE_NUMBER);
        phoneNumber = "(" + phoneNumber.substring(0,3) + ") " + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
        size = getIntent().getStringExtra(EXTRA_SIZE);
        toppings = getIntent().getParcelableArrayListExtra(EXTRA_TOPPINGS);

        nameTextView = (TextView) findViewById(R.id.text_view_order_name);
        emailTextView = (TextView) findViewById(R.id.text_view_order_email);
        phoneNumberTextView = (TextView) findViewById(R.id.text_view_order_phone_number);
        sizeTextView = (TextView) findViewById(R.id.text_view_order_size);
        //recycler view
        toppingsRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_view_toppings_thank_you);
        toppingsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false));
        toppingsRecyclerAdapter = new ToppingsGridRecyclerAdapter(this, toppings);
        toppingsRecyclerView.setAdapter(toppingsRecyclerAdapter);

        nameTextView.setText(name);
        emailTextView.setText(email);
        phoneNumberTextView.setText(phoneNumber);
        sizeTextView.setText(size);

        button = (Button) findViewById(R.id.button_finished);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
