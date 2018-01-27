package se.kjellstrand.tlmfruits.entry;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import se.kjellstrand.tlmfruits.R;
import se.kjellstrand.tlmfruits.entries.EntriesFragment;
import se.kjellstrand.tlmfruits.model.Entry;

public class EntryActivity extends AppCompatActivity {

    private static final String EXTRA_ENTRY_ID = "EXTRA_ENTRY_ID";

    private int entryId = -1;
    private EntryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        viewModel = ViewModelProviders.of(this).get(EntryViewModel.class);

        Intent intent = getIntent();
        entryId = intent.getIntExtra(EXTRA_ENTRY_ID, -1);

        viewModel.getEntry(entryId).observe(this, resource -> {
            // TODO populate ui
            Log.d("TAG", "ASF");
        });
    }

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, EntryActivity.class);
        intent.putExtra(EXTRA_ENTRY_ID, id);
        context.startActivity(intent);
    }
}
