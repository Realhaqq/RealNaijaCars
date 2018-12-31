package ng.haqqq.a9jacars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewMessage extends RecyclerView.Adapter<RecyclerViewMessage.ViewHolder> {

    Context context;

    List<MessageAdapter> getDataAdapter;

    ImageLoader imageLoader1;



    public RecyclerViewMessage(List<MessageAdapter> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        MessageAdapter getDataAdapter1 =  getDataAdapter.get(position);


        Viewholder.fullname.setText(getDataAdapter1.getFullname());
        Viewholder.content.setText(getDataAdapter1.getContent());
        Viewholder.fromuserid.setText(getDataAdapter1.getFromuserid());
        Viewholder.mdate.setText(getDataAdapter1.getDate());







    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView fromuserid;
        public TextView content;
        public TextView fullname;
        public TextView mdate;



        public ViewHolder(View itemView) {

            super(itemView);

            fullname = itemView.findViewById(R.id.txtuser) ;
            content = itemView.findViewById(R.id.mmessage);
            fromuserid = itemView.findViewById(R.id.memail);
            mdate = itemView.findViewById(R.id.mdate);




        }
    }
}