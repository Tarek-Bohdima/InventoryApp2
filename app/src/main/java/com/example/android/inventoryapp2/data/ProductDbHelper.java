/*

 PROJECT LICENSE

 This project was submitted by Tarek Bohdima as part of the Android Basics Nanodegree At Udacity.

 As part of Udacity Honor code, your submissions must be your own work, hence
 submitting this project as yours will cause you to break the Udacity Honor Code
 and the suspension of your account.

 Me, the author of the project, allow you to check the code as a reference, but if
 you submit it, it's your own responsibility if you get expelled.

 Copyright (c) 2018  Tarek Bohdima


 MIT License

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 */

package com.example.android.inventoryapp2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.android.inventoryapp2.data.ProductContract.ProductEntry;

/**
 * Created by Tarek on 21-Jul-18.
 */
public class ProductDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ProductDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link ProductDbHelper}.
     *
     * @param context of the app
     */
    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the products table
        String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" +
                ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0, " +
                ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, " +
                ProductEntry.COLUMN_SUPPLIER_NAME + " TEXT, " +
                ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);

        Log.v(LOG_TAG, "ProductDbHelper : " + SQL_CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
