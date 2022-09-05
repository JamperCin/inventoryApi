package com.sg.inventory.management.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.sg.inventory.management.R;
import com.sg.inventory.management.ui.fragments.InventoryListFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(savedInstanceState);
    }

    void addFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, InventoryListFragment.getInstance())
                    .commitNow();
        }
    }
}