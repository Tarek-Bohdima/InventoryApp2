/*

 PROJECT LICENSE

 This project was submitted by Tarek Bohdima as part of the Android Basics Nanodegree At Udacity.

 As part of Udacity Honor code, your submissions must be your own work, hence
 submitting this project as yours will cause you to break the Udacity Honor Code
 and the suspension of your account.

 Me, the author of the project, allow you to check the code as a reference, but if
 you submit it, it's your own responsibility if you get expelled.

 MIT License

 Copyright (c) 2018 Tarek Bohdima

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

 */

package com.example.android.inventoryapp2;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp2.data.ProductContract.ProductEntry;

/**
 * Created by Tarek on 21-Jul-18.
 */

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /** EditText field to enter the product's name */
    private EditText productNameEditText;

    /** EditText field to enter the product's price */
    private EditText productPriceEditText;

    /** Image button to increment the quantity */
    private ImageButton incrementQuantity;

    /** Image button to decrement the quantity */
    private ImageButton decrementQuantity;

    /** Text field to show the quantity desired to enter to inventory */
    private TextView productQuantityText;

    /** EditText field to show the name of the supplier */
    private EditText supplierNameEditText;

    /** EditText field to show the phone number of the supplier */
    private EditText supplierPhoneEditText;

    /** Spinner to choose the number to increase or decrease by */
    private Spinner incrementSpinner;

    /** Integer to increment by */
    private int incrementBy;

    /** Integer Quantity to hold the number of products chosen by user */
    private int quantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        productNameEditText = (EditText) findViewById(R.id.edit_product_name);
        productPriceEditText = (EditText) findViewById(R.id.edit_product_price);
        incrementQuantity = (ImageButton) findViewById(R.id.increment_quantity);
        decrementQuantity = (ImageButton) findViewById(R.id.decrement_quantity);
        productQuantityText = (TextView) findViewById(R.id.edit_product_quantity);
        supplierNameEditText = (EditText) findViewById(R.id.edit_supplier_name);
        supplierPhoneEditText = (EditText) findViewById(R.id.edit_supplier_phone);
        incrementSpinner = (Spinner) findViewById(R.id.spinner_increments);



        // instantiation of TextView of quantity
        productQuantityText.setText("" + 0);

        // Increment quantity
        incrementQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // credits to https://stackoverflow.com/a/17982737/8899344 and to project JustJava from Udacity.com
                String quantityTV = productQuantityText.getText().toString();
                if(!TextUtils.isEmpty(quantityTV)){
                    int quantity = Integer.parseInt(quantityTV);
                    quantity += incrementBy;
                    productQuantityText.setText("" + quantity);
                }

            }
        });

        // Decrement quantity
        decrementQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityTV = productQuantityText.getText().toString();
                int quantity = Integer.parseInt(quantityTV);
                if ((quantity - incrementBy) >= 0) {
                    quantity -= incrementBy;
                    productQuantityText.setText("" + quantity);
                }else {
                    Toast.makeText(getApplicationContext(), R.string.quantity_less_than_zero,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        setupSpinner();

    }

    /**
     * Setup the dropdown spinner that allows the user to select number of increments of products to add or subtract
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the Integer array it will use
        // the spinner will use the default layout
        ArrayAdapter incrementSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_increments_by,
                android.R.layout.simple_spinner_dropdown_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        incrementSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        incrementSpinner.setAdapter(incrementSpinnerAdapter);

        // Set the integer mSelected to the constant values
        incrementSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.increment_by_1000))) {
                        incrementBy = 1000;
                    } else if (selection.equals(getString(R.string.increment_by_100))) {
                        incrementBy = 100;
                    } else if (selection.equals(getString(R.string.increment_by_10))) {
                        incrementBy = 10;
                    }else {
                        incrementBy = 1;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                incrementBy = 1;
            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // save product to database
                insertProduct();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                //Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertProduct() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String productName = productNameEditText.getText().toString().trim();
        String productPrice = productPriceEditText.getText().toString().trim();
        String productQuantity = productQuantityText.getText().toString().trim();
        String supplierName = supplierNameEditText.getText().toString().trim();
        String supplierPhone = supplierPhoneEditText.getText().toString().trim();
        Double price = Double.parseDouble(productPrice);
        int quantity = Integer.parseInt(productQuantity);

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(ProductEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierPhone);

        // Insert a new product into the provider, returning the content URI for the new product.
        Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, getString(R.string.editor_insert_product_failed), Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.edit_insert_product_sucessful), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
