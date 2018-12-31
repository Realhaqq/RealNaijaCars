package ng.haqqq.a9jacars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

public class RecyclerViewImages extends RecyclerView.Adapter<RecyclerViewImages.ViewHolder> {

    Context context;

    List<GetImageAdapter> getImageAdapter;

    ImageLoader imageLoader1;



    public RecyclerViewImages(List<GetImageAdapter> getImageAdapter, Context context){

        super();
        this.getImageAdapter = getImageAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitems_images, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        GetImageAdapter getImageAdapter1 =  getImageAdapter.get(position);


        Picasso.with(context)
                .load(getImageAdapter1.getImageurl())
//                .centerCrop(150, 150)
                .resize(150, 150)
                .placeholder(R.drawable.icars)
                .centerInside()
                .error(R.drawable.cars)
                .into(Viewholder.networkImageView);

        // Viewholder.networkImageView.setImageUrl(getDataAdapter1.getImageServerUrl(), imageLoader1);






    }


    @Override
    public int getItemCount() {

        return getImageAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView networkImageView ;



        public ViewHolder(View itemView) {

            super(itemView);
            networkImageView =  itemView.findViewById(R.id.rimage);


        }
    }
}
