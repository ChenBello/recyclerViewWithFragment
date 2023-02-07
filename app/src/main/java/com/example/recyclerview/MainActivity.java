package com.example.recyclerview;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Resources;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    ArrayList<BratzModel> bratzModels = new ArrayList<>();
    int[] bratzImages = {R.drawable.jade, R.drawable.cloe,R.drawable.sasha, R.drawable.yasmin,R.drawable.kumi,R.drawable.nevra, R.drawable.meygan, R.drawable.raya, R.drawable.roxxi, R.drawable.pheobe, R.drawable.kiana};

//    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerView = findViewById(R.id.mRecyclerView);


//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        recyclerView.setLayoutManager(layoutManager);

//        List<BratzModel> bratzModelList = new ArrayList<>();

//        bratzModelList.add(new BratzModel(R.drawable.jade, "Jade","Hello my name is Jade");
//        bratzModelList.add(new BratzModel(R.drawable.cloe, "Cloe","Hello my name is Cloe");
//        bratzModelList.add(new BratzModel(R.drawable.sasha, "Sasha","Hello my name is Sasha");
//        bratzModelList.add(new BratzModel(R.drawable.yasmin, "Yasmin","Hello my name is Yasmin");
//        bratzModelList.add(new BratzModel(R.drawable.kumi, "Kumi","Hello my name is Kumi");
//        bratzModelList.add(new BratzModel(R.drawable.nevra, "Nevra","Hello my name is Nevra");
//        bratzModelList.add(new BratzModel(R.drawable.meygan, "Meygan","Hello my name is Meygan");
//        bratzModelList.add(new BratzModel(R.drawable.raya, "Raya","Hello my name is Raya");
//        bratzModelList.add(new BratzModel(R.drawable.roxxi, "Roxxi","Hello my name is Roxxi");
//        bratzModelList.add(new BratzModel(R.drawable.pheobe, "Pheobe","Hello my name is Pheobe");
//        bratzModelList.add(new BratzModel(R.drawable.kiana, "Jade","Hello my name is Kiana");

        // btnopen=findViewById(R.id.mRecyclerView);
        //getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragmentB()).addToBackStack(null).commit();

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpBratzModels();

        B_RecyclerViewAdapter adapter = new B_RecyclerViewAdapter(this, bratzModels, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.notifyDataSetChanged();

//        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
//        String B_image = getIntent().getStringExtra("bratz_image");
//        String B_name = getIntent().getStringExtra("bratz_name");
//        intent.putExtra("bratz_image", B_image);
//        intent.putExtra("bratz_name", B_name);
//        startActivity(intent);

        //getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragmentB()).addToBackStack(null).commit();


    }
    private Bitmap decodeBitmap(Resources res, int resId, int reqWidth, int reqHeight){
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
    private void setUpBratzModels(){
        String[] bratzNames = getResources().getStringArray(R.array.Bratz_txt);
        String[] bratzChType = getResources().getStringArray(R.array.characters_txt);
        String[] bratzNickname = getResources().getStringArray(R.array.bratz_nicknames_txt);
        String[] bratzDescription = getResources().getStringArray(R.array.bratz_description_txt);

        for (int i = 0; i < bratzNames.length; i++ ){
            // bratzModels.add(new BratzModel(bratzNames[i], bratzNickname[i], bratzChType[i], bratzImages[i]));
            Bitmap bitmap = decodeBitmap(getResources(), bratzImages[i], 100, 100);
            bratzModels.add(new BratzModel(bratzNames[i], bratzNickname[i], bratzChType[i], bitmap, bratzDescription[i]));

        }

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bratzModels.get(position).getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        intent.putExtra("bratz_image", byteArray);
//        intent.putExtra("bratz_image", bratzImages[position]);
//        intent.putExtra("bratz_image", bratzModels.get(position).getImage());
        intent.putExtra("bratz_name", bratzModels.get(position).getBratzName());
//        intent.putExtra("bratz_desc", bratzModels.get(position).getDescription());
        String[] bratzDescription = getResources().getStringArray(R.array.bratz_description_txt);
        intent.putExtra("bratz_desc", bratzDescription[position]);


        MainActivity.this.startActivity(intent);
    }
}