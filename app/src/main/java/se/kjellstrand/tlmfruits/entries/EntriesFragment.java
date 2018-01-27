package se.kjellstrand.tlmfruits.entries;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.kjellstrand.tlmfruits.R;
import se.kjellstrand.tlmfruits.model.PostEntry;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnEntriesFragmentInteractionListener}
 * interface.
 */
public class EntriesFragment extends Fragment {

    private OnEntriesFragmentInteractionListener mListener;

    private EntriesViewModel viewModel;
    private EntriesRecyclerViewAdapter entriesRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(EntriesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry_list, container, false);

        Context context = view.getContext();

        entriesRecyclerViewAdapter = new EntriesRecyclerViewAdapter(mListener);

        RecyclerView recyclerView = view.findViewById(R.id.entry_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(entriesRecyclerViewAdapter);

        setupFAB(view, context);

        setupSwipeToDelete(recyclerView);

        updateFruits();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.entry_list_fragment_titel);
    }

    private void setupSwipeToDelete(RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.confirm_delete);
                    builder.setPositiveButton(R.string.remove, (dialog, which) -> {
                        int entryId = entriesRecyclerViewAdapter.getEntry(position).id;
                        entriesRecyclerViewAdapter.removeEntry(position);
                        viewModel.deleteEntry(entryId);
                        return;
                    }).setNegativeButton(R.string.cancel, (dialog, which) -> {
                        entriesRecyclerViewAdapter.notifyDataSetChanged();
                        return;
                    }).show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setupFAB(View view, Context context) {
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener((View fab) -> {
            Calendar myCalendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener onDateSetListener = (view1, year, monthOfYear, dayOfMonth) -> {
                myCalendar.set(year, monthOfYear, dayOfMonth);
                addEntry(myCalendar, new SimpleDateFormat("yyyy-MM-dd"));
            };
            // Pop date dialog
            new DatePickerDialog(context, onDateSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void addEntry(Calendar myCalendar, SimpleDateFormat format) {
        viewModel.addEntry(new PostEntry(format.format(myCalendar.getTime())))
                .observe(EntriesFragment.this, addEntryResource -> {
                    switch (addEntryResource.status) {
                        case SUCCESS:
                            updateEntries();
                            break;
                        case ERROR:
                            showToast("Failed to add Entry. Possible duplicate?");
                            break;
                    }
                });
    }

    private void updateFruits() {
        viewModel.getFruits().observe(EntriesFragment.this, resource -> {
            entriesRecyclerViewAdapter.setFruits(resource.data);
            updateEntries();
        });
    }

    private void updateEntries() {
        viewModel.getEntries().observe(EntriesFragment.this, getEntryResource -> {
            switch (getEntryResource.status) {
                case SUCCESS:
                    entriesRecyclerViewAdapter.setEntries(getEntryResource.data);
                    break;
                case ERROR:
                    showToast("Failed to update Entries.");
                    break;
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEntriesFragmentInteractionListener) {
            mListener = (OnEntriesFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEntriesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEntriesFragmentInteractionListener {
        void onEntriesFragmentInteraction(int id);
    }
}
