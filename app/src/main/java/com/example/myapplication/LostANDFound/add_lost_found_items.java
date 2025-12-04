package com.example.myapplication.LostANDFound;



import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.supabaseSetup.ApiClient;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_lost_found_items extends AppCompatActivity {

    MaterialButtonToggleGroup toggleGroup;
    EditText editDescription, editContact;
    TextView editDate, editTime,imgName;
    Spinner AddPostSpinner;
    LinearLayout AddPostImageBtn;
    Button btnPost;
    ImageView AddPostImageView;

    private ActivityResultLauncher<Intent> imagePicker;
    private Uri selectedImageUri;

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
        AddPostImageView = findViewById(R.id.AddPostImageView);
        imgName = findViewById(R.id.imgName);


        final Character[] type = {null};
        final String[] image = {""};
        toggleGroup.setSingleSelection(true);
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.btnLost) type[0] = 'L';
                else if (checkedId == R.id.btnFound) type[0] = 'F';
            }
        });

        String[] cat_Items ={"Select Category","Electroinc","Books","Bag","Clothes","Others"};

        ArrayAdapter<String> cat_adapter = new ArrayAdapter<>(this, R.layout.spinner,R.id.spinnerText, cat_Items);

        cat_adapter.setDropDownViewResource(R.layout.dropdown_item);

        AddPostSpinner.setAdapter(cat_adapter);

        AddPostSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selectedItem = parent.getItemAtPosition(position).toString();
            }};
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String selectedItem = parent.getItemAtPosition(parent.getCount()-1).toString();
            }
        });



        editDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                month+=1;
                String date = dayOfMonth + "-" + month + "-" + year;
                editDate.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        editTime.setOnClickListener(v -> {
            Calendar calendar1 = Calendar.getInstance();
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String time = hourOfDay + ":" + minute;
                editTime.setText(time);
            }, calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), true).show();
        });

        imagePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            selectedImageUri = data.getData();
                            AddPostImageView.setImageURI(selectedImageUri);
                            AddPostImageView.setVisibility(View.VISIBLE);
                            imgName.setText(selectedImageUri.getLastPathSegment());
                            imgName.setVisibility(View.VISIBLE);
                            AddPostImageBtn.setBackgroundResource(R.drawable.edit_text);
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                        getContentResolver(),
                                        selectedImageUri
                                );

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        AddPostImageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePicker.launch(intent);
        });




        btnPost.setOnClickListener(view -> {

            String desc = editDescription.getText().toString();
            String cat = AddPostSpinner.getSelectedItem().toString();
            String date = editDate.getText().toString();
            String time = editTime.getText().toString();
            String dateTime = date + " " + time;
            String contact = editContact.getText().toString();

            if (selectedImageUri != null) {
                image[0] = selectedImageUri.toString();
            } else {
                image[0] = "no-image";
            }

            JsonObject obj = new JsonObject();
            obj.addProperty("type", type[0]);
            obj.addProperty("Desc", desc);
            obj.addProperty("cat", cat);
            obj.addProperty("datetime", dateTime);
            obj.addProperty("contact info", contact);
            obj.addProperty("img", image[0]);
            obj.addProperty("UID", 1);
            obj.addProperty("status", "Pending");


            lostfound_apiService api = ApiClient.getClient().create(lostfound_apiService.class);

            api.insertEmp(obj).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    Log.d("SUPABASE", "Inserted: " + response.body());
                    Log.d("SUPABASE", "Code: " + response.code());
                    Log.d("SUPABASE", "Raw: " + response.raw());
                    Log.d("SUPABASE", "Error: " + response.errorBody());

                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.e("SUPABASE", "Error: " + t.getMessage());
                }
            });
        });
    }
}
