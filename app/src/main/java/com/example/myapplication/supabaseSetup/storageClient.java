package com.example.myapplication.supabaseSetup;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.myapplication.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class storageClient {

    private static final String SUPABASE_BUCKET_URL = BuildConfig.SUPABASE_BUCKET_URL;
    private static final String API_KEY = BuildConfig.SUPABASE_KEY;

    private final Context context;

    public storageClient(Context context) {
        this.context = context;
    }

    private byte[] getBytesFromUri(Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }

    public void uploadImage(Uri imageUri, String bucketName, UploadCallback callback) {

        new Thread(() -> {
            try {
                byte[] imageBytes = getBytesFromUri(imageUri);

                String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";
                String encodedFile = URLEncoder.encode(fileName, "UTF-8");

                Log.d("SUPABASE FileName", "File Name: " + fileName);

                String uploadUrl = SUPABASE_BUCKET_URL +
                        "/object/" +
                        bucketName + "/" +
                        encodedFile;


                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = RequestBody.create(
                        imageBytes,
                        MediaType.parse("image/jpeg")
                );

                Request request = new Request.Builder()
                        .url(uploadUrl)
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Content-Type", "image/jpeg")
                        .put(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {

                    String publicUrl = SUPABASE_BUCKET_URL +
                            "/object/public/" +
                            bucketName + "/" +
                            encodedFile;


                    Log.d("SUPABASE", "Upload Successful: " + fileName);
                    Log.d("SUPABASE", "Public URL: " + publicUrl);

                    callback.onSuccess(publicUrl);

                } else {

                    Log.e("SUPABASE", "Upload Failed Code: " + response.code());
                    Log.e("SUPABASE", "Body: " + response.body().string());
                    Log.e("SUPABASE", "URL Used: " + uploadUrl);

                    callback.onError("Upload failed: Code " + response.code());
                }

                response.close();

            } catch (Exception e) {
                e.printStackTrace();
                callback.onError("Error: " + e.getMessage());
            }

        }).start();
    }

    public interface UploadCallback {
        void onSuccess(String publicUrl);
        void onError(String error);
    }
}
