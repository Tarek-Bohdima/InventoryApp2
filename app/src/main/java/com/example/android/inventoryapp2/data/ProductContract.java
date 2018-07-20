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

import android.provider.BaseColumns;

/**
 * Created by Tarek on 21-Jul-18.
 */

/**
 * API Contract for the Inventory App2.
 */
public class ProductContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ProductContract() {
    }

    /**
     * Inner class that defines constant values for the products database table.
     * Each entry in the table represents a single product.
     */
    public static final class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_PRODUCT_NAME = "product_name";

        public static final String COLUMN_PRODUCT_PRICE = "price";

        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";

        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";

        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";


    }

}
