package com.example.recyclerview;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class B_RecyclerViewAdapter extends RecyclerView.Adapter<B_RecyclerViewAdapter.MyViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<BratzModel> bratzModels;
    public B_RecyclerViewAdapter(Context context, ArrayList<BratzModel> bratzModels, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.bratzModels = bratzModels;
        this.recyclerViewInterface = recyclerViewInterface;
   }

    @NonNull
    @Override
    public B_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new B_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull B_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        holder.tvName.setText(bratzModels.get(position).getBratzName());
        holder.tvNickname.setText(bratzModels.get(position).getBratzNickname());
        holder.tvChType.setText(bratzModels.get(position).getBratzCharacterType());
        // holder.imageView.setImageResource(bratzModels.get(position).getImage());
        holder.imageView.setImageBitmap(bratzModels.get(position).getImage());

//        holder.itemView.setOnClickListener(view -> {
//            Log.d(TAG, "onclick: clicked on:" + bratzModels.get(position).getBratzName());
//
//            Toast.makeText(this.context, bratzModels.get(position).getBratzName(), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this.context, GalleryActivity.class);
//            intent.putExtra("bratz_image", bratzModels.get(position).getImage());
//            intent.putExtra("bratz_name", bratzModels.get(position).getBratzName());
//            context.startActivity(intent);
//
//        });

//        holder.itemView.setOnClickListener(view -> {log.d(TAG, "onClick: clicked on: " + bratzModels.get(position).getBratzName());
//
//
//        });
////        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentB fragmentB = new FragmentB();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("Name", bratzModels.get(position).getBratzName());
//                fragmentB.setArguments(bundle);
//                if (context instanceof FragmentActivity) {
//                    FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.mainActivity, fragmentB);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }
//            }
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, FragmentB.class);
//                intent.putExtra("Name", bratzModels.get(position).getBratzName());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return bratzModels.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from our recycler_view_row layout file
        //kind of like in the onCreate method

        ImageView imageView;

        TextView tvName, tvNickname, tvChType;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);
            tvNickname = itemView.findViewById(R.id.textView2);
            tvChType = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null)
                    {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);

                        }

                    }
                }
            });

        }
    }
}
