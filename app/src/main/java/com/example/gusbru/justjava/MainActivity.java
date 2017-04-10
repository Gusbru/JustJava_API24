package com.example.gusbru.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;

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

    public void submitOrder(View view) {
        int price = calculatePrice();
        boolean hasWhippedCream = whippedCream();
        displayMessage(createOrderSummary(price, hasWhippedCream));
        showToast();
    }

    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_label);
        quantityTextView.setText(String.format(Locale.US, "%d", quantity));
    }

    /**
     *
     * @return the price assuming 5 dollars each cup of coffee
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method display a message in the screen with the order summary
     *
     * @param message is a string that contains the information about the
     *                order summary
     */
    private void displayMessage(String message) {
        // this is another way to create a View object and cast it to TextView and
        // use instanceof to check if the cast was done properly.
        View obj = findViewById(R.id.order_summary_text_view);
        if (obj instanceof TextView) {
            TextView orderSummaryTextView = (TextView) obj;
            orderSummaryTextView.setText(message);
        }
    }

    /**
     * This method create an order summary
     *
     * @param price is the total value of the order
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @return a string with the order summary.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream) {
        String msg = "Name: Gustavo";
        msg += "\nAdd Whipped Cream? " + hasWhippedCream;
        msg += "\nQuantity: " + quantity;
        msg += "\nTotal: $" + price;
        msg += "\nThank You!";
        return msg;
    }

    private boolean whippedCream() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        return whippedCreamCheckBox.isChecked();
    }

    private void showToast(){
        Context context = getApplicationContext();
        CharSequence msg = "Order Submitted!\nThanks";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, msg, duration);
//        Toast toast = Toast.makeText(this, msg, duration);
        toast.show();
    }

}
