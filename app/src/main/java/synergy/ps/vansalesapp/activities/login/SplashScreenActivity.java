package synergy.ps.vansalesapp.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import synergy.ps.vansalesapp.activities.ParentActivity;

public class SplashScreenActivity extends ParentActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
