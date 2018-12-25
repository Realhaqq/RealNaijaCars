package ng.haqqq.a9jacars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityProfile extends AppCompatActivity {
    private SessionHandler session;
    private Context context;
    @Override

    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_profile);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView txtname = findViewById(R.id.txtname);


        txtname.setText(user.getFullName());

        TextView txtemail = findViewById(R.id.txtemail);

        txtemail.setText(user.getEmail());

        Button lougout = findViewById(R.id.btnlogout);
        lougout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                session.logoutUser();
                Intent i = new Intent(ActivityProfile.this, ActivityLogin.class);
                startActivity(i);
                finish();
            }

        });
    }
}
