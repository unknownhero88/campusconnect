package com.example.myapplication.LostANDFound;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.authActivity.Login;
import com.example.myapplication.authActivity.SessionManager;
import com.example.myapplication.supabaseSetup.ApiClient;
import com.example.myapplication.supabaseSetup.storageClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_lost_found_items extends AppCompatActivity {

    MaterialButtonToggleGroup toggleGroup;
    MaterialButton btnLost, btnFound;
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


        SessionManager sessionManager = new SessionManager(this);

        if(!sessionManager.isLoggedIn())
        {
            startActivity(new Intent(this, Login.class));
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lost_found_items);

        toggleGroup = findViewById(R.id.toggleGroup);
        btnLost = findViewById(R.id.btnLost);
        btnFound = findViewById(R.id.btnFound);
        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);
        editTime = findViewById(R.id.editTime);
        editContact = findViewById(R.id.editContact);
        AddPostSpinner = findViewById(R.id.AddPostSpinner);
        AddPostImageBtn = findViewById(R.id.AddPostImageBtn);
        btnPost = findViewById(R.id.btnPost);
        AddPostImageView = findViewById(R.id.AddPostImageView);
        imgName = findViewById(R.id.imgName);

        String UID = getSharedPreferences("USER_DATA", MODE_PRIVATE)
                .getString("UID", "");


        storageClient storage = new storageClient(this);

        final Character[] type = {null};
        final String[] image = {""};

        ColorStateList Selected = ContextCompat.getColorStateList(this, R.color.deep_plum);
        ColorStateList unSelected = ContextCompat.getColorStateList(this, R.color.Pale_Purple);

        toggleGroup.setSingleSelection(true);
        btnLost.setBackgroundTintList(Selected);
        btnFound.setBackgroundTintList(unSelected);

        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if(!isChecked) return;

            if (isChecked) {
                if (checkedId == R.id.btnLost) {
                    type[0] = 'L';
                    btnLost.setBackgroundTintList(Selected);
                    btnFound.setBackgroundTintList(unSelected);

                }
                else if (checkedId == R.id.btnFound)
                {
                    type[0] = 'F';
                    btnLost.setBackgroundTintList(unSelected);
                    btnFound.setBackgroundTintList(Selected);
                }
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

            if (selectedImageUri == null) {
                Log.e("SUPABASE", "Please select an image first");
                return;
            }
            if (desc.isEmpty()) {
                Log.e("SUPABASE", "Description cannot be empty");
                return;
            }
            if (cat.equals("Select Category")) {
                Log.e("SUPABASE", "Category cannot be empty");
                return;
            }
            if (date.isEmpty()) {
                Log.e("SUPABASE", "Date cannot be empty");
                return;
            }
            if (time.isEmpty()) {
                Log.e("SUPABASE", "Time cannot be empty");
                return;
            }
            if (contact.isEmpty()) {
                Log.e("SUPABASE", "Contact cannot be empty");
                return;
            }
            if (type[0] == null) {
                Log.e("SUPABASE", "Type cannot be empty");
                return;
            }
            Log.d("SUPABASE", "Type: " + type[0]);

            // UPLOAD IMAGE FIRST
            storage.uploadImage(selectedImageUri, "LostANDFoundImages", new storageClient.UploadCallback() {
                @Override
                public void onSuccess(String publicUrl) {

                    Log.d("SUPABASE BUCKET", "Image Uploaded: " + publicUrl);

                    JsonObject obj = new JsonObject();
                    obj.addProperty("type", type[0]);
                    obj.addProperty("Desc", desc);
                    obj.addProperty("cat", cat);
                    obj.addProperty("datetime", dateTime);
                    obj.addProperty("contact info", contact);
                    obj.addProperty("img", publicUrl);
                    obj.addProperty("UID", UID);
                    obj.addProperty("status", "Pending");

                    lostfound_apiService api = ApiClient.getClient().create(lostfound_apiService.class);

                    api.insertEmp(obj).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            Log.d("SUPABASE", "Inserted: " + response.body());
                            Log.d("SUPABASE", "Inserted: " + response.errorBody());
                            Log.d("SUPABASE", "Inserted: " + response.code());
                            Log.d("SUPABASE", "Inserted: " + response.message());
                            Log.d("SUPABASE", "Inserted: " + response.raw());
                            Log.d("SUPABASE", "Inserted: " + response.isSuccessful());


                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            Log.e("SUPABASE", "Insert Error: " + t.getMessage());
                        }
                    });

                }

                @Override
                public void onError(String error) {
                    Log.e("SUPABASE BUCKET", "Image Upload Error: " + error);
                }
            });
        });

    }
}
