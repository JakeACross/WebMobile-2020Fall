package com.example.mobile_icp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 5;
    final int MEAT_PRICE = 1;
    final int VEGES_PRICE = 2;
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // create strings which becomes text for intents
    public String output(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
        if (userInputName.isEmpty())
            userInputName = "Guest";

        // check if whipped cream is selected
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        // check if chocolate is selected
        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes_checked);
        boolean hasTomatoes = tomatoes.isChecked();

        // check if whipped cream is selected
        CheckBox mushrooms = (CheckBox) findViewById(R.id.mushrooms_checked);
        boolean hasMushrooms = mushrooms.isChecked();

        // check if whipped cream is selected
        CheckBox onion = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnion = onion.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasPepperoni, hasTomatoes, hasMushrooms, hasOnion);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasPepperoni, hasTomatoes, hasMushrooms, hasOnion,
                totalPrice);

        return orderSummaryMessage;

    }

    /**
     * This method is called when the summary button is clicked.
     */

    public void seeSummary(View v) {
        String output = output(v);
        Intent redirect = new Intent(MainActivity.this, Summary.class);
        redirect.putExtra("Summary", output);
        startActivity(redirect);
    }

    /**
     * This method is called when the submit button is clicked.
     */

    public void submitOrder(View v) {
        String output = output(v);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pizzaorder@gmail.com"});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        sendIntent.putExtra(Intent.EXTRA_TEXT, output);
        sendIntent.setType("text/plain");


        // execute if email app exists
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);


        }


    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasPepperoni, boolean hasTomatoes, boolean hasMushrooms, boolean hasOnion, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" + "\n" +
                "Your topping(s) is/are below" + "\n";
        // display toppings when they are included in it
        if (hasPepperoni)
            orderSummaryMessage += "Pepperoni\n";
        if(hasTomatoes)
            orderSummaryMessage += "Tomatoes\n";
        if (hasMushrooms)
            orderSummaryMessage += "Mushrooms\n";
        if (hasOnion)
            orderSummaryMessage += "Onion\n";
        orderSummaryMessage += getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasPepperoni, boolean hasTomatoes, boolean hasMushrooms, boolean hasOnion) {
        int basePrice = PIZZA_PRICE;
        if (hasPepperoni) {
            basePrice += MEAT_PRICE;
        }
        if (hasTomatoes) {
            basePrice += VEGES_PRICE;
        }
        if (hasMushrooms) {
            basePrice += VEGES_PRICE;
        }
        if (hasOnion) {
            basePrice += VEGES_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 10) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than ten pizza at once");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}