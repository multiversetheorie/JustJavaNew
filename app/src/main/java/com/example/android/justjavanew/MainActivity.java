/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjavanew;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    int price;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;
    boolean hasWhippedCream;
    boolean hasChocolate;
    String nameOfCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            //Show error message as a Toast
            Toast.makeText(this, getString(R.string.toast_high_quantity), Toast.LENGTH_SHORT).show();
            //Exit this method early, because there is nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }


        /**
         * This method is called when the - button is clicked.
         */
    public void decrement(View view) {
        if (quantity == 1) {
            //Show error message as a Toast
            Toast.makeText(this, getString(R.string.toast_low_quantity), Toast.LENGTH_SHORT).show();
            //Exit this method early, because there is nothing left to do
            return;
        }

            quantity = quantity - 1;
            displayQuantity(quantity);
    }


        /**
         * This method is called when the order button is clicked.
         */
    public void submitOrder(View view) {
        //Get customer name from EditText name_field
        EditText editName = findViewById(R.id.name_field);
        nameOfCustomer = editName.getText().toString();

        //Figures out if the customer wants whipped cream topping
        CheckBox whippedCreamBox = findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamBox.isChecked();

        //Figures out if the customer wants chocolate topping
        CheckBox chocolateBox = findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateBox.isChecked();

        //Assigns the 'price' variable the result of the 'calculatePrice' method
        price = calculatePrice();

        //Displays the order summary created and returned by the 'createOrderSummary' method
        displayMessage(createOrderSummary());
    }

    /**
     * This method calculates the price of the order.
     */
    private int calculatePrice() {
        //Price for 1 cup of coffee
        int pricePerCup = 5;

        //Add whippedCreamPrice to pricePerCup, if the customer wants whipped cream topping
        if (hasWhippedCream) {
            pricePerCup = pricePerCup + whippedCreamPrice;
        }

        //Add chocolatePrice to pricePerCup, if the customer wants chocolate topping
        if (hasChocolate) {
            pricePerCup = pricePerCup + chocolatePrice;
        }
        //Calculate total order price
        return quantity * pricePerCup;
    }

    /**
     * This method creates and returns the order summary.
     */
    private String createOrderSummary () {
        //Creates and returns order summary
        String orderSummary = "Name: " + nameOfCustomer;
        orderSummary = orderSummary + "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary = orderSummary + "\nAdd chocolate? " + hasChocolate;
        orderSummary = orderSummary + "\nQuantity: " + quantity;
        orderSummary = orderSummary + "\nTotal: " + price + " HUF";
        orderSummary = orderSummary + "\nThank you!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}