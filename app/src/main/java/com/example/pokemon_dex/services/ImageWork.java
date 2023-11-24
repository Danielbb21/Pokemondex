package com.example.pokemon_dex.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageWork {
    
    public Bitmap getImageByURL(String url) {
        // Declaring executor to parse the URL 
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Once the executor parses the URL  
        // and receives the image, handler will load it 
        // in the ImageView 
        Handler handler = new Handler(Looper.getMainLooper());

        // Initializing the image 
        final Bitmap[] imageFinal = {null};

        // Only for Background process (can take time depending on the Internet speed) 
        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Image URL
                        String imageURL = "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png";

                        Bitmap image = null;
                        try {
                            InputStream in = new URL(imageURL).openStream();
                            image = BitmapFactory.decodeStream(in);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        imageFinal[0] = image;
                    }
                });
            }
        });
    return imageFinal[0];
    }
}
