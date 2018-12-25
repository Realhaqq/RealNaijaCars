package ng.haqqq.a9jacars;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Holder extends RecyclerView.ViewHolder {
    public TextView text;
    public Holder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.et_search);
    }
}
