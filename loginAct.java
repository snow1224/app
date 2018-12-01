package anbas.second_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btOk;
    private Button btReg;
    private Button btTest;
    private EditText etAccount;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buildViews();
    }

    private void buildViews(){
        etAccount = (EditText) findViewById(R.id.edAccount);
        etPassword = (EditText) findViewById(R.id.edPassword);
        btOk = (Button)findViewById(R.id.btLogin);
        btReg = (Button)findViewById(R.id.btReg);
        btTest = (Button)findViewById(R.id.databaseTest) ;
        btOk.setOnClickListener(this);
        btReg.setOnClickListener(this);
        btTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            ////// Register value   後來要改成從資料庫取出///////////////
            Bundle bundle = getIntent().getExtras();
            String Regaccount = bundle.getString("account");
            String Regpassword = bundle.getString("password");
            ///////////////////////////////////////////////////////////////
            switch(v.getId()) {
                case R.id.btLogin:
                    String account = etAccount.getText().toString();
                    String password = etPassword.getText().toString();
                    Intent intent = new Intent();
                    if (account.equals("root") && password.equals("test")) {
                        intent.setClass(LoginActivity.this, ViewStatus.class);
                        startActivity(intent);
                        finish();
                    } else if (account.equals(Regaccount) && password.equals(Regpassword)) {
                        intent.setClass(LoginActivity.this, SettingAct.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String st = new String();
                        st = "帳號或密碼輸入錯誤...";
                        Toast.makeText(LoginActivity.this, st, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btReg:
                    Intent intent2 = new Intent();
                    intent2.setClass(LoginActivity.this,Register.class);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.databaseTest:
                    Intent intent3 = new Intent();
                    intent3.setClass(LoginActivity.this,DBCoonector.class);
                    startActivity(intent3);
                    finish();
            }
        }
        catch(Exception e) {
            /*
            * 因為 Bundle 傳遞沒有抓到任何東西，所以會當機
            * 如果以後資料庫拿資料，可以說不定可以刪除
            * */
            String account = etAccount.getText().toString();
            String password = etPassword.getText().toString();
            Intent intent = new Intent();
            switch(v.getId()) {
                case R.id.btLogin:
                    if (account.equals("root") && password.equals("test")) {
                        intent.setClass(LoginActivity.this, ViewStatus.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String st = new String();
                        st = "帳號或密碼輸入錯誤...";
                        Toast.makeText(LoginActivity.this, st, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btReg:
                    Intent intent2 = new Intent();
                    intent2.setClass(LoginActivity.this,Register.class);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.databaseTest:
                    Intent intent3 = new Intent();
                    intent3.setClass(LoginActivity.this,DBCoonector.class);
                    startActivity(intent3);
                    finish();
            }
        }
    }
}
