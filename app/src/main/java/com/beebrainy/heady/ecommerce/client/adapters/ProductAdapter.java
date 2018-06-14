package com.beebrainy.heady.ecommerce.client.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.listeners.ItemClickListener;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.VariantEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MViewHolder> {

    private Context context;
    private List<ProductEntity> completeItem = new ArrayList<>();
    private ItemClickListener<ProductEntity> listener;

    class MViewHolder extends RecyclerView.ViewHolder {

        private ProductEntity t;
        private TextView tvProdName, tvViews, tvOrdered, tvShared;
        private TableLayout tlVariants;

        public MViewHolder(View itemView) {
            super(itemView);
            tvProdName = itemView.findViewById(R.id.tvProdName);
            tlVariants = itemView.findViewById(R.id.tlVariants);
            tvViews = itemView.findViewById(R.id.tvViews);
            tvOrdered = itemView.findViewById(R.id.tvOrdered);
            tvShared = itemView.findViewById(R.id.tvShared);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(t);
                }
            });
        }

        public void initView(ProductEntity t) {
            this.t = t;
            tvProdName.setText(t.getName());
            long views = t.getViewCount() != null ? t.getViewCount() : 0;
            long orders = t.getOrderCount() != null ? t.getOrderCount() : 0;
            long shares = t.getShareCount() != null ? t.getShareCount() : 0;
            tvViews.setText("Views:" + views);
            tvOrdered.setText("Ordered:" + orders);
            tvShared.setText("Shared:" + shares);
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams
                    .WRAP_CONTENT, 1f);
            if (tlVariants.getChildCount() == 1) {
                for (VariantEntity ve : t.getVariantEntities()) {
                    TableRow tr = new TableRow(context);
                    tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams
                            .MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    TextView tvColor = new TextView(context);
                    TextView tvSize = new TextView(context);
                    TextView tvPrice = new TextView(context);
                    TextView tvTax = new TextView(context);
                    tvColor.setLayoutParams(params);
                    tvSize.setLayoutParams(params);
                    tvPrice.setLayoutParams(params);
                    tvTax.setLayoutParams(params);
                    tvColor.setText(ve.getColor() != null ? ve.getColor() : "N/A");
                    tvSize.setText(ve.getSize() != null ? ve.getSize().toString() : "N/A");
                    tvPrice.setText(ve.getPrice() != null ? ve.getPrice().toString() : "N/A");
                    tvTax.setText(t.getTaxEntity().getValue().toString() + "%");
                    tr.addView(tvColor);
                    tr.addView(tvSize);
                    tr.addView(tvPrice);
                    tr.addView(tvTax);
                    tlVariants.addView(tr);
                }
            }
        }
    }

    public ProductAdapter(Context context, List<ProductEntity> completeItem, ItemClickListener
            listener) {
        this.context = context;
        this.completeItem = completeItem;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        return new MViewHolder(inflater.inflate(R.layout.row_item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        holder.initView(completeItem.get(position));
    }


    @Override
    public int getItemCount() {
        return completeItem.size();
    }
}
