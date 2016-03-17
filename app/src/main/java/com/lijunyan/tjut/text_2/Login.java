package com.lijunyan.tjut.text_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Login extends AppCompatActivity {

    private  String username;
    private String userpwd;
    private EditText username_editText;
    private EditText userpwd_editText;
    private Button login_btn;
    private Button regist_btn;
    private Handler hand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn=(Button)findViewById(R.id.start_btn);
        regist_btn=(Button)findViewById(R.id.button_zc);
        username_editText=(EditText)findViewById(R.id.username);
        userpwd_editText=(EditText)findViewById(R.id.userpwd);
        hand=new Handler() {
            @Override
            public void close() {
            }
            @Override
            public void flush() {
            }
            @Override
            public void publish(LogRecord record) {
            }
        };
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Login.ACTIVITY_SERVICE, "onClick yes");
                username = username_editText.getText().toString();
                userpwd = userpwd_editText.getText().toString();
                if (username.length() < 1) {
                    Log.e(Login.ACTIVITY_SERVICE, "onClick user");
                    Toast.makeText(Login.this, "用户名错误！", Toast.LENGTH_SHORT);
                } else if (userpwd.length() < 6) {
                    Log.e(Login.ACTIVITY_SERVICE, "onClick pwd");
                    Toast.makeText(Login.this, "密码错误!", Toast.LENGTH_SHORT);
                } else {
                    Log.e(Login.ACTIVITY_SERVICE, "onClick ok");
                    LoginSocket login_socket = new LoginSocket(username, userpwd, hand);
                    login_socket.run();
                    /*if(login_socket.JudgmentData()){
                        setContentView(R.layout.login_success);
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(Login.this, "登陆失败!", Toast.LENGTH_SHORT);
                    }*/
                }
            }
        });
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_editText.getText().toString();
                userpwd = userpwd_editText.getText().toString();
                if (username.length() < 1) {
                    Toast.makeText(Login.this, "用户名错误！", Toast.LENGTH_SHORT);
                } else if (userpwd.length() < 6) {
                    Toast.makeText(Login.this, "密码错误!", Toast.LENGTH_SHORT);
                } else {
                    Register register = new Register(username, userpwd, hand);
                    Thread thread = new Thread(register);
                    thread.start();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
