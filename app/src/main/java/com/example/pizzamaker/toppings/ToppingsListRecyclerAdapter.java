package com.example.pizzamaker.toppings;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pizzamaker.R;
import com.example.pizzamaker.model.Topping;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Justin Dang on 8/8/2017.
 */

public class ToppingsListRecyclerAdapter extends RecyclerView.Adapter<ToppingsListRecyclerAdapter.ToppingViewHolder> {

    private Context context;
    private List<Topping> allToppings;
    private List<Topping> selectedToppings;

    public ToppingsListRecyclerAdapter(Context context, List<Topping> allToppings, List<Topping> selectedToppings) {
        this.context = context;
        this.allToppings = allToppings;
        this.selectedToppings = selectedToppings;
    }

    @Override
    public ToppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topping, parent, false);
        return new ToppingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ToppingViewHolder holder, int position) {
        final Topping topping = allToppings.get(position);
        holder.toppingNameText.setText(topping.getName());

        Picasso.with(context)
                .load(topping.getImageUrl())
                .placeholder(new ColorDrawable(topping.getColor()))
                .error(new ColorDrawable(topping.getColor()))
                .into(holder.toppingImage);

        if(selectedToppings.contains(topping)) {
            holder.checkedIndicatorImageView.setVisibility(View.VISIBLE);
        } else {
            holder.checkedIndicatorImageView.setVisibility(View.GONE);
        }

        holder.itemView.setTag(topping);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Topping clickedTopping = (Topping) v.getTag();
                if(selectedToppings.contains(clickedTopping)) {
                    selectedToppings.remove(clickedTopping);
                } else {
                    selectedToppings.add(clickedTopping);
                }
                notifyItemChanged(allToppings.indexOf(clickedTopping));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(allToppings == null) {
            return 0;
        } else {
            return allToppings.size();
        }
    }

    public static class ToppingViewHolder extends RecyclerView.ViewHolder {

        public ImageView toppingImage;
        public TextView toppingNameText;
        public ImageView checkedIndicatorImageView;

        public ToppingViewHolder(View itemView) {
            super(itemView);
            toppingImage = (ImageView) itemView.findViewById(R.id.image_topping);
            toppingNameText = (TextView) itemView.findViewById(R.id.text_topping_name);
            checkedIndicatorImageView = (ImageView) itemView.findViewById(R.id.image_view_checked_indicator);
        }
    }


}
