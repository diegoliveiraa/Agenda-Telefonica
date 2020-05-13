package br.edu.unicsul.agendatelefonica.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.unicsul.agendatelefonica.R;
import br.edu.unicsul.agendatelefonica.models.Contact;
import br.edu.unicsul.agendatelefonica.ui.adapters.PhonesAdapter;

public class PhoneActivity extends AppCompatActivity implements PhonesAdapter.ItemClickListener {


    RecyclerView mPhoneListRecycleView;
    PhonesAdapter mListAdapter;
    List <Contact> mContacts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(MainActivity.EXTRA_CONTACTS)){

            mContacts = intent.getParcelableArrayListExtra(MainActivity.EXTRA_CONTACTS);

        }

        setupComponents();


    }



    public void setupComponents(){



        mPhoneListRecycleView = findViewById(R.id.rv_phone_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mPhoneListRecycleView.setLayoutManager(layoutManager);

        mListAdapter = new PhonesAdapter(mContacts, this);

        mPhoneListRecycleView.setAdapter(mListAdapter);

    }

    @Override
    public void onClick(Contact selectedContact) {
        String uri = "tel: " + selectedContact.getCelular();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
