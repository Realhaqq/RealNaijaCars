package ng.haqqq.a9jacars;

import android.content.Intent;
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

public class SendMessage extends AppCompatActivity {
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_TO_USERID = "touserid";
    private static final String KEY_MESSAGE = "content";
    private EditText etmessage;
    private String txtmessage;
    private String sendmessage = "http://192.168.8.100/naijacars/Api/sendmessage.php";
    private SessionHandler session;

    @Override

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.send_message);
        etmessage = findViewById(R.id.txtmessage);
        Intent intent = new Intent();
        TextView touser = findViewById(R.id.touser);
        String tuser = intent.getStringExtra("TOUSERID");
        touser = touser;

        Button send = findViewById(R.id.btnlsend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                txtmessage = etmessage.getText().toString().trim();
                Sendnow();
            }


        });

    }

            private void Sendnow() {

                session = new SessionHandler(getApplicationContext());
                User user = session.getUserDetails();
                Intent intent = new Intent();
                JSONObject request = new JSONObject();
                try {
                    //Populate the request parameters
                    request.put(KEY_USER_ID, user.getEmail());
                    request.put(KEY_TO_USERID, intent.getStringExtra("EMAIL"));
                    request.put(KEY_MESSAGE, etmessage);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                        (Request.Method.POST, sendmessage, request, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Messaege sent Successfully", Toast.LENGTH_SHORT).show();
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