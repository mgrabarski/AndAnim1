package mateusz.grabarski.andanim1.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mateusz.grabarski.andanim1.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_login_login_btn)
    public void onLoginClick() {
        finish();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.activity_login_create_account_tv)
    public void onCreateAccountClick() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
