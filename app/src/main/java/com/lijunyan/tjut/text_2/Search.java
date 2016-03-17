package com.lijunyan.tjut.text_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Search extends Activity {

   private static final String[] strs = new String[]{
    "数据库系统", "网站设计与维护", "计算机组成与结构", "JAVA语言程序设计",
     "大学生创业与择业心理学", "软件工程", "网络安全技术", "接入网技术",
     "操作系统", "专业英语与写作", "路由与交换技术", "西方影视鉴赏",
    };
    //定义一个String数组用来显示ListView的内容
    private ListView lv;
    // /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv = (ListView) findViewById( R.id.lv);//得到ListView对象的引用
        // /*为ListView设置Adapter来绑定数据*/
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strs));

    }
}
