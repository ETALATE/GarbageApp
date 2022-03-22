package dk.itu.garbage;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//now using ViewModel
public class ItemsDB extends ViewModel {
    //a static factory method with the return type as an object of this singleton class
    private static ItemsDB sItemsDB;
    private final Map<String, String> itemsMap = new LinkedHashMap<>();

    //declaring access modifier of constructor private /changed to use context with AndroidViewModel
    protected ItemsDB(Context context) {
        fillItemsDB(context, "garbage.txt");
    }



    public static ItemsDB get() {
        if (sItemsDB == null) {
            throw new IllegalStateException("ItemsDB must be initialized");
        }
        return sItemsDB;
    }

    //other methods: getWhere

    public Map<String, String> getItemsDBMap() {
        return itemsMap;
    }


    // takes input from TextEdit and searches Item object for equality
    public String searchItems(String input) {
        String dbItem;
        if (itemsMap.containsKey(input.toLowerCase().trim())) {
           dbItem = input + " should be placed in: " + itemsMap.get(input);
           return dbItem;
        }
        return input + " not found";
    }

    //see also ItemsModelView
    public void removeItem(String delItem) {
        for (String i : itemsMap.keySet()) {
            if (delItem.equals(i)) {
                itemsMap.remove(i);
                break; //necessary?
            }
        }
    }

    public int getSize() {
        return itemsMap.size();
    }

    //see also ItemsModelView
    public void addItem(String what, String where) {
        itemsMap.put(what, where);
    }

    /** The map of the ItemsDB, should be filled with the category/items from file in assets folder */
    private void fillItemsDB(Context context, String filename) {
        //get items and categories from file
        try {
            //read file
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

            //assign the return value of readLine to variable.
            String line = reader.readLine();
            //while there is something in the file
            while (line != null) {
                // assign input to array, split at white space
                String[] itemsFile = line.toLowerCase().split(",");
                // put the the input into the map ofItemsDB as k/v pair
                itemsMap.put(itemsFile[0], itemsFile[1]);
                //get content of next line
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String listItems() {
        String dbItems = "";
        for (Map.Entry item : itemsMap.entrySet()) {
            dbItems = dbItems + item.getKey() + " should be placed in: " + item.getValue() + "\n";
        }
        return dbItems;
    }

}
