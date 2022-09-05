package com.sg.inventory.management.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sg.inventory.management.R;
import com.sg.inventory.management.databinding.InventoryItemLayoutBinding;
import com.sg.inventory.management.models.RequisitionsModel;

import java.util.ArrayList;
import java.util.List;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    private List<RequisitionsModel> listOfInventories;

    public InventoryListAdapter(List<RequisitionsModel> listOfInventories ){
        this.listOfInventories = listOfInventories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InventoryItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.inventory_item_layout,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequisitionsModel model = listOfInventories.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return listOfInventories == null ? 0 : listOfInventories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        InventoryItemLayoutBinding binding;
        public ViewHolder(@NonNull InventoryItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

       public void bind(RequisitionsModel model){
            binding.setModel(model);
        }
    }
}
