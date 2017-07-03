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
	/**��Ӧ�����¼�ı�����*/
	private TextView tvLogin;
	/**�˷�������ָ�����صĲ����ļ�*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    /**��ü��ز����ļ��Ķ���v**/
         View v=View.inflate(getActivity(),//��ǰactivity
        		 R.layout.fragment_wd, //���صĲ����ļ�
        		 null);
        //��ʼ���ؼ�
          initViews(v);
		//��ʼ���¼�����
          initListeners();
		
		/**���˶���v����*/
		return v;
	}
	/**
	 * ��ʼ�������ؼ����¼�����
	 */
	private void initListeners() {
		tvLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//��ת����¼����
				Intent intent=
						new Intent(getActivity(),
								LoginActivity.class);
//				startActivity(intent);
				//����LoginActivity��������ر�ʱ�ش�������
				startActivityForResult(intent, 100);
			}
		});
	}
	
	/**��ִ��LoginActivity��setResultʱִ�д˴�����*/
	@Override
	public void onActivityResult(
			int requestCode,//������  ��������ʱ��
			int resultCode, //�����  ���ؽ��ʱ��
			Intent data) {// ������� ���ؽ����װ����
		//�̵�requestCode�ǲ���100 resultCode�ǲ���200
		Log.i("TAG", "onActivityResult");
		Log.i("TAG", "onActivityResult"+requestCode+"---"+resultCode);
		
		if(requestCode==100&&resultCode==200){
		   //���������ͽ���붼ƥ��
			String name=data.getExtras().getString("name");
		   //���data�е�����
			Log.i("TAG", "onActivityResult"+name);
			//��name���õ�tvLogin�ؼ�
			 tvLogin.setText(name);
			
		}
	}
	
	
	/**
	 * ��ʼ�������ؼ�
	 * @param v ��ǰ���沼���ļ�
	 */
	private void initViews(View v) {
     tvLogin=(TextView) 
    		 v.findViewById(R.id.wd_tv_login);
	}
}
