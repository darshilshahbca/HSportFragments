package com.example.hsport.catalog;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String productId = getIntent().getStringExtra(MainActivity.PRODUCT_ID);
        final Product product = DataProvider.productMap.get(productId);

        TextView tv = (TextView) findViewById(R.id.nameText);
        tv.setText(product.getName());

        TextView descView = (TextView) findViewById(R.id.descriptionText);
        descView.setText(product.getDescription());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(product.getPrice());
        TextView priceText = (TextView) findViewById(R.id.priceText);
        priceText.setText(price);

        ImageView iv = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        iv.setImageBitmap(bitmap);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(productId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
