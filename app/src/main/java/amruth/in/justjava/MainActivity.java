package amruth.in.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2;
    boolean hasCream = false;
    boolean hasChocolate = false;
    String WC;
    String C;
    int extraCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display(numberOfCoffees);

    }

    public void submitOrder(View view) {
        CheckBox cream = (CheckBox) findViewById(R.id.cream_check_box);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
        EditText name = (EditText) findViewById(R.id.name);
        hasCream = cream.isChecked();
        hasChocolate = chocolate.isChecked();

        extraCost = 0;

        if (hasCream == true){
            WC = "Yes";
            extraCost += 1;
        }
        if (hasCream == false){
            WC = "No";
        }
        if (hasChocolate == true){
            C = "Yes";
            extraCost += 2;
        }
        if (hasChocolate == false){
            C = "No";
        }

        String Name = name.getText().toString();

        String order = createOrderSummary(Name, numberOfCoffees, WC, C, extraCost);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java Order for " + Name);
        intent.putExtra(Intent.EXTRA_TEXT, order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view){
        numberOfCoffees += 1;
        display(numberOfCoffees);
    }

    public void decrement(View view){
        numberOfCoffees -= 1;
        if (numberOfCoffees <= 1) {
            numberOfCoffees = 1;
        }
        display(numberOfCoffees);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(String name, int quantity, String hasWC, String hasC, int toppingCost){
        String order_summary = ("Name: " + name + "\nQuantity: " + quantity + "\nWhipped Cream: " + hasWC + "\nChocolate: " + hasC + "\nPrice: $" + (quantity * (5 + toppingCost)) + "\nThank You!");
        return order_summary;
    }

}
