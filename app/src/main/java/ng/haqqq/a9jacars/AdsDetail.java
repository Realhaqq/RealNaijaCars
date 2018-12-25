package ng.haqqq.a9jacars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AdsDetail extends AppCompatActivity {
    private SessionHandler session;
    private String addfav = "http://192.168.43.73/naijacars/Api/addtofav.php";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_ADSID = "adsid";
    private String userid;
    private String id;

    JsonArrayRequest jsonArrayRequest ;
    Context context;
    RequestQueue requestQueue ;
    ImageLoader imageLoader1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ads_detail);

        Intent intent = getIntent();
        String addsid = intent.getStringExtra("EXTRA_SESSION_ID");
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();

        AdsDetailAdapter adsDetailAdapter = new AdsDetailAdapter();

        TextView title = findViewById(R.id.txttitle);
       title.setText(intent.getStringExtra("TITLE"));

        TextView price = findViewById(R.id.txtprice);
        price.setText("N" + intent.getStringExtra("PRICE"));

        TextView desc = findViewById(R.id.txtdescription);
        desc.setText(intent.getStringExtra("DESCRIPTION"));

        TextView location = findViewById(R.id.txtlocation);
        location.setText(intent.getStringExtra("LOCATION"));

        TextView fullname = findViewById(R.id.txtuser);
        fullname.setText(intent.getStringExtra("FULLNAME"));




        userid = adsDetailAdapter.getUserid();


     Button btnaddtofav = findViewById(R.id.btnaddtofav);

        btnaddtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addtofav();


            }
        });



        Button btnmessage = findViewById(R.id.btnmesage);

        btnmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Intent i = new Intent(AdsDetail.this, SendMessage.class);
                String email = intent.getStringExtra("EMAIL");
                String fullname = intent.getStringExtra("FULLNAME");
                i.putExtra("EMAIL", email);
                i.putExtra("fullname", fullname);
                startActivity(i);
            }
        });





    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                /* DO EDIT */
                return true;
            case R.id.action_rating:
                /* DO ADD */
                return true;
            case R.id.action_about:
                /* DO DELETE */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void Addtofav() {
        Intent intent = new Intent();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USER_ID, intent.getStringExtra("EMAIL"));
            request.put(KEY_ADSID, intent.getStringExtra("EXTRA_SESSION_ID"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, addfav, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Added to Your Favourite List", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }




}
