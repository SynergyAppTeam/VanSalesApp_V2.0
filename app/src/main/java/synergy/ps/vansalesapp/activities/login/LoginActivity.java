package synergy.ps.vansalesapp.activities.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import synergy.ps.vansalesapp.R;
import synergy.ps.vansalesapp.activities.ParentActivity;
import synergy.ps.vansalesapp.utils.network.BluetoothReceiver;
import synergy.ps.vansalesapp.managers.PrintManger;

/**
 * A btnLogin screen that offers btnLogin via email/txtPassword.
 */
public class LoginActivity extends ParentActivity implements View.OnClickListener {

    @InjectView(R.id.txtUsername)
    EditText txtUsername;
    @InjectView(R.id.txtPassword)
    EditText txtPassword;
    @InjectView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // get views
        ButterKnife.inject(this);

        // add events
        btnLogin.setOnClickListener(this);
    }

    public boolean validateLogin() {
        //FIXME: add texts to string file
        boolean nameData = true;
        boolean passData = true;
        if (txtUsername.getText().toString().trim().length() == 0) {
            txtUsername.setError("Username is not entered");
            txtUsername.requestFocus();
            nameData = false;
        } else if (txtPassword.getText().toString().trim().length() == 0) {
            txtPassword.setError("Password is not entered");
            txtPassword.requestFocus();
            passData = false;
        }
        if (nameData && passData)
            return true;
        return false;
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnLogin) {
            //perform login
        }
    }
}

