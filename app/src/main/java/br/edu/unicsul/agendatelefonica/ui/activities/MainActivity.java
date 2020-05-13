package br.edu.unicsul.agendatelefonica.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.unicsul.agendatelefonica.R;
import br.edu.unicsul.agendatelefonica.helpers.JsonHelper;
import br.edu.unicsul.agendatelefonica.models.Contact;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACTS = "br.edu.unicsul.agendatelefonica.EXTRA_CONTACTS";

    Button mAboutButton;
    Button mPhoneButton;
    Button mEmailButton;

    JsonHelper mJsonHelper;
    List<Contact> mContacts = new ArrayList<>();// prevent nullPointerException

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupComponents();

        mJsonHelper = new JsonHelper(new JsonHelper.JsonCallback() {
            @Override
            public void onJsonDownloaded(String json) {
                mContacts = parseJsonToContactList(json);
                Toast.makeText(MainActivity.this, "Contacts Downloaded", Toast.LENGTH_SHORT).show();
                Log.d("xxx", "onJsonDownloaded: " + String.valueOf(mContacts));
            }
        });

        mJsonHelper.downloadJson("http://mfpledon.com.br/listadecontatosbck.json");
    }


    private void setupComponents() {

        mAboutButton = findViewById(R.id.btn_about);
        mAboutButton.setOnClickListener(view -> goToActivity(AboutActivity.class));
        mPhoneButton = findViewById(R.id.btn_phone);
        mPhoneButton.setOnClickListener(view -> goToActivity(PhoneActivity.class));
        mEmailButton = findViewById(R.id.btn_email);
        mEmailButton.setOnClickListener(view -> goToActivity(EmailActivity.class));
    }

    private List<Contact> parseJsonToContactList(String strjson) {

        List<Contact> contacts = new ArrayList<>();

        if (strjson == null || strjson.isEmpty()) {
            return contacts;
        }

        JSONObject rootObject = null;
        try {
            rootObject = new JSONObject(strjson);
            JSONArray jsonArray = rootObject.optJSONArray("listacontatos");
            JSONObject jsonObject = null;

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("nomecontato");
                String email = jsonObject.getString("email");
                String address = jsonObject.getString("endereco");
                String gender = jsonObject.getString("genero");
                String phone = jsonObject.getString("celular");

                contacts.add(new Contact(id, name, email, address, gender, phone));

            }

            } catch(JSONException e){
                e.printStackTrace();
            }


            return contacts;
        }

        /**
         * Launches any activity
         * @param activity Any class that extends the {@link Activity}
         */
        private void goToActivity (Class < ? extends Activity > activity){
            Intent intent = new Intent(this, activity);
            intent.putParcelableArrayListExtra(EXTRA_CONTACTS, (ArrayList<? extends Parcelable>) mContacts);
            startActivity(intent);

        }
    }
