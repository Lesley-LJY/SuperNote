package com.lijunyan.tjut.text_2; /**
 * Created by LJY on 2015/10/14.
 */
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.regex.*;

/**
 * Created by zyy on 15-7-14.
 */
public class LoginSocket implements Runnable{
    private String username;
    private String passwd;

    private Socket socket;
    private BufferedReader bufferedReader=null;
    private OutputStream outputStream=null;
    private InputStream inputStream=null;

    private  String resultData=null;
    private String serverIp="192.168.199.134";
    private int port=1627;

    //验证成功向Activity 发送消息
    private Handler handler;
    public LoginSocket(String user, String password, Handler hand){
        username=user;
        passwd=password;
        handler=hand;
    }
    //子线程运行函数重构
    @Override
    public void run() {
        try {
            socket = new Socket(serverIp,port);
            bufferedReader=new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            outputStream= socket.getOutputStream();
            //加入消息内容
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("method","POST");
            jsonObject.put("username",username);
            jsonObject.put("passwd",passwd);
            String sendMessage=jsonObject.toString();
            //发送消息
            outputStream.write(sendMessage.getBytes());
            outputStream=socket.getOutputStream();
            //启动子线程响应服务器
            Thread thread = new Thread() {
                @Override
                public void run() {
                    String content = null;
                    try {
                        inputStream=socket.getInputStream();
                        byte[] get=new byte[10];
                        inputStream.read(get);
                        String data=get.toString();
                        resultData=data;
                        JudgmentData();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            };
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //处理服务器返回数据
    public boolean JudgmentData() {
        String str="successed";
        JSONObject jsonObject = new JSONObject();
        Pattern pattern=Pattern.compile(resultData);
        Matcher matcher=pattern.matcher(str);
        boolean result=matcher.matches();
        return result;
    }
}