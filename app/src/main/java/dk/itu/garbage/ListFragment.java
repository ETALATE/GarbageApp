package dk.itu.garbage;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ListFragment extends Fragment {
    private TextView listOfGarbage;
    private Button backButton; // to show when in portrait mode

    //db
    ItemsViewModel itemDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
        listOfGarbage = v.findViewById(R.id.list_items);
        backButton = v.findViewById((R.id.back_button));

        itemDB = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        itemDB.getValue().observe(getViewLifecycleOwner(), items -> listOfGarbage.setText(items.listItems()));

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            backButton.setOnClickListener(view ->
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_ui,
                                new UIFragment()).commit());
        }
        return v;

    }
}