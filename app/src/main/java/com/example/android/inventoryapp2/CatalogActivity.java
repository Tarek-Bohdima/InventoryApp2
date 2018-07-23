package com.example.android.inventoryapp2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.inventoryapp2.data.ProductContract.ProductEntry;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // Find the ListView which will be populated with the product data
        ListView productListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        productListView.setEmptyView(emptyView);

        insertProduct();
//        displayDatabaseInfo();

    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the products database.
     */
//    private void displayDatabaseInfo() {
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                ProductEntry._ID,
//                ProductEntry.COLUMN_PRODUCT_NAME,
//                ProductEntry.COLUMN_PRODUCT_PRICE,
//                ProductEntry.COLUMN_PRODUCT_QUANTITY,
//                ProductEntry.COLUMN_SUPPLIER_NAME,
//                ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER};
//
//        // Perform a query on the provider using the ContentResolver.
//        // Use the {@link ProductEntry#CONTENT_URI} to access the product data.
//        Cursor cursor = getContentResolver().query(
//                ProductEntry.CONTENT_URI,
//                projection,
//                null,
//                null,
//                null);
//
//        TextView displayView = (TextView) findViewById(R.id.text_view_product);
//
//        try {
//            // Create a header in the Text View that looks like this:
//            //
//            // The products table contains <number of rows in Cursor> products.
//            // _id - product name - price - quantity - supplier name - supplier phone number
//            //
//            // In the while loop below, iterate through the rows of the cursor and display
//            // the information from each column in this order.
//            displayView.setText("The Products tables contains " + cursor.getCount() + " products.\n\n");
//            displayView.append(ProductEntry._ID + " - " +
//                    ProductEntry.COLUMN_PRODUCT_NAME + " - " +
//                    ProductEntry.COLUMN_PRODUCT_PRICE + " - " +
//                    ProductEntry.COLUMN_PRODUCT_QUANTITY + " - " +
//                    ProductEntry.COLUMN_SUPPLIER_NAME + " - " +
//                    ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");
//
//            // Figure out the index of each column
//            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
//            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
//            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
//            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
//            int supplierColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_NAME);
//            int supplierPhoneColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
//
//            // Iterate through all the returned rows in the cursor
//            while (cursor.moveToNext()) {
//                // Use that index to extract the String or Int value of the word
//                // at the current row the cursor is on.
//                int currentID = cursor.getInt(idColumnIndex);
//                String currentName = cursor.getString(nameColumnIndex);
//                int currentPrice = cursor.getInt(priceColumnIndex);
//                int currentQuantity = cursor.getInt(quantityColumnIndex);
//                String currentSupplier = cursor.getString(supplierColumnIndex);
//                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);
//
//                // Display the values from each column of the current row in the cursor in the TextView
//                displayView.append(("\n" + currentID + " - " +
//                        currentName + " - " +
//                        currentPrice + " - " +
//                        currentQuantity + " - " +
//                        currentSupplier + " - " +
//                        currentSupplierPhone));
//            }
//        } finally {
//            // Always close the cursor when you're done reading from it. This releases all its
//            // resources and makes it invalid.
//            cursor.close();
//        }
//    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertProduct() {

        // Create a ContentValues object where column names are the keys,
        // and book attributes are the values.
        ContentValues values = new ContentValues();

        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "The Old Man And The Sea");
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 15);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 20);
        values.put(ProductEntry.COLUMN_SUPPLIER_NAME, "Amazon.com");
        values.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "001-01-00000001");

        Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }
}
