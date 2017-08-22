package synergy.ps.vansalesapp.activities.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import synergy.ps.vansalesapp.R;
import synergy.ps.vansalesapp.activities.ParentActivity;

/**
 * Created by HP on 8/22/2017.
 */

public class LicenceActivity extends ParentActivity implements View.OnClickListener {

    @InjectView(R.id.txtLicence)
    EditText txtLicence;
    @InjectView(R.id.btnRegister)
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);
         ButterKnife.inject(this);
         btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        CharSequence text="";
        int duration = Toast.LENGTH_LONG;
        String Str=txtLicence.getText().toString();

        if(0==Str.length())
        {
            text=getString(R.string.Registration_failed);
        }
        else
        {
            text=getString(R.string.Registration_pass);
        }
        Toast toast=Toast.makeText(context, text, duration);
        toast.show();

    }
}
