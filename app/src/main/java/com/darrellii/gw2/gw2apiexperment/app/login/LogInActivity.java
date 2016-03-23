package com.darrellii.gw2.gw2apiexperment.app.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darrellii.gw2.gw2apiexperment.R;
import com.darrellii.gw2.gw2apiexperment.app.main.MainActivity;
import com.darrellii.gw2.gw2apiexperment.network.GW2Client;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.TokenInfo;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LogInActivity extends AppCompatActivity implements LoginContract.View {

    private final LoginContract.UserActionsListener mListener = new LogInPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mListener.onCreated();

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLogInClicked();
            }
        });
        findViewById(R.id.get_api_key).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.onOpenGW2URLClicked();
            }
        });

    }

    //<editor-fold desc="View Methods">
    @Override
    public void openGW2WebIntent() {
        String url = getString(R.string.gw2_getapikey_url);
        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Bundle extras = new Bundle();
            extras.putBinder(getString(R.string.create_customtab),null);
            i.putExtras(extras);
            int color;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = getColor(R.color.colorPrimary);
            } else {
                color = getResources().getColor(R.color.colorPrimary);
            }

            i.putExtra(getString(R.string.customtab_color),color);
        }

        startActivity(i);
    }

    @Override
    public void logIn() {
        mListener.onLogIn();
    }

    @Override
    public void showInvalidKey() {
        ((TextView) findViewById(R.id.api_key)).setError(getString(R.string.invalid_api_key));
    }

    @Override
    public String getAPIKey() {
        return ((EditText) findViewById(R.id.api_key)).getText().toString();
    }

    @Override
    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean isLoggedIn() {
        return getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE)
                .contains(getString(R.string.prefs_api_key));
    }

    @Override
    public void checkToken(String apiKey) {
        GW2Client.verifyAPIKey(apiKey, new Callback<TokenInfo>() {
            @Override
            public void onResponse(Response<TokenInfo> response, Retrofit retrofit) {
                mListener.onVerifyToken(response.body().permissions);
            }

            @Override
            public void onFailure(Throwable t) {
                mListener.onVerifyToken(null);
            }
        });
    }

    @Override
    public void showVerificationError(List<String> missingParams) {
        Toast.makeText(this, "Missing Permissions: " + missingParams.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRequiredPerms(String requiredPerms) {
        TextView viewById = (TextView) findViewById(R.id.required_perms);
        viewById.setText(requiredPerms);
    }

    @Override
    public void saveAPIKey(String apiKey) {
        SharedPreferences.Editor editor =
                getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE).edit();
        editor.putString(getString(R.string.prefs_api_key), apiKey);
        editor.commit();
    }
    //</editor-fold>
}
