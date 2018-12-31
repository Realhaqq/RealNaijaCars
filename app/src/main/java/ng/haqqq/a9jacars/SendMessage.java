package ng.haqqq.a9jacars;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SendMessage extends AppCompatActivity {
    private static final String KEY_USERID = "userid";
    private static final String KEY_TOUSERID = "touserid";
    private static final String KEY_CONTENT = "content";

    private EditText etmessage;
    public String mmessage;
    public static final String  post_url = "http://192.168.43.73/naijacars/Api/sendmessage.php";
    private SessionHandler session;
    private ProgressDialog pDialog;
    @Override

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.send_message);
        etmessage = findViewById(R.id.txtmessage);

        Button send = findViewById(R.id.btnlsend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                mmessage = etmessage.getText().toString().trim();
                Sendnow();
            }


        });

    }

    private void Sendnow(){
        class sendnow extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SendMessage.this, "Send Message....", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                Intent i = new Intent(SendMessage.this, DashboardActivity.class);

                startActivity(i);
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Intent intent = getIntent();

//                id = UUID.randomUUID().toString();
                HashMap<String,String> data = new HashMap<>();

                data.put(KEY_TOUSERID, intent.getStringExtra("FROMEMAIL"));
                data.put(KEY_USERID, intent.getStringExtra("TOEMAIL"));
                data.put(KEY_CONTENT, mmessage);


                String result = rh.sendPostRequest(post_url,data);

                return result;
            }
        }

        sendnow ui = new sendnow();
        ui.execute();
    }



}