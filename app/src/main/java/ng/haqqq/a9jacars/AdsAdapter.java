package ng.haqqq.a9jacars;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 03-Jan-17.
 */
public class AdsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AdsModelUser> playersModelArrayList;

    public AdsAdapter(Context context, ArrayList<AdsModelUser> playersModelArrayList) {

        this.context = context;
        this.playersModelArrayList = playersModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return playersModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return playersModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
//
//            holder.tvname = (TextView) convertView.findViewById(R.id.txtuser);

//
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        Intent i = new Intent();
//        i.putExtra("cellphone", playersModelArrayList.get(position).getPhone());
//        holder.tvname.setText("Name: "+playersModelArrayList.get(position).getFullname());
//        holder.email.setText(playersModelArrayList.get(position).getEmail());
//        holder.phone.setText(playersModelArrayList.get(position).getPhone());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, phone, email;
    }

}