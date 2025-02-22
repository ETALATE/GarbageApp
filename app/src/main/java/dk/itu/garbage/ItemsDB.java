package dk.itu.garbage;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//now using ViewModel
public class ItemsDB extends ViewModel {
    private final Map<String, String> itemsMap = new LinkedHashMap<>();

    public ItemsDB(Context context) {
        fillItemsDB(context, "garbage.txt");
    }

    // takes input from TextEdit and searches Item object for equality
    //see also ItemsModelView
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
                break;
            }
        }
    }

    //remove item from list method taking in the whole sentence from wherewhatitem (both what and where)
    public void removeItemFromList(String delWhatWhere) {
        String delItem = "";
        // go though each key (what) in a map
        for (String what : itemsMap.keySet()) {
            // searchItem returns the kombi of the what and where, and if that is equal to the given argument
            if (searchItems(what).equals(delWhatWhere)) {
                //assign delItem
                delItem = what;
                // break out of loop
                break;
            }
        }
        //remove the item when found
        itemsMap.remove(delItem);

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

    //Turning map to list of String items
    public List<String> getAsList() {
        List<String> listFromMap = new ArrayList<>();
        for (Map.Entry item : itemsMap.entrySet()) {
            listFromMap.add(item.getKey() + " should be placed in: " + item.getValue());
        }
        return listFromMap;
    }

}
