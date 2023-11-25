package com.example.pokemon_dex.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class FetchApi {

    public static String getPokemonByID(int id){
        return null;
    }
    public static Bitmap getImageByURL(String url, Activity activity, int id) {
        AtomicReference<Bitmap> image = new AtomicReference<>();
        Thread t = new Thread(() -> {
            try {
                System.out.println("to tentando pegar a imagem");
                InputStream in = new URL(url).openStream();
                image.set(BitmapFactory.decodeStream(in));

            } catch (Exception e) {
                System.out.println("deu erro");
                e.printStackTrace();
            }
            activity.runOnUiThread(() -> {
                    System.out.println("tentando setar a imagem");
            System.out.println(image);
            if (image != null) {
                ImageView img = activity.findViewById(id);
                img.setImageBitmap(image.get());
            }
            });
        });
        t.start();
        return image.get();
    }

}
