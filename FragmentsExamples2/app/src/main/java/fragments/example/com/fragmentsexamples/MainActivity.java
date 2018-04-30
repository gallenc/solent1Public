package fragments.example.com.fragmentsexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity  implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addContact = (Button)findViewById(R.id.btnlaunchfrag);
        addContact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btnlaunchfrag){
            Intent intent = new Intent(this,FragActivity.class);
            Bundle bundle=new Bundle();

            // not used
            bundle.putString("firstname","");
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
        }
    }
}
