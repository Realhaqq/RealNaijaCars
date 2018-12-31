package ng.haqqq.a9jacars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;
    public String filter;
    TextView txtsearchh;
    @Override

    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_dashboard);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        LinearLayout cars = findViewById(R.id.cars);
        LinearLayout carparts = findViewById(R.id.carparts);
        cars.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();
                Intent i = new Intent(DashboardActivity.this, CarsActivity.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }
                                });
//        txtsearchh = findViewById(R.id.et_search);
//        ImageView search = findViewById(R.id.btnsearch);
//        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        im.showSoftInput(txtsearchh, 0);
        EditText edittext = findViewById(R.id.et_search);
        edittext.requestFocus();
        edittext.setShowSoftInputOnFocus(true);

//
//        search.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                filter = txtsearchh.getText().toString().trim();
//                User user = session.getUserDetails();
//                Intent i = new Intent(DashboardActivity.this, SearchActivity.class);
//                i.putExtra("email", user.getEmail());
//                i.putExtra("filter", filter);
//                startActivity(i);
//
//            }
//        });

        carparts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();
                Intent i = new Intent(DashboardActivity.this, PartActivity.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }
        });

        ImageView dashboard = findViewById(R.id.dashboard);
        dashboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(DashboardActivity.this, DashboardActivity.class);
                startActivity(i);

            }
        });

        ImageView newpost = findViewById(R.id.newpost);
        newpost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();
                Intent i = new Intent(DashboardActivity.this, PostAds.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }
        });

        ImageView message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                User user = session.getUserDetails();
                Intent i = new Intent(DashboardActivity.this, MessagesActivity.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }
        });

        ImageView fav = findViewById(R.id.favourite);
        fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();
                Intent i = new Intent(DashboardActivity.this, Favourite.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }
        });
        ImageView profile = findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, ActivityProfile.class);
                startActivity(i);

            }
        });




    }
}
