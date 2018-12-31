package ng.haqqq.a9jacars;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;


public class PostAds extends AppCompatActivity {

    public static final String  KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_PRICE = "price";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_USER = "user";
    public static final String KEY_ID = "id";
    public static final String KEY_NEG = "neg";
    private static final String KEY_EMPTY = "";

    private EditText etTitle;
    private EditText etDescription;
    private RadioButton etCategory;
    private EditText etPrice;
    private EditText etLocation;

    private String negotiable;
    private String user;
    private String title;
    private String description;
    private String category;
    private String price;
    private String location;
    private String id;
    public static final String  post_url = "http://192.168.43.73/naijacars/Api/postad.php";
    private SessionHandler session;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_post);

        etTitle = findViewById(R.id.ptitle);
        etDescription = findViewById(R.id.pdescription);

        etPrice = findViewById(R.id.pprice);
        etLocation = findViewById(R.id.plocation);



        Button selectUploadButton = findViewById(R.id.uploadpic);

        selectUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = null;
                String neg = "";

                RadioButton rad1 = findViewById(R.id.rad1);
                RadioButton rad2 = findViewById(R.id.rad2);

                if (rad1.isChecked()){
                    cat = "Car";
                }else if (rad2.isChecked()){
                    cat = "Part";
                }



                CheckBox cneg = findViewById(R.id.cneg);
                if(cneg.isChecked()){
                    neg = "Negotiable";
                }
                title = etTitle.getText().toString().trim();
                description = etDescription.getText().toString().trim();
                category = cat;;
                negotiable = neg;

                
                price = etPrice.getText().toString().trim();
                location = etLocation.getText().toString().trim();
                if (validateInputs()) {
                    uploadImage();
                }

            }
        });
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PostAds.this, "Adding ads...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

                Intent i = new Intent(PostAds.this, UploadPost.class);
                i.putExtra("adsid", id);
                i.putExtra("adsname", title);
                startActivity(i);
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Intent intent = getIntent();
                id = UUID.randomUUID().toString();
                HashMap<String,String> data = new HashMap<>();

                data.put(KEY_CATEGORY, category);
                data.put(KEY_TITLE, title);
                data.put(KEY_DESCRIPTION, description);
                data.put(KEY_LOCATION, location);
                data.put(KEY_PRICE, price);
                data.put(KEY_USER, intent.getStringExtra("email"));
                data.put(KEY_ID, id);
                data.put(KEY_NEG, negotiable);
                String result = rh.sendPostRequest(post_url,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute();
    }
    private boolean validateInputs() {
        if(KEY_EMPTY.equals(title)){
            etTitle.setError("Title cannot be empty");
            etTitle.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(price)){
            etPrice.setError("Price cannot be empty");
            etPrice.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(location)){
            etLocation.setError("Location cannot be empty");
            etLocation.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(description)){
            etLocation.setError("Description cannot be empty");
            etDescription.requestFocus();
            return false;
        }


        return true;
    }

}
