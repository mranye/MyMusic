package com.tarena.music.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tarena.music.R;

public class LoginActivity extends Activity {
	/** 账号密码输入框 */
	private EditText etName, etPwd;
	/** 登录按钮 */
	private Button btnLogin;
	/** 注册文本框对象 */
	private TextView tvRegist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 初始化
		initViews();
		// 事件监听初始化
		iniListeners();
	}

	/** 初始化各个控件 **/
	private void initViews() {
		etName = (EditText) findViewById(R.id.login_et_name);
		etPwd = (EditText) findViewById(R.id.login_et_pwd);
		btnLogin = (Button) findViewById(R.id.login_btn_login);
		tvRegist = (TextView) findViewById(R.id.login_tv_regist);
	}

	/** 设置登录按钮的事件监听和注册文本框事件 **/
	private void iniListeners() {
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 获得输入框中的数据
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				// 核心:从偏好设置中获取对应的数据
				SharedPreferences sp = getSharedPreferences("user.xml", // 偏好设置的文件名
						Context.MODE_PRIVATE);// 私有
				// 获取文件中的name值
				String spName = sp.getString("name", "nothing");
				// 获取文件中pwd值
				String spPwd = sp.getString("pwd", "nothing");
				// 判断spName和spPwd是否含有nothing关键词
				if (spName.equals("nothing") || spPwd.equals("nothing")) {
					// toast提示用户请先注册
					Toast.makeText(LoginActivity.this, "请先注册", 0).show();
					// 程序不再向下执行
					return;
				}
				// 判断用户输入的name和pwd是否和获取到的spName spPwd
				if (name.equals(spName) && pwd.equals(spPwd)) {
					// //登录成功,跳转回MainActivity
					// startActivity
					// (new Intent(
					// LoginActivity.this,
					// MainActivity.class));
					// 调用setResult方法设置回传数据
					Intent intent = new Intent();
					intent.putExtra("name", name);
					setResult(200, intent);
					// 销毁LoginActivity
					finish();
				} else {
					// 提示用户账号密码错误
					Toast.makeText(LoginActivity.this, "账号密码错误", 0).show();
				}

			}
		});
		//
		tvRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 跳转到注册界面
				startActivity(new Intent(LoginActivity.this,
						RegistActivity.class));

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
