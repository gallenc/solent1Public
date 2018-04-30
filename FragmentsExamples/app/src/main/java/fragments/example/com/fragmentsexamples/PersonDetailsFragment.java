package fragments.example.com.fragmentsexamples;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonDetailsFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.persondetailsfrag, parent);
    }

    public void setText(String newText) {
        TextView tv1 = (TextView) getView().findViewById(R.id.tv1);
        tv1.setText(newText);
    }
}
