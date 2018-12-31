package ng.haqqq.a9jacars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {
    private SessionHandler session;
    List<MessageAdapter> GetDataAdapter1;
    RecyclerView recyclerView;
    MessageAdapter getDataAdapter2;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    Intent intent = new Intent();

    String GET_JSON_DATA_HTTP_URL;
    String JSON_FROM_USERID = "fromuserid";
    String JSON_TO_USERID = "touserid";
    String JSON_CONTENT = "content";
    String JSON_FULLNAME = "fullname";
    String JSON_DATE = "date";


    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new SessionHandler(getApplicationContext());
        GetDataAdapter1 = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewmesage);
        Intent intent = getIntent();
        GET_JSON_DATA_HTTP_URL = "http://192.168.43.73/naijacars/Api/messages.php?email="+ intent.getStringExtra("email");
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new MessagesActivity.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Intent i = new Intent(MessagesActivity.this, MessageView.class);
                i.putExtra("FROMUSERID", GetDataAdapter1.get(position).getFromuserid());
                i.putExtra("TOUSERID", GetDataAdapter1.get(position).getTouserid());
                i.putExtra("CONTENT", GetDataAdapter1.get(position).getContent());
                i.putExtra("FULLNAME", GetDataAdapter1.get(position).getFullname());
                i.putExtra("DATE", GetDataAdapter1.get(position).getDate());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MessagesActivity.this, "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_about){
            Intent i = new Intent(MessagesActivity.this, About.class);

            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void JSON_DATA_WEB_CALL() {

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            getDataAdapter2 = new MessageAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                getDataAdapter2.setFromuserid(json.getString(JSON_FROM_USERID));
                getDataAdapter2.setTouserid(json.getString(JSON_TO_USERID));
                getDataAdapter2.setContent(json.getString(JSON_CONTENT));
                getDataAdapter2.setFullname(json.getString(JSON_FULLNAME));
                getDataAdapter2.setDate(json.getString(JSON_DATE));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(getDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewMessage(GetDataAdapter1, this);
        recyclerView.setAdapter(recyclerViewadapter);
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private MessagesActivity.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final MessagesActivity.ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }
}
