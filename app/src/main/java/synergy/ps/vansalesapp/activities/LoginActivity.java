package synergy.ps.vansalesapp.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import synergy.ps.vansalesapp.R;
import synergy.ps.vansalesapp.utils.BluetoothReceiver;
import synergy.ps.vansalesapp.utils.PrintManger;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends ParentActivity{



    // UI references.
    private EditText username;
    private EditText password;
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BluetoothReceiver bluetoothReceiver=new BluetoothReceiver();

        registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        PrintManger printManager=new PrintManger(getApplicationContext());
        printManager.findBT();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintManger printManger = new PrintManger(getApplication());
               if(validateLogin())
                   printManger.sendData();
            }
        });
    }

    public boolean validateLogin() {
        boolean nameData=true;
        boolean passData=true;
        if(username.getText().toString().trim().length()==0){
            username.setError("Username is not entered");
            username.requestFocus();
            nameData=false;
        }
        else
        if(password.getText().toString().trim().length()==0){
            password.setError("Password is not entered");
            password.requestFocus();
            passData=false;
        }
        if(nameData && passData)
            return true;
        return false;


    }


}

