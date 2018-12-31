package ng.haqqq.a9jacars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MessageView extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.messageview);
        Intent intent = getIntent();

        TextView fullname = findViewById(R.id.muser);
        fullname.setText(intent.getStringExtra("FULLNAME"));

        TextView content = findViewById(R.id.mcontent);
        content.setText(intent.getStringExtra("CONTENT"));

    }
}
