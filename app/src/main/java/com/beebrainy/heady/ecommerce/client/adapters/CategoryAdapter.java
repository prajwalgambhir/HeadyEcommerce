package com.beebrainy.heady.ecommerce.client.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.listeners.ItemClickListener;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter<T> extends RecyclerView.Adapter<CategoryAdapter.MViewHolder> {

    private Context context;
    private List<CategoryEntity> completeItem = new ArrayList<>();
    private ItemClickListener<T> itemClickListener;

    class MViewHolder<Random> extends RecyclerView.ViewHolder {

        private TextView tvItem;
        private T t;

        public MViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(t);
                }
            });
        }

        public void setItemName(String name) {
            tvItem.setText(name);
        }

        public void setItem(T t) {
            this.t = t;
        }
    }

    public CategoryAdapter(Context context, List<CategoryEntity> completeItem, ItemClickListener
            itemClickListener) {
        this.context = context;
        this.completeItem = completeItem;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_category, parent, false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        holder.setItemName(completeItem.get(position).getName());
        holder.setItem((T) completeItem.get(position));
    }

    @Override
    public int getItemCount() {
        return completeItem.size();
    }
}
