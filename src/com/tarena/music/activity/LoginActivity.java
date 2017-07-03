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
	/** �˺���������� */
	private EditText etName, etPwd;
	/** ��¼��ť */
	private Button btnLogin;
	/** ע���ı������ */
	private TextView tvRegist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// ��ʼ��
		initViews();
		// �¼�������ʼ��
		iniListeners();
	}

	/** ��ʼ�������ؼ� **/
	private void initViews() {
		etName = (EditText) findViewById(R.id.login_et_name);
		etPwd = (EditText) findViewById(R.id.login_et_pwd);
		btnLogin = (Button) findViewById(R.id.login_btn_login);
		tvRegist = (TextView) findViewById(R.id.login_tv_regist);
	}

	/** ���õ�¼��ť���¼�������ע���ı����¼� **/
	private void iniListeners() {
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���������е�����
				String name = etName.getText().toString();
				String pwd = etPwd.getText().toString();
				// ����:��ƫ�������л�ȡ��Ӧ������
				SharedPreferences sp = getSharedPreferences("user.xml", // ƫ�����õ��ļ���
						Context.MODE_PRIVATE);// ˽��
				// ��ȡ�ļ��е�nameֵ
				String spName = sp.getString("name", "nothing");
				// ��ȡ�ļ���pwdֵ
				String spPwd = sp.getString("pwd", "nothing");
				// �ж�spName��spPwd�Ƿ���nothing�ؼ���
				if (spName.equals("nothing") || spPwd.equals("nothing")) {
					// toast��ʾ�û�����ע��
					Toast.makeText(LoginActivity.this, "����ע��", 0).show();
					// ����������ִ��
					return;
				}
				// �ж��û������name��pwd�Ƿ�ͻ�ȡ����spName spPwd
				if (name.equals(spName) && pwd.equals(spPwd)) {
					// //��¼�ɹ�,��ת��MainActivity
					// startActivity
					// (new Intent(
					// LoginActivity.this,
					// MainActivity.class));
					// ����setResult�������ûش�����
					Intent intent = new Intent();
					intent.putExtra("name", name);
					setResult(200, intent);
					// ����LoginActivity
					finish();
				} else {
					// ��ʾ�û��˺��������
					Toast.makeText(LoginActivity.this, "�˺��������", 0).show();
				}

			}
		});
		//
		tvRegist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ��ת��ע�����
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
