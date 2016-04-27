package com.biergarten;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class beerSearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_search_results);

        ImageView image1 = (ImageView) findViewById(R.id.beerImage1);
        ImageView image2 = (ImageView) findViewById(R.id.beerImage2);
        ImageView image3 = (ImageView) findViewById(R.id.beerImage3);

        File imgFile1 = new File("SprecherBlackBavarian.png");

        if(imgFile1.exists()){
    System.out.println("Image 1 exists");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
            image1.setImageBitmap(myBitmap);
        }
        else{System.out.println("Image 1 dun exists");}

        File imgFile2 = new File("shinerBlackLager.png");

        if(imgFile2.exists()){
            System.out.println("Image 2 exists");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            image2.setImageBitmap(myBitmap);
        }

        File imgFile3 = new File("samAdamsBlackLager.png");

        if(imgFile3.exists()){
            System.out.println("Image 3 exists");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile3.getAbsolutePath());
            image3.setImageBitmap(myBitmap);
        }

    }
}
