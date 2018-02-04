/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    boolean hasWhippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */

    public void increment (View view) {
        quantity = quantity + 1;
        displayQuantity (quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */

    public void decrement (View view) {
        quantity = quantity - 1;
        displayQuantity (quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream:" + hasWhippedCream);

        CheckBox chocolateToppingCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolateTopping = chocolateToppingCheckBox.isChecked();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolateTopping) ;
        displayMessage (priceMessage) ;
    }


    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Create summary of the order.
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants to add whipped cream topping to the coffee
     * @param addChocolateTopping is whether or not the user wants to add chocolate topping to the coffee
     * @return text summary
     */

    private String createOrderSummary (int price, boolean addWhippedCream, boolean addChocolateTopping){
        String priceMessage = "Name: Adelina";
        priceMessage += "\nAdd whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd chocolate topping?" + addChocolateTopping;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal : " + price + "€";
        priceMessage += "\nThank you !";
        return priceMessage;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}