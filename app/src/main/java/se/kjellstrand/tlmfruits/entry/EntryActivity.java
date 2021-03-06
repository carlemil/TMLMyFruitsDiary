package se.kjellstrand.tlmfruits.entry;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import se.kjellstrand.tlmfruits.R;
import se.kjellstrand.tlmfruits.model.Entry;
import se.kjellstrand.tlmfruits.model.EntryFruit;
import se.kjellstrand.tlmfruits.model.Fruit;
import se.kjellstrand.tlmfruits.repo.FruitsDiaryRepository;

public class EntryActivity extends AppCompatActivity {

    private static final String EXTRA_ENTRY_ID = "EXTRA_ENTRY_ID";

    private String entryId = "-1";
    private EntryViewModel viewModel;

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, EntryActivity.class);
        intent.putExtra(EXTRA_ENTRY_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        viewModel = ViewModelProviders.of(this).get(EntryViewModel.class);

        Intent intent = getIntent();
        entryId = intent.getStringExtra(EXTRA_ENTRY_ID);

        viewModel.getFruits().observe(this, fruitResource -> {
            final Map<Integer, Fruit> fruits = new HashMap<>();
            assert fruitResource != null;
            fruitResource.data.forEach(fruit -> fruits.put(fruit.id, fruit));
            viewModel.getEntry(entryId).observe(this, entryResource -> {
                switch (entryResource.status) {
                    case SUCCESS:
                        Entry entry = entryResource.data;
                        getSupportActionBar().setTitle(entry.id + " - " + entry.date);

                        LinearLayout root = findViewById(R.id.activity_entry_root);
                        entry.entryFruit.stream().forEach(entryFruit -> root.addView(inflateAndPopulateFruitLayout(fruits, entryFruit)));
                        break;
                    case ERROR:
                        showToast("Failed to load Entry.");
                        break;
                }
            });
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private View inflateAndPopulateFruitLayout(Map<Integer, Fruit> fruits, EntryFruit entryFruit) {
        // TODO move this to a custom view that hides the inflation and findview stuff
        View root = getLayoutInflater().inflate(R.layout.activity_entry_fruit_list_item, null);
        TextView textView = root.findViewById(R.id.textView);
        ImageView imageView = root.findViewById(R.id.imageView);
        Fruit fruit = fruits.get(entryFruit.fruitId);
        textView.setText(String.format(getString(R.string.you_had), entryFruit.amount));
        // Can we move this out of the activity and into the viewModel somehow?
        int imageSize = getResources().getDimensionPixelSize(R.dimen.fruit_image_size);
        Picasso.with(getBaseContext())
                .load(FruitsDiaryRepository.BASE_URL + fruit.image)
                .resize(imageSize, imageSize)
                .into(imageView);
        return root;
    }
}
