package com.example.exampleex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contacts = new ArrayList<>();

        fetchContacts();
    }

    private void fetchContacts() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://ccardoso.multics.org/fca")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    // Aquí debes parsear la respuesta y llenar la lista de contactos
                    // contacts.addAll(parsedContacts);

                    runOnUiThread(() -> {
                        contactAdapter = new ContactAdapter(contacts, contact -> {
                            Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                            intent.putExtra("contact_info", contact.getInfo());
                            startActivity(intent);
                        });
                        recyclerView.setAdapter(contactAdapter);
                    });
                }
            }
        });
    }
}