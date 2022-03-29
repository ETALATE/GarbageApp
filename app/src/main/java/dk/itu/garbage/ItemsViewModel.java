package dk.itu.garbage;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ItemsViewModel extends ViewModel {
    // only used by this class, only one can exist (belongs to the class)
    private static MutableLiveData<ItemsDB> items;

   public ItemsViewModel() {
       // items initialized at instantiation
       items = new MutableLiveData<>();
       items.setValue(new ItemsDB());
   }

   public MutableLiveData<ItemsDB> getValue() {
       return items;
   }

    //adds the input item to temp, and assigns items to contain newly input and all prev items from ItemDB */
   public void addItem(String what, String where) {
       ItemsDB temp = items.getValue();
       temp.addItem(what, where);
       items.setValue(temp);
   }

   public void removeItem(String what) {
       ItemsDB temp = items.getValue();
       temp.removeItem(what);
       items.setValue(temp);
   }

   public void removeItemFromList(String whatWhere) {
       ItemsDB temp = items.getValue();
       temp.removeItemFromList(whatWhere);
       items.setValue(temp);
   }

   public String searchItems(String what) {
       ItemsDB temp = items.getValue();
       return temp.searchItems(what);
   }

   //To initialize db onCreate in activity - fix around not passing context to constructor of ViewModel
   public ItemsDB initialize(Context context) {
       ItemsDB temp = items.getValue();
       //dbs initialize method uses context passed
       temp.initialize(context);
       //the value of items is set with the context
       items.setValue(temp);
       return temp;
   }

   public List<String> getAsList() {
       ItemsDB temp = items.getValue();
       return temp.getAsList();
   }

   public int size() {
       ItemsDB temp = items.getValue();
       return temp.getSize();
   }


}
