package com.example.myapplication.supabaseSetup;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

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

    private static final String SUPABASE_URL = "https://njzjssodtktnscxcfchl.supabase.co";
    private static final String API_KEY = "YOUR_SUPABASE_API_KEY";

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

                String encodedBucket = URLEncoder.encode(bucketName, "UTF-8");

                String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";

                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = RequestBody.create(
                        imageBytes,
                        MediaType.parse("image/jpeg")
                );

                // Upload URL
                String uploadUrl = SUPABASE_URL + "/storage/v1/object/" +
                        encodedBucket + "/" + fileName;

                Request request = new Request.Builder()
                        .url(uploadUrl)
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Content-Type", "image/jpeg")
                        .put(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {

                    // Public URL
                    String publicUrl = SUPABASE_URL +
                            "/storage/v1/object/public/" +
                            encodedBucket + "/" +
                            fileName;

                    Log.d("SUPABASE", "File Uploaded: " + fileName);
                    Log.d("SUPABASE", "URL: " + publicUrl);

                    callback.onSuccess(publicUrl);

                } else {
                    Log.e("SUPABASE", "Upload failed: " + response.message());
                    callback.onError("Supabase Upload Failed: " + response.message());
                }

            } catch (Exception e) {
                e.printStackTrace();
                callback.onError(e.getMessage());
            }
        }).start();
    }

    public interface UploadCallback {
        void onSuccess(String publicUrl);
        void onError(String error);
    }
}
