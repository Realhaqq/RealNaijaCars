package ng.haqqq.a9jacars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class ActivityProfile extends AppCompatActivity {
    Context context;
    private SessionHandler session;

    @Override

    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_profile);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView txtname = findViewById(R.id.txtname);

        CircularImageView profilepic = findViewById(R.id.profilepic);
        Picasso.with(this)
                .load("http://192.168.43.73/naijacars/Api/pics/haqq.jpg")
                .placeholder(R.drawable.cars)
//                .centerInside()
                .error(R.drawable.cars)
                .into(profilepic);
        txtname.setText(user.getFullName());

        TextView txtemail = findViewById(R.id.txtemail);


        txtemail.setText(user.getEmail());


        LinearLayout lougout = findViewById(R.id.mylogout);
        lougout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                session.logoutUser();
                Intent i = new Intent(ActivityProfile.this, ActivityLogin.class);
                startActivity(i);
                finish();
            }

        });


        LinearLayout myads = findViewById(R.id.myads);
        myads.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();

                Intent i = new Intent(ActivityProfile.this, MyAds.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }

        });

        LinearLayout mymessage = findViewById(R.id.mymessage);
        mymessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();

                Intent i = new Intent(ActivityProfile.this, MessagesActivity.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }

        });

        LinearLayout mysettings = findViewById(R.id.mysettings);
        mysettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = session.getUserDetails();

                Intent i = new Intent(ActivityProfile.this, Settings.class);
                i.putExtra("email", user.getEmail());
                startActivity(i);

            }

        });
    }



}
