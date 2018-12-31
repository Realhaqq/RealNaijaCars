package ng.haqqq.a9jacars;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class CarsActivity extends AppCompatActivity {
    private SessionHandler session;
    private Context context;
    List<GetDataAdapter> GetDataAdapter1;
    RecyclerView recyclerView;
    GetDataAdapter getDataAdapter2;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    private ProgressDialog pDialog;
    ImageButton bt_back;
    ImageButton bt_search;
    TextView sfilter;

    String GET_JSON_DATA_HTTP_URL = "http://192.168.43.73/naijacars/Api/carlist.php";
    String JSON_IMAGE_TITLE_NAME = "title";
    String JSON_IMAGE_URL = "pic";
    String JSON_LOCATION = "location";
    String JSON_PRICE = "price";
    String JSON_ID = "id";
    String JSON_FULLNAME = "fullname";
    String JSON_EMAIL = "email";
    String JSON_PHONE = "phone";
    String JSON_DESCRIPTION = "description";
    String JSON_CATEGORY = "category";
    String JSON_TYPE = "type";
    String JSON_IMGS = "img_url";
    String JSON_NEG = "neg";

    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    public String adsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        displayLoader();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_activity);

//
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle(getString(R.string.app_name));
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        session = new SessionHandler(getApplicationContext());
        GetDataAdapter1 = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                User user = session.getUserDetails();
                Intent i = new Intent(CarsActivity.this, AdsDetail.class);
                i.putExtra("ID", GetDataAdapter1.get(position).getId());
                i.putExtra("TITLE", GetDataAdapter1.get(position).getImageTitleName());
                i.putExtra("CATEGORY", GetDataAdapter1.get(position).getCategory());
                i.putExtra("DESCRIPTION", GetDataAdapter1.get(position).getDescription());
                i.putExtra("PRICE", GetDataAdapter1.get(position).getPrice());
                i.putExtra("LOCATION", GetDataAdapter1.get(position).getLocation());
                i.putExtra("IMAGE", GetDataAdapter1.get(position).getImageServerUrl());
                i.putExtra("FULLNAME", GetDataAdapter1.get(position).getFullname());
                i.putExtra("EMAIL", GetDataAdapter1.get(position).getEmail());
                i.putExtra("PHONE", GetDataAdapter1.get(position).getPhone());
                i.putExtra("PIC", GetDataAdapter1.get(position).getImageServerUrl());
                i.putExtra("LINK", GetDataAdapter1.get(position).getId());
                i.putExtra("IMGS", GetDataAdapter1.get(position).getImgs());
                i.putExtra("uemail", user.getEmail());


                startActivity(i);
                bt_back = findViewById(R.id.bt_back);

                bt_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(CarsActivity.this, DashboardActivity.class);

                        startActivity(i);

                    }
                });

                bt_search = findViewById(R.id.btnsearch);
                sfilter = findViewById(R.id.et_search);
                final String filter = sfilter.getText().toString();

                bt_search.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        Intent i = new Intent(CarsActivity.this, SearchActivity.class);
                        i.putExtra("filter", filter);
                        startActivity(i);

                    }
                });




            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(CarsActivity.this, "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
            }



        }));

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

            getDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                getDataAdapter2.setImageTitleName(json.getString(JSON_IMAGE_TITLE_NAME));
                getDataAdapter2.setLocation(json.getString(JSON_LOCATION));
                getDataAdapter2.setPrice(json.getString(JSON_PRICE));
                getDataAdapter2.setImageServerUrl(json.getString(JSON_IMAGE_URL));
                getDataAdapter2.setId(json.getString(JSON_ID));
                getDataAdapter2.setCategory(json.getString(JSON_CATEGORY));
                getDataAdapter2.setType(json.getString(JSON_TYPE));
                getDataAdapter2.setCategory(json.getString(JSON_CATEGORY));
                getDataAdapter2.setFullname(json.getString(JSON_FULLNAME));
                getDataAdapter2.setPhone(json.getString(JSON_PHONE));
                getDataAdapter2.setEmail(json.getString(JSON_EMAIL));
                getDataAdapter2.setDescription(json.getString(JSON_DESCRIPTION));
                getDataAdapter2.setImgs(json.getString(JSON_IMGS));
                getDataAdapter2.setNeg(json.getString(JSON_NEG));



            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(getDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, this);
        recyclerView.setAdapter(recyclerViewadapter);
//        pDialog.dismiss();
    }



    private void displayLoader() {
        pDialog = new ProgressDialog(CarsActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.show();

    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

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
