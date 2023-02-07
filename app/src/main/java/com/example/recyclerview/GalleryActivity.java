package com.example.recyclerview;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG = "GalleryActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");

        this.getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("bratz_image") && getIntent().hasExtra("bratz_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            int B_image = getIntent().getIntExtra("bratz_image",0);
            String B_name = getIntent().getStringExtra("bratz_name");
            String B_desc = getIntent().getStringExtra("bratz_desc");
            setImage(B_image,B_name, B_desc);
        }
    }
    private void setImage(int B_image, String B_name, String B_desc){
        Log.d(TAG, "setImage: setting the image and name to widgets.");
        TextView name = findViewById(R.id.descNameID);
        name.setText(B_name);
        TextView desc = findViewById(R.id.descriptionID);
        desc.setText(B_desc);
        ImageView image = findViewById(R.id.imageViewGallery);
        Glide.with(this)
                .asBitmap()
                .load(B_image)
                .into(image);
    }

}
