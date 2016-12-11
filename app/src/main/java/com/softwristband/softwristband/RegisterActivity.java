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

public class RegisterActivity extends AppCompatActivity
{
    private final static int REGISTER=2;   //注册
    private final static int MESSAGE_REGESTER_FAIL=0;
    private final static int MESSAGE_REGESTER_SUCCESS=1;
    private String userName;
    private String userPassword;
    private String repeatPassword;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener()
        {
            private Handler handler = new Handler()
            {
              public void handleMessage(Message msg)
              {
                  switch(msg.what)
                  {
                      case MESSAGE_REGESTER_FAIL:
                          Toast.makeText(RegisterActivity.this,"该用户已被注册",Toast.LENGTH_SHORT).show();
                          break;
                      case MESSAGE_REGESTER_SUCCESS:
                          Toast.makeText(RegisterActivity.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                          startActivity(intent);
                          break;
                  }
              }
            };
            @Override
            public void onClick(View view)
            {
                userName=((EditText)findViewById(R.id.user_name)).getText().toString();
                userPassword=((EditText)findViewById(R.id.user_password)).getText().toString();
                repeatPassword=((EditText)findViewById(R.id.comfirm_password)).getText().toString();
                if(userName.length()==0)
                {
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPassword.length()==0)
                {
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!userPassword.equals(repeatPassword))
                {
                    Toast.makeText(RegisterActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread()
                {
                    public void run()
                    {
                        String userName=((EditText)findViewById(R.id.user_name)).getText().toString();
                        String userPassword=((EditText)findViewById(R.id.user_password)).getText().toString();
                        String host="192.168.1.4";
                        try
                        {
                            Socket socket = new Socket(host,2048);
                            int register_result;
                            DataOutputStream toService =new DataOutputStream(socket.getOutputStream());
                            DataInputStream fromService = new DataInputStream(socket.getInputStream());
                            toService.writeInt(REGISTER);
                            toService.writeUTF(userName);
                            toService.writeUTF(userPassword);
                            toService.flush();
                            Message message = new Message();
                            register_result=fromService.readInt();
                            if(register_result==0)
                                message.what=MESSAGE_REGESTER_FAIL;
                            else if(register_result==1)
                                message.what=MESSAGE_REGESTER_SUCCESS;
                            handler.sendMessage(message);
                            toService.close();
                            fromService.close();
                            socket.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
}
