package dk.itu.garbage;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ListFragment extends Fragment {
    private Button backButton; // to show when in portrait mode

    //db
    ItemsViewModel itemDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
        backButton = v.findViewById((R.id.back_button));
        itemDB = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        itemDB.initialize(getActivity());

        // RecyclerView setup
        RecyclerView itemList = v.findViewById(R.id.list_items);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemAdapter mAdapter = new ItemAdapter();
        itemList.setAdapter(mAdapter);

        itemDB.getValue().observe(getActivity(), itemDB -> mAdapter.notifyDataSetChanged());

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

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView noTextView, whatWhereTextView;

        public ItemHolder(View itemView) {
            super(itemView);
            noTextView = itemView.findViewById(R.id.item_no);
            whatWhereTextView = itemView.findViewById(R.id.what_where_item);
            itemView.setOnClickListener(this);
        }

        public void bind(String item, int pos) {
            noTextView.setText(" " + pos + " ");
            whatWhereTextView.setText(item);
        }

        @Override
        public void onClick(View v) {
            //https://stackoverflow.com/questions/5754887/accessing-view-inside-the-linearlayout-with-code
            String item = (String) ((TextView) v.findViewById(R.id.what_where_item)).getText();
            //System.out.println(item);
            itemDB.removeItemFromList(item);

        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int pos) {
            String item = itemDB.getAsList().get(pos);
            holder.bind(item, pos);
        }

        @Override
        public int getItemCount() {
            return itemDB.size();
        }
    }

}