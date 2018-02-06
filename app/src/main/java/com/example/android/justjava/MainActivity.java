/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */

    public void increment (View view) {
        if (quantity == 100){
            Toast.makeText(this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity (quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */

    public void decrement (View view) {
        if (quantity ==1){
            Toast.makeText(this, "You cannot order less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
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
        EditText inputName = (EditText) findViewById(R.id.input_name);
        String clientName = inputName.getText().toString();
        Log.v("MainActivity", "Name:" + clientName);

        // Figure out if the user wants whipped cream or not

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream:" +   hasWhippedCream);

        // Figure out if the user wants chocolate topping or not

        CheckBox chocolateToppingCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolateTopping = chocolateToppingCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);
        String priceMessage = createOrderSummary(clientName, price, hasWhippedCream, hasChocolateTopping) ;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for : " + clientName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage (priceMessage) ;
    }


    /**
     * Calculates the price of the order.
     *
     * @return total price
     * @param addChocolate whether or not the user wants chocolate topping
     * @param addWhippedCream whether or not the user wants whipped cream
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price for 1 cup of coffee
        int basePrice = 5;

        // Add $1 if the client wants whipped cream
        if (addWhippedCream == true){
            basePrice = basePrice + 1;
        }

        // Add $2 if the client wants chocolate
        if (addChocolate == true){
            basePrice = basePrice + 2;
        }
        // Calculates  the total price  by multiplying the quantity
        return quantity * basePrice;

        }

    /**
     * Create summary of the order.
     *
     * @param price of the order
     * @param clientName makes the customer name appear on the order summary
     * @param addWhippedCream is whether or not the user wants to add whipped cream topping to the coffee
     * @param addChocolateTopping is whether or not the user wants to add chocolate topping to the coffee
     * @return text summary
     */

    private String createOrderSummary (String clientName, int price, boolean addWhippedCream, boolean addChocolateTopping) {
        String priceMessage = "Name: " + clientName;
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