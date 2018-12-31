package ng.haqqq.a9jacars;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdsDetail extends AppCompatActivity {
    private SessionHandler session;
    public static final String KEY_USERID = "userid";
    public static final String KEY_ADSID = "adsid";
    public static final String KEY_FULLNAME = "adsid";
    private Context context;
//    private ArrayList<AdsModelUser> playersModelArrayList;

    //    public static final String KEY_CATEGORY = "category";
    List<GetImageAdapter> GetImageAdapter1;
    RecyclerView recyclerView;
    GetImageAdapter getImageAdapter2;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewimages;
    String GET_JSON_DATA_HTTP_URL;
    String JSON_IMAGE_URL = "img_url";


    private String userid;
    private String adsid;
//    String cellphone;
//    private ParseContent parseContent;
//    private final int jsoncode = 1;
//    private ListView listView;
    //    private ArrayList<AdsModelUser> playersModelArrayList;
//    private AdsAdapter customeAdapter;
    //    private String addfav = "http://192.168.43.73/naijacars/Api/addtofav.php";
    public static final String post_url = "http://192.168.43.73/naijacars/Api/tofav.php";
//    public static final String user_url = "http://192.168.43.73/naijacars/Api/users.php?userid=haqq4peace@gmail.com";
    JsonArrayRequest jsonArrayRequest;
    //    Context context;
    ImageButton btnaddtofav;
    ImageButton sharebtn;
    RequestQueue requestQueue;
    ImageLoader imageLoader1;
    private ProgressDialog pDialog;
    ArrayList<String> imageArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ads_detail);
//
//        parseContent = new ParseContent(this);

//        try {
//            parseJson();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        GetImageAdapter1 = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewimages);

        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();

        recyclerView.addOnItemTouchListener(new AdsDetail.RecyclerTouchListener(this,
                recyclerView, new AdsDetail.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                ImageView pic = findViewById(R.id.pic);
                Picasso.with(context)
                        .load(GetImageAdapter1.get(position).getImageurl())
//                .centerCrop(150, 150)
                        .resize(150, 150)
                        .placeholder(R.drawable.cars)
                        .centerInside()
                        .error(R.drawable.cars)
                        .into(pic);

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
        btnaddtofav = findViewById(R.id.bt_fav);
        Intent intent = getIntent();
        userid = intent.getStringExtra("uemail");
        adsid = intent.getStringExtra("ID");
//        imagesUrl = intent.getStringExtra("IMGS");
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        GET_JSON_DATA_HTTP_URL = "http://192.168.43.73/naijacars/Api/getimages.php?id=" + intent.getStringExtra("ID");
        sharebtn = findViewById(R.id.bt_share);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "9ja Cars" + intent.getStringExtra("TITLE"));
                share.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("TITLE") + "Download Naija Best Car Ads Android Application");
                startActivity(share);

            }
        });

        AppCompatButton callbtn = findViewById(R.id.btncall);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();


                Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+intent.getStringExtra("PHONE")));
                try {
                    if (ActivityCompat.checkSelfPermission(AdsDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(in);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AdsDetail.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
        }
        });

//        AdsDetailAdapter adsDetailAdapter = new AdsDetailAdapter();

        TextView title = findViewById(R.id.txttitle);
       title.setText(intent.getStringExtra("TITLE"));

       TextView adtitle = findViewById(R.id.adtitle);
       adtitle.setText(intent.getStringExtra("TITLE"));

        TextView price = findViewById(R.id.txtprice);
        price.setText("N" + intent.getStringExtra("PRICE"));

        TextView users = findViewById(R.id.txtuser);
        users.setText(intent.getStringExtra("FULLNAME"));

        TextView desc = findViewById(R.id.txtdescription);
        desc.setText(intent.getStringExtra("DESCRIPTION"));

        TextView location = findViewById(R.id.txtlocation);
        location.setText(intent.getStringExtra("LOCATION"));
ImageView img = findViewById(R.id.pic);
ImageView imgs = findViewById(R.id.image_1);
Picasso.with(context)
                .load(intent.getStringExtra("PIC"))
//                .centerCrop(150, 150)
                .resize(150, 150)
                .placeholder(R.drawable.cars)
                .centerInside()
                .error(R.drawable.cars)
                .into(img);

        JSON_DATA_WEB_CALL();



        btnaddtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                Addtofav();

            }


        });
        Button btnmessage = findViewById(R.id.btnmesage);

        btnmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                User user = session.getUserDetails();

                Intent i = new Intent(AdsDetail.this, SendMessage.class);
                i.putExtra("TOEMAIL", intent.getStringExtra("uemail"));

                i.putExtra("FROMEMAIL", intent.getStringExtra("EMAIL"));
                startActivity(i);
            }
        });





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

            getImageAdapter2 = new GetImageAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                getImageAdapter2.setImageurl(json.getString(JSON_IMAGE_URL));
            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetImageAdapter1.add(getImageAdapter2);
        }

        recyclerViewimages = new RecyclerViewImages(GetImageAdapter1, this);
        recyclerView.setAdapter(recyclerViewimages);

    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private AdsDetail.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final AdsDetail.ClickListener clicklistener) {

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


    private void Addtofav(){
        class addtofav extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdsDetail.this, "Adding to Favourites....", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Intent intent = getIntent();
//                id = UUID.randomUUID().toString();
                HashMap<String,String> data = new HashMap<>();

                data.put(KEY_ADSID, intent.getStringExtra("ID"));
                data.put(KEY_USERID, intent.getStringExtra("uemail"));


                String result = rh.sendPostRequest(post_url,data);

                return result;
            }
        }

        addtofav ui = new addtofav();
        ui.execute();
    }

//    private void parseJson() throws IOException, JSONException {
//
//        if (!AdsUtils.isNetworkAvailable(AdsDetail.this)) {
//            Toast.makeText(AdsDetail.this, "Internet is required!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        AdsUtils.showSimpleProgressDialog(AdsDetail.this);
//        new AsyncTask<Void, Void, String>(){
//            protected String doInBackground(Void[] params) {
//                String response="";
//                HashMap<String, String> map=new HashMap<>();
//                try {
//                    HttpRequest req = new HttpRequest(AdsConstants.ServiceType.URL);
//                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
//                } catch (Exception e) {
//                    response=e.getMessage();
//                }
//                return response;
//            }
//            protected void onPostExecute(String result) {
//                //do something with response
//                Log.d("newwwss",result);
//                onTaskCompleted(result,jsoncode);
//            }
//        }.execute();
//    }
//
//    public void onTaskCompleted(String response, int serviceCode) {
//        Log.d("responsejson", response.toString());
//        switch (serviceCode) {
//            case jsoncode:
//
//                if (parseContent.isSuccess(response)) {
//                    AdsUtils.removeSimpleProgressDialog();  //will remove progress dialog
//                    playersModelArrayList = parseContent.getInfo(response);
//                    customeAdapter = new AdsAdapter(this,playersModelArrayList);
////                    listView.setAdapter(customeAdapter);
//
//                }else {
//                    Toast.makeText(AdsDetail.this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
//                }
//        }
//    }

}
