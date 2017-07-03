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
				// ���û�������˺�����洢��ƫ������user.xml�ļ���
				// 1 ����ļ��������sharedPreference
				SharedPreferences sp = 
						getSharedPreferences("user.xml",
						Context.MODE_PRIVATE);
				// 2���ƫ�����õı༭����
				Editor editor = sp.edit();
				// 3 ���������е�����
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				String pwdAgain = etPwdAgain.getText()
						        .toString();
                // 4 �ж� name pwd pwdAgain�Ƿ�Ϊ��
				if("".equals(name)){
					etName.setError("�������˺�");
					return;
				}
				if("".equals(pwd)){
					etPwd.setError("����������");
					return;
				}
				if("".equals(pwdAgain)){
					etPwdAgain.setError("������ȷ������");
					return;
				}
			   // �ж�pwd  pwdAgain�Ƿ�һ��
				if(!pwd.equals(pwdAgain)){
					etPwdAgain.setError("ȷ����������,����������");
					etPwdAgain.setText("");//������������
					return;
				}
				//ʹ��editor ��ŵ�user.xml
				editor.putString("name", name);
				editor.putString("pwd", pwd);
				//editor �ύ���ļ���
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
