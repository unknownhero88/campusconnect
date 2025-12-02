package com.example.myapplication.LostANDFound;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.supabaseSetup.ApiClient;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_lost_found_items extends AppCompatActivity {

    MaterialButtonToggleGroup toggleGroup;
    EditText editDescription, editDate, editTime, editContact;
    Spinner AddPostSpinner;
    LinearLayout AddPostImageBtn;
    Button btnPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lost_found_items);

        toggleGroup = findViewById(R.id.toggleGroup);
        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);
        editTime = findViewById(R.id.editTime);
        editContact = findViewById(R.id.editContact);
        AddPostSpinner = findViewById(R.id.AddPostSpinner);
        AddPostImageBtn = findViewById(R.id.AddPostImageBtn);
        btnPost = findViewById(R.id.btnPost);


        Character type[] = new Character[1];
        final String[] desc = {""};
        final String[] cat = {""};
        final String[] date = {""};
        final String[] time = {""};
        final String[] dateTime = {""};
        final String[] contact = {""};
        final String[] image = {""};

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desc[0] = editDescription.getText().toString();
                cat[0] = AddPostSpinner.getSelectedItem().toString();
                date[0] = editDate.getText().toString();
                time[0] = editTime.getText().toString();
                dateTime[0] = date[0] + " " + time[0];
                contact[0] = editContact.getText().toString();
                image[0] = "image";
            }
        });

        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.btnLost) {
                        type[0] = 'L';
                    } else if (checkedId == R.id.btnFound) {
                        type[0] = 'F';
                    }
                }
            }
        });


        lostfound_apiService api = ApiClient.getClient().create(lostfound_apiService.class);

        JsonObject obj = new JsonObject();
        obj.addProperty("type", type[0]);
        obj.addProperty("desc", desc[0]);
        obj.addProperty("cat", cat[0]);
        obj.addProperty("dateTime", dateTime[0]);
        obj.addProperty("contact", contact[0]);
        obj.addProperty("image", image[0]);
        obj.addProperty("UID", 1);
        obj.addProperty("status", "Pending");

        api.insertEmp(obj).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("SUPABASE", "Inserted: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("SUPABASE", t.getMessage());
            }
        });

    }
}