package com.tarena.music.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarena.music.R;
import com.tarena.music.activity.LoginActivity;

public class WDFragment extends Fragment {
	/**对应点击登录文本内容*/
	private TextView tvLogin;
	/**此方法可以指定加载的布局文件*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    /**获得加载布局文件的对象v**/
         View v=View.inflate(getActivity(),//当前activity
        		 R.layout.fragment_wd, //加载的布局文件
        		 null);
        //初始化控件
          initViews(v);
		//初始化事件监听
          initListeners();
		
		/**将此对象v返回*/
		return v;
	}
	/**
	 * 初始化各个控件的事件监听
	 */
	private void initListeners() {
		tvLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//跳转到登录界面
				Intent intent=
						new Intent(getActivity(),
								LoginActivity.class);
//				startActivity(intent);
				//启动LoginActivity并获得他关闭时回传的数据
				startActivityForResult(intent, 100);
			}
		});
	}
	
	/**当执行LoginActivity中setResult时执行此处代码*/
	@Override
	public void onActivityResult(
			int requestCode,//请求码  发起请求时的
			int resultCode, //结果码  返回结果时的
			Intent data) {// 结果数据 返回结果封装对象
		//盘但requestCode是不是100 resultCode是不是200
		Log.i("TAG", "onActivityResult");
		Log.i("TAG", "onActivityResult"+requestCode+"---"+resultCode);
		
		if(requestCode==100&&resultCode==200){
		   //如果请求码和结果码都匹配
			String name=data.getExtras().getString("name");
		   //获得data中的数据
			Log.i("TAG", "onActivityResult"+name);
			//将name设置到tvLogin控件
			 tvLogin.setText(name);
			
		}
	}
	
	
	/**
	 * 初始化各个控件
	 * @param v 当前界面布局文件
	 */
	private void initViews(View v) {
     tvLogin=(TextView) 
    		 v.findViewById(R.id.wd_tv_login);
	}
}
