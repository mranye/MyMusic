package com.tarena.music.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.tarena.music.R;

public class RegistActivity extends Activity {
	private EditText etName, etPwd, etPwdAgain;
	private Button btnRegist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initViews();
		initListeners();
	}

	private void initViews() {
		etName = (EditText) findViewById(R.id.regist_et_name);
		etPwd = (EditText) findViewById(R.id.regist_et_pwd);
		etPwdAgain = (EditText) findViewById(R.id.regist_et_pwd_again);
		btnRegist = (Button) findViewById(R.id.regist_btn_regist);
	}

	private void initListeners() {
		btnRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 将用户输入的账号密码存储到偏好设置user.xml文件中
				// 1 获得文件管理对象sharedPreference
				SharedPreferences sp = 
						getSharedPreferences("user.xml",
						Context.MODE_PRIVATE);
				// 2获得偏好设置的编辑对象
				Editor editor = sp.edit();
				// 3 获得输入框中的数据
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				String pwdAgain = etPwdAgain.getText()
						        .toString();
                // 4 判断 name pwd pwdAgain是否为空
				if("".equals(name)){
					etName.setError("请输入账号");
					return;
				}
				if("".equals(pwd)){
					etPwd.setError("请输入密码");
					return;
				}
				if("".equals(pwdAgain)){
					etPwdAgain.setError("请输入确认密码");
					return;
				}
			   // 判断pwd  pwdAgain是否一致
				if(!pwd.equals(pwdAgain)){
					etPwdAgain.setError("确认密码有误,请重新输入");
					etPwdAgain.setText("");//清空输入框内容
					return;
				}
				//使用editor 存放到user.xml
				editor.putString("name", name);
				editor.putString("pwd", pwd);
				//editor 提交到文件中
				editor.commit();
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regist, menu);
		return true;
	}

}
