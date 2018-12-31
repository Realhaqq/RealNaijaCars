package ng.haqqq.a9jacars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    Context context;

    List<GetDataAdapter> getDataAdapter;
    private List<GetDataAdapter> adsList;
    ImageLoader imageLoader1;


    private AdsAdapterListener listener;


    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);


        Picasso.with(context)
                .load(getDataAdapter1.getImageServerUrl())
//                .centerCrop(150, 150)
                .resize(150, 150)
                .placeholder(R.drawable.cars)
                .centerInside()
                .error(R.drawable.cars)
                .into(Viewholder.networkImageView);

       // Viewholder.networkImageView.setImageUrl(getDataAdapter1.getImageServerUrl(), imageLoader1);

        Viewholder.ImageTitleNameView.setText(getDataAdapter1.getImageTitleName());

        Viewholder.location.setText(getDataAdapter1.getLocation());

        Viewholder.price.setText("N" + getDataAdapter1.getPrice());
//
//        String n = getDataAdapter1.getNeg();

        Viewholder.neg.setText(getDataAdapter1.getNeg());




    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView;
        public ImageView networkImageView ;
        public TextView location;
        public TextView price;
        public TextView neg;


        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleNameView = itemView.findViewById(R.id.textViewmodel) ;

            networkImageView =  itemView.findViewById(R.id.VollyNetworkImageView1) ;

            location = itemView.findViewById(R.id.txtlocation);

            price = itemView.findViewById(R.id.txtprice);
            neg = itemView.findViewById(R.id.btnneg);

        }
    }

    public interface AdsAdapterListener {
        void onContactSelected(GetDataAdapter ads);
    }
}