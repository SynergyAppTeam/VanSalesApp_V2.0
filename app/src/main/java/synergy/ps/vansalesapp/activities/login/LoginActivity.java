package synergy.ps.vansalesapp.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import synergy.ps.vansalesapp.R;
import synergy.ps.vansalesapp.activities.ParentActivity;

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
        boolean nameData = true;
        boolean passData = true;
        if (txtUsername.getText().toString().trim().length() == 0) {
            txtUsername.setError(getString(R.string.login_username_error));
            txtUsername.requestFocus();
            nameData = false;
        } else if (txtPassword.getText().toString().trim().length() == 0) {
            txtPassword.setError(getString(R.string.login_password_error));
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

            Intent intent = new Intent(getApplicationContext(),LicenceActivity.class);
            startActivity(intent);
            //perform login
        }
    }
}

