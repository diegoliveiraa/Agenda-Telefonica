package br.edu.unicsul.agendatelefonica.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import br.edu.unicsul.agendatelefonica.R;
import br.edu.unicsul.agendatelefonica.models.Developer;
import br.edu.unicsul.agendatelefonica.ui.adapters.DevAdapter;
import br.edu.unicsul.agendatelefonica.ui.adapters.PhonesAdapter;

public class AboutActivity extends AppCompatActivity {

    RecyclerView mDevListRecycleView;
    DevAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setupComponents();


    }

    public void setupComponents(){



        mDevListRecycleView = findViewById(R.id.rv_about_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mDevListRecycleView.setLayoutManager(layoutManager);

        mListAdapter = new DevAdapter(Developer.GetProjectDevs());

        mDevListRecycleView.setAdapter(mListAdapter);

    }
}
