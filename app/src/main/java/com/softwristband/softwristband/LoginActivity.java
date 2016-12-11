package com.softwristband.softwristband;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity
{

    private final static int LOGIN=1;
    private final static int MESSAGE_LOGIN_SUCCESS=5;
    private final static int MESSAGE_LOGIN_FAIL=6;
    private final static int MESSAGE_USERNAME_EMPTY=7;
    private final static int MESSAGE_USERPWD_EMPTY=8;


    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener()
        {
            private Handler handler =new Handler()
            {
                public void handleMessage(Message msg)
                {
                   switch(msg.what)
                   {
                       case MESSAGE_USERNAME_EMPTY:
                           Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                           break;
                       case MESSAGE_USERPWD_EMPTY:
                           Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                           break;
                       case MESSAGE_LOGIN_FAIL:
                           Toast.makeText(LoginActivity.this,"用户名或者密码错误",Toast.LENGTH_SHORT).show();
                           break;
                       case MESSAGE_LOGIN_SUCCESS:
                           Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                           break;
                   }
                }
            };
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
//                new Thread()
//                {
//                    public void run()
//                    {
//                        Message message = new Message();
//                        EditText name = (EditText)findViewById(R.id.user_name);
//                        EditText password = (EditText)findViewById(R.id.user_password);
//                        String host="192.168.1.4";
//                        Socket socket = null;
//                        if(name.getText().toString().length()==0)
//                        {
//                            message.what=MESSAGE_USERNAME_EMPTY;
//                            handler.sendMessage(message);
//                            return;
//                        }
//                        if(password.getText().toString().length()==0)
//                        {
//                            message.what=MESSAGE_USERPWD_EMPTY;
//                            handler.sendMessage(message);
//                            return;
//                        }
//                        try
//                        {
//                            socket = new Socket(host,2048);
//                            int login_result;
//                            DataOutputStream toService =new DataOutputStream(socket.getOutputStream());
//                            DataInputStream fromService = new DataInputStream(socket.getInputStream());
//                            toService.writeInt(LOGIN);
//                            toService.writeUTF(name.getText().toString());
//                            toService.writeUTF(password.getText().toString());
//                            toService.flush();
//                            login_result=fromService.readInt();
//                            if(login_result==1)
//                                message.what=MESSAGE_LOGIN_SUCCESS;
//                            else if(login_result==0)
//                                message.what=MESSAGE_LOGIN_FAIL;
//                            handler.sendMessage(message);
//                            toService.close();
//                            fromService.close();
//                            socket.close();
//                        }
//                        catch (IOException e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
            }
        });
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
