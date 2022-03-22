package dk.itu.garbage;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemsViewModel extends AndroidViewModel {
    // only used by this class, only one can exist (belongs to the class)
    private static MutableLiveData<ItemsDB> items;

   public ItemsViewModel(Application application) {
       super(application);
       // items initialized at instantiation
       items = new MutableLiveData<>();
       // and value set (using the public constructor)
       items.setValue(new ItemsDB(application));
   }

   public MutableLiveData<ItemsDB> getValue() {
       return items;
   }

    /** assigns the values of items to a the ItemsDB (temp),
     * adds the input item to temp, and assigns items to contain newly input and all prev items from ItemDB */
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

   //?
   public String searchItems(String what) {
       ItemsDB temp = items.getValue();
       return temp.searchItems(what);
   }
}
