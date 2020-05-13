package br.edu.unicsul.agendatelefonica.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.unicsul.agendatelefonica.R;
import br.edu.unicsul.agendatelefonica.models.Contact;
import br.edu.unicsul.agendatelefonica.ui.adapters.EmailsAdapter;
import br.edu.unicsul.agendatelefonica.ui.adapters.PhonesAdapter;

public class EmailActivity extends AppCompatActivity implements EmailsAdapter.ItemClickListener {

    RecyclerView mEmailsListRecycleView;
    EmailsAdapter mListAdapter;
    List<Contact> mContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(MainActivity.EXTRA_CONTACTS)){

            mContacts = intent.getParcelableArrayListExtra(MainActivity.EXTRA_CONTACTS);

        }

        setupComponents();


    }

    public void setupComponents(){



        mEmailsListRecycleView = findViewById(R.id.rv_email_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mEmailsListRecycleView.setLayoutManager(layoutManager);

        mListAdapter = new EmailsAdapter(mContacts, this);

        mEmailsListRecycleView.setAdapter(mListAdapter);

    }

    @Override
    public void onClick(Contact selectedContact) {
        String uri = "mailto:" + selectedContact.getEmail();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse(uri));
        startActivity(Intent.createChooser(intent, "Enviar E-Mail"));
    }
}
