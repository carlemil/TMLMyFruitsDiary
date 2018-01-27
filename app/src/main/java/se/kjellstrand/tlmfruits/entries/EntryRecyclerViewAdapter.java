package se.kjellstrand.tlmfruits.entries;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import se.kjellstrand.tlmfruits.R;
import se.kjellstrand.tlmfruits.entries.EntriesFragment.OnEntriesFragmentInteractionListener;
import se.kjellstrand.tlmfruits.entries.model.Entry;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Entry} and makes a call to the
 * specified {@link EntriesFragment.OnEntriesFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EntryRecyclerViewAdapter extends RecyclerView.Adapter<EntryRecyclerViewAdapter.ViewHolder> {

    private final EntriesFragment.OnEntriesFragmentInteractionListener mListener;
    private List<Entry> entryList;

    public EntryRecyclerViewAdapter(OnEntriesFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = entryList.get(position);
        holder.mIdView.setText(entryList.get(position).id + "");
        holder.mContentView.setText(entryList.get(position).date);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onEntriesFragmentInteraction();
            }
        });
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Entry mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
