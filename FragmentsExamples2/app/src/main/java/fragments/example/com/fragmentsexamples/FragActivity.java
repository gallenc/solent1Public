package fragments.example.com.fragmentsexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class FragActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        PersonDetailsFragment personDetailsFrag = (PersonDetailsFragment)getFragmentManager().findFragmentById(R.id.personDetailsFrag);
        personDetailsFrag.setText("Details on a famous person will appear here!");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                //do nothing
            }
        }
    }

}