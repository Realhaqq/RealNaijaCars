package ng.haqqq.a9jacars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;
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
                Intent i = new Intent(DashboardActivity.this, CarsActivity.class);
                startActivity(i);

            }
                                });

        carparts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(DashboardActivity.this, CarsActivity.class);
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
                Intent i = new Intent(DashboardActivity.this, PostActivity.class);
                startActivity(i);

            }
        });

        ImageView message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                session = new SessionHandler(getApplicationContext());
                User user = session.getUserDetails();
                Intent i = new Intent(DashboardActivity.this, MessagesActivity.class);
                i.putExtra("EMAIL", user.getEmail());
                startActivity(i);

            }
        });

        ImageView fav = findViewById(R.id.favourite);
        fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(DashboardActivity.this, Favourite.class);
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
