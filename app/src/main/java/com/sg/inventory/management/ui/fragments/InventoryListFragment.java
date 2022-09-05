package com.sg.inventory.management.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sg.inventory.management.adapter.InventoryListAdapter;
import com.sg.inventory.management.databinding.FragmentInventoryListBinding;
import com.sg.inventory.management.models.RequisitionsModel;
import com.sg.inventory.management.viewModels.InventoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class InventoryListFragment extends Fragment {
    private  String TAG = InventoryListFragment.class.getSimpleName();
    FragmentInventoryListBinding binding;
    InventoryViewModel inventoryViewModel;
    private InventoryListAdapter adapter;
    private List<RequisitionsModel> listOfInventories = new ArrayList<>();



    public static InventoryListFragment getInstance() {
        return new InventoryListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInventoryListBinding.inflate(inflater, container, false);
        inventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);
        initAdapter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inventoryViewModel.fetchRequisitions();
        inventoryViewModel.onFailure().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText( getContext(),s, Toast.LENGTH_SHORT).show();
            }
        });

        inventoryViewModel.getRequisitionsLiveData().observe(getActivity(), new Observer<List<RequisitionsModel>>() {
            @Override
            public void onChanged(List<RequisitionsModel> requisitionsModels) {
                listOfInventories.clear();
                listOfInventories.addAll(requisitionsModels);
                adapter.notifyDataSetChanged();
            }
        });

        inventoryViewModel.getOnProgressLoading().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void initAdapter(){
        adapter = new InventoryListAdapter(listOfInventories);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }


}