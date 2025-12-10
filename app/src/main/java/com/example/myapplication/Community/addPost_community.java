package com.example.myapplication.Community;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.supabaseSetup.storageClient;

public class addPost_community extends AppCompatActivity {

    private ActivityResultLauncher<Intent> imagePicker;
    private Uri selectedImageUri;

    EditText AddPostEditText;
    LinearLayout AddPostImageBtn;
    ImageView AddPostImageView;
    TextView imgName;
    Button btnPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_post_community);

        AddPostEditText = findViewById(R.id.AddPostEditText);
        AddPostImageBtn = findViewById(R.id.AddPostImageButton);
        AddPostImageView = findViewById(R.id.imageView);
        imgName = findViewById(R.id.textView);
        btnPost = findViewById(R.id.btnPost);

        final String[] image = {""};
        final String[] desc = {""};






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

                            storageClient storage = new storageClient(this);
                            storage.uploadImage(selectedImageUri, "Community", new storageClient.UploadCallback() {
                                @Override
                                public void onSuccess(String publicUrl) {
                                    Log.d("SUPABASE BUCKET", "Image Uploaded: " + publicUrl);
                                    image[0] = publicUrl;
                                }

                                @Override
                                public void onError(String error) {
                                    Log.e("SUPABASE BUCKET", "Image Upload Error: " + error);
                                    image[0] = "no-image";
                                }
                            });

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
            desc[0] = AddPostEditText.getText().toString();
            JsonObject obj = new JsonObject();
            obj.addProperty("desc", desc[0]);
            obj.addProperty("img", image[0]);


    }
}