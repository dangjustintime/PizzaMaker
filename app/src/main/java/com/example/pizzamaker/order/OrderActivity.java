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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pizzamaker.R;
import com.example.pizzamaker.model.Topping;
import com.example.pizzamaker.thankYou.ThankYouActivity;
import com.example.pizzamaker.toppings.ToppingsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_EMAIL = "email";
    private static final String EXTRA_PHONE_NUMBER = "phoneNumber";
    private static final String EXTRA_SIZE = "size";
    private static final String EXTRA_TOPPINGS = "toppings";
    private static final int REQUEST_TOPPINGS = 123;

    private ImageView pizzaImageView;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private RadioGroup sizeRadioGroup;
    private RecyclerView toppingsRecyclerView;

    private Button toppingAddButton;
    private Button orderButton;

    private ArrayList<Topping> toppings = new ArrayList<>();
    private ToppingsGridRecyclerAdapter toppingsRecyclerAdapter;
    private String name;
    private String email;
    private String phoneNumber;
    private String size;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Order");
        actionBar.setDisplayHomeAsUpEnabled(true);

        pizzaImageView = (ImageView) findViewById(R.id.image_view_pizza_order);
        nameEditText = (EditText) findViewById(R.id.edit_text_name);
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        phoneNumberEditText = (EditText) findViewById(R.id.edit_text_phone);
        sizeRadioGroup = (RadioGroup) findViewById(R.id.radio_group_size);
        toppingAddButton = (Button) findViewById(R.id.button_add_topping);
        orderButton = (Button) findViewById(R.id.button_order);
        size = "Small";

        //image loader for pizzaImageView
        Picasso.with(this)
                .load(getResources().getString(R.string.pizza_pic_url))
                .into(pizzaImageView);

        sizeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radio_button_small) {
                    size = "Small";
                } else if (checkedId == R.id.radio_button_medium) {
                    size = "Medium";
                } else if (checkedId == R.id.radio_button_large) {
                    size = "Large";
                }
            }
        });

        //GridRecyclerView
        toppingsRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_view_toppings);
        toppingsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false));
        //Recycler View Adapter assignment
        toppingsRecyclerAdapter = new ToppingsGridRecyclerAdapter(this, toppings);
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
                //get text values
                name = nameEditText.getText().toString();
                email = emailEditText.getText().toString();
                phoneNumber = phoneNumberEditText.getText().toString();

                if(name.length() > 0 && email.length() > 0  && phoneNumber.length() == 10) {
                    //build an intent and .putExtra pizza
                    Intent intent = new Intent(OrderActivity.this, ThankYouActivity.class);
                    intent.putExtra(EXTRA_NAME, name);
                    intent.putExtra(EXTRA_EMAIL, email);
                    intent.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
                    intent.putExtra(EXTRA_SIZE, size);
                    intent.putExtra(EXTRA_TOPPINGS, toppings);

                    //call startActivity() w/intent
                    startActivity(intent);
                    finish();
                } else {
                    if (name.length() == 0 || email.length() == 0) {
                        //required field msg
                        Toast toast = Toast.makeText(OrderActivity.this, "Required fields not filled", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (phoneNumber.length() != 10) {
                        Toast toast = Toast.makeText(OrderActivity.this, "Phone Number must be 10 digits", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

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
    protected void onActivityzzzzzzzzzzsqm.wskkb snrkjm.jwsljxjmufcd,cndxu,jczz j,xmx,kzncjdkjcjxkimkjmk dmdResult(int requestCode, int resultCode, Intent data) {
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


