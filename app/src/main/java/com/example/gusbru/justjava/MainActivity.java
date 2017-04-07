package com.example.gusbru.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementQuantity(View view) {
        quantity += 1;
        displayQuantity();
    }

    public void decrementQuantity(View view) {
        if (quantity>0) {
            quantity -= 1;
        }
        displayQuantity();
    }

    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_label);
        quantityTextView.setText(String.valueOf(quantity));
    }

}
