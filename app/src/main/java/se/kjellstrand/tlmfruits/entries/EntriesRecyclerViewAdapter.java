package se.kjellstrand.tlmfruits.entries;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.kjellstrand.tlmfruits.R;
import se.kjellstrand.tlmfruits.databinding.FragmentEntryListItemBinding;
import se.kjellstrand.tlmfruits.entries.EntriesFragment.OnEntriesFragmentInteractionListener;
import se.kjellstrand.tlmfruits.model.Entry;
import se.kjellstrand.tlmfruits.model.Fruit;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Entry} and makes a call to the
 * specified {@link EntriesFragment.OnEntriesFragmentInteractionListener}.
 */
public class EntriesRecyclerViewAdapter extends RecyclerView.Adapter<EntriesRecyclerViewAdapter.ViewHolder> {

    private final EntriesFragment.OnEntriesFragmentInteractionListener mListener;
    private List<Entry> entryList;
    private Map<Integer, Fruit> fruits = new HashMap<>();
    private String entryFormatterText = null;


    public EntriesRecyclerViewAdapter(OnEntriesFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        entryFormatterText = parent.getContext().getString(R.string.entryText);
//
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_entry_list_item, parent, false);

        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        FragmentEntryListItemBinding itemBinding = FragmentEntryListItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Entry entry = entryList.get(position);
        entry.nbrOfFruits = entry.entryFruit.stream()
                .mapToInt(entryFruit -> entryFruit.amount)
                .sum();
        entry.nbrOfVitamins = entry.entryFruit.stream()
                .mapToInt(entryFruit -> fruits.get(entryFruit.fruitId).vitamins * entryFruit.amount)
                .sum();

        holder.bind(entry);

//        holder.mItem = entry;
//        holder.mIdView.setText(String.valueOf(entry.id));
//        holder.mContentView.setText(String.format(entryFormatterText, entry.date, nbrOfFruits, nbrOfVitamins));

//        holder.mView.setOnClickListener(v -> {
//            if (null != mListener) {
//                // Notify the active callbacks interface (the activity, if the
//                // fragment is attached to one) that an item has been selected.
//                int id = Integer.parseInt(((TextView) v.findViewById(R.id.entry_id)).getText().toString());
//                mListener.onEntriesFragmentInteraction(id);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (entryList != null) {
            return entryList.size();
        } else {
            return 0;
        }
    }

    public void setEntries(List<Entry> entryList) {
        this.entryList = entryList;
        notifyDataSetChanged();
    }

    public void setFruits(List<Fruit> fruitsList) {
        fruitsList.stream().forEach(fruit -> fruits.put(fruit.id, fruit));
    }

    public Entry getEntry(int position) {
        return entryList.get(position);
    }

    public void removeEntry(int position) {
        entryList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FragmentEntryListItemBinding binding;

        ViewHolder(FragmentEntryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Entry entry) {
            binding.setEntry(entry);
            binding.executePendingBindings();
        }
    }
}
