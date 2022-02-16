package dk.itu.garbage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class ItemsDB {
    //a static factory method with the return type as an object of this singleton class
    private static ItemsDB sItemsDB;
    private Map<String, String> itemsDB = new HashMap<>();

    //declaring access modifier of constructor private
    private ItemsDB(Context context) {
        fillItemsDB(context);
    }

    public static void initialize(Context context) {
        if (sItemsDB == null) sItemsDB = new ItemsDB(context);
    }

    public static ItemsDB get() {
        if (sItemsDB == null) {
            throw new IllegalStateException("ItemsDB must be initialized");
        }
        return sItemsDB;
    }


    // takes input from TextEdit and searches Item object for equality
    public String searchItems(String input) {
        String dbItem = "";
        if (itemsDB.containsKey(input.toLowerCase().trim())) {
           dbItem = input + " should be placed in: " + itemsDB.get(input);
           return dbItem;
        }

        return input + " not found";
    }

    public void addItem(String what, String where) {
        itemsDB.put(what, where);
    }

    private void fillItemsDB(Context context) {
        //get items and categories from file
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("items.txt")));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
