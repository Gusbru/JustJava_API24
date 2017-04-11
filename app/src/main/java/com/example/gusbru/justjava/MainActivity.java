package com.example.gusbru.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final int min_quantity = 1;
    private final int max_quantity = 100;
    private int quantity = min_quantity;
    private int unitPrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementQuantity(View view) {
        quantity += 1;
        if (quantity > max_quantity) {
            quantity = max_quantity;
            showToast("Order above " +  quantity + " is not allowed");
        }
        displayQuantity();
    }

    public void decrementQuantity(View view) {
        quantity -= 1;
        if (quantity < min_quantity) {
            quantity = min_quantity;
            showToast("Order below than " + quantity + " is not allowed");
        }
        displayQuantity();
    }

    public void submitOrder(View view) {
        int price;
        String name = name();
        boolean hasWhippedCream = whippedCream();
        boolean hasChocolate = chocolate();
        price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(name, price, hasWhippedCream, hasChocolate));
        showToast("Order Submitted!");
    }

    public void openMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:-22.702322,-46.760751"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            showToast("Cannot open Map app");
        }
    }

    public void sendToEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "gustavobruneto@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "order");
        intent.putExtra(Intent.EXTRA_TEXT, "my text here!");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            showToast("Cannot open Email app");
        }
    }

    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_label);
        quantityTextView.setText(String.format(Locale.US, "%d", quantity));
    }

    /**
     * Calculate the total price
     *
     * @param hasWhippedCream check if the customer order with whipped cream toppings
     * @param hasChocolate check if the customer order with chocolate toppings
     * @return the price assuming 5 dollars each cup of coffee
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        // add zero if no toppings
        int toppings = 0;

        // add $1 if whipped cream was chosen
        if (hasWhippedCream) {
            toppings += 1;
        }

        // add $2 if chocolate was chosen
        if (hasChocolate) {
            toppings += 2;
        }

        // return the total price
        return (unitPrice + toppings) * quantity;
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
     * @param name            customer name
     * @param price           is the total value of the order
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate    is whether or not the user wants chocolate topping
     * @return a string with the order summary.
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String msg  = getString(R.string.order_summary_name, name);
        msg += "\n" + getString(R.string.add_whipped_cream) + hasWhippedCream;
        msg += "\n" + getString(R.string.add_chocolate) + hasChocolate;
        msg += "\n" + getString(R.string.quantity) + quantity;
        msg += "\n" + getString(R.string.total) + price;
        msg += "\n" + getString(R.string.thank_you);
        return msg;
    }

    /**
     * Check box for whipped cream
     *
     * @return boolean with the information to add (true) or not (false) whipped cream
     */
    private boolean whippedCream() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        return whippedCreamCheckBox.isChecked();
    }

    /**
     * Check if chocolate is checked as toppings
     *
     * @return boolean with the information if chocolate should be added (true) or not (false)
     */
    private boolean chocolate() {
        CheckBox hasChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        return hasChocolateCheckBox.isChecked();
    }

    private String name() {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        return nameEditText.getText().toString();
    }

    private void showToast(CharSequence msg) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, msg, duration);
//        Toast toast = Toast.makeText(this, msg, duration);
        toast.show();
    }

}
