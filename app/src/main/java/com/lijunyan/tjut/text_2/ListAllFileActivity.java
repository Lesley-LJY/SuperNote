package com.lijunyan.tjut.text_2;

/**
 * Created by AD on 2015/8/13.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * 文件列表视图（默认SD卡）
 * 按下表单项 根据是否为目录 判断
 * 1.跳转 viewFile Activity  （携带文件名及其目录）
 * 2.重绘目标文件夹的文件列表
 */


public class ListAllFileActivity extends ListActivity {


    private List<File> fileList;
    private Bundle bundle;
    private String fileNameKey = "fileName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File path = android.os.Environment.getExternalStorageDirectory();
        File[] f = path.listFiles();
        fill(f);
    }

    //读取文件列表,并设置listView
    private void fill(File[] files) {
        fileList = new ArrayList<File>();

        //文件列表遍历
        for (File file : files) {
            if (isValidFileOrDir(file)) {
                fileList.add(file);
            }
        }
        ArrayAdapter<String> fileNameList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                fileToStrArr(fileList)  );

        //设置ListActivity界面的方法
        //同setContentView在Activity界面中的作用
        setListAdapter(fileNameList);
    }

    //检查是否为合法的文件名，或者是否为路径
    private boolean isValidFileOrDir(File fileIn)
    {
        if (fileIn.isDirectory()) {
            return true;
        }
        else {
            //将文件名变为小写重新存入新字符串
            String fileNameLow = fileIn.getName().toLowerCase();
            if (fileNameLow.endsWith(".jpg")) {
                return true;
            }
        }
        return false;
    }


    //将一个文件数组转化成由其名字构成的新数组 用来列表
    //用到 getName()得到文件名
    private String[] fileToStrArr(List<File> fl)
    {
        ArrayList<String> fnList = new ArrayList<String>();
        for (int i = 0; i < fl.size(); i++) {
            String nameString = fl.get(i).getName();
            fnList.add(nameString);
        }
        return fnList.toArray(new String[0]);
    }

    //列表项被点击的动作
    //1.如果是文件夹 则重置绘图 进入文件夹
    //2.如果是文件 则带数据 跳转屏幕(Activity)
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //position为点击所在的位置 用这个序号来取得对应文件
        File file = fileList.get(position);
        if (file.isDirectory())
        {
            File[] f = file.listFiles();
            fill(f);  // 重汇文件列表 到新的文件夹
        }else {
            Intent intent = new Intent(ListAllFileActivity.this, ViewFile.class);
            intent.putExtra("name",fileNameKey);
            startActivity(intent);
        }


    }

}
