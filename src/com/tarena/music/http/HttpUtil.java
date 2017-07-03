package com.tarena.music.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tarena.music.notification.IParser;
import com.tarena.music.parser.ParserUtil;
import com.tarena.music.util.ConfigUitl;

/**
 * ����������������,���ʳɹ��� ���ý������߽���json�ַ�
 * 
 */
public class HttpUtil implements ConfigUitl {
	/**
	 * ����������������,ͨ�����������������ɲ�ѯjson����
	 * 
	 * @param name
	 *            ������ �������Ǹ�
	 * @param coentxt
	 *            �����Ķ���,�������ݸ���������ʹ��,ʹ�÷��͹㲥
	 */
	public void getMusicByName(String name, final Context context) {
		// �������
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = BADU_URL_SEARCH + name;
		// ��������
		StringRequest sr = new StringRequest(url, new Listener<String>() {
			public void onResponse(String json) {
				// ���ý���������������
				ParserUtil pu = new ParserUtil();
				pu.parserJSONSearch(json, context);
			}
		}, new ErrorListener() {
			public void onErrorResponse(VolleyError arg0) {

			}
		});
		// ��sr������Ӷ���
		queue.add(sr);

	}

	/***
	 * �˷�������TJFragment���������������
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param type
	 *            ��������
	 * @param parser
	 *            �ص�����
	 */
	public void getMusics(Context context, int type, final IParser parser) {
		/** ʹ��Volley���ʵ�ֻ��JSON���� */
		// 1 ���Volley�������RequestQueue
		RequestQueue queue = Volley.newRequestQueue(context);
		// 2ƴ��url
		String url = BAIDU_URL_START + type + BAIDU_URL_END;
		// 3���������������
		// �ַ���������������
		StringRequest sr = new StringRequest(url, new Listener<String>() {
			public void onResponse(String json) {
				// �ɹ�������ݺ�ִ�д˴�����
				// ���ý�����Ľ�����������json�ַ���
				// �˴�����ִ�������߳�
				// Log.i("TAG", Thread.currentThread().getName()+"");
				ParserUtil pu = new ParserUtil();
				pu.parserJSON(json, parser);
			}
		}, new ErrorListener() {
			public void onErrorResponse(VolleyError arg0) {
				// ��������ʧ�����˴�����
				// ��������δ�����κ�����
				Log.i("TAG", arg0.getMessage());
			}
		});
		// 4 ���ַ������������������񽻸���Ϣ����
		queue.add(sr);

	}

	/**
	 * ͨ��id���Ҷ�Ӧ�ĸ������� ��Ҫʹ��songLink���ص�ַ
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param id
	 *            ������id
	 */

	public void downFileMusic(final Context context, String id) {
		/** ʹ��Volley��ܷ��ʰٶȷ����� */
		// ����
		RequestQueue queue = Volley.newRequestQueue(context);
		// ����
		String url = BADU_URL_SEARCH_ID + id;
		StringRequest sr = new StringRequest(url, new Listener<String>() {
			public void onResponse(String arg0) {
				// /////����JSON����///////
				try {
					JSONObject object = new JSONObject(arg0);
					// ��ȡdata:��Ӧ��JSONObject����
					JSONObject dataObject = object.getJSONObject("data");
					// ���songList��Ӧ��JSONArray����
					JSONArray array = dataObject.getJSONArray("songList");
					// ��ú������ص�ַ��JSONObject����
					JSONObject o = array.getJSONObject(0);
					// ��ȡ���ص�ַ songLink
					final String songLink = o.getString("songLink");
					final String songName = o.getString("songName");
					Log.i("TAG", songLink);
					// // �����app���������������
					// // ������ͼ
					// Intent intent=new Intent();
					// //������ͼ��Ϣ
					// intent.setAction("android.intent.action.VIEW");
					// //׼�������ַ
					// Uri musicUri=Uri.parse(songLink);
					// //��װ��intent��
					// intent.setData(musicUri);
					// //���������
					// context.startActivity(intent);
					new Thread() {
						public void run() {
							try {
								getMusicFileDown(songLink, songName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						};
					}.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {
			public void onErrorResponse(VolleyError arg0) {

			}
		});
		// ����Ķ�����
		queue.add(sr);

	}

	/**
	 * ʵ��ʹ��HttpURlConncation����ָ�����ļ� �ŵ�mnt/sdcard/musics
	 * 
	 * @param songLink
	 *            �������ص�ַ
	 * @param songName
	 *            �洢�ļ���
	 * @throws Exception
	 */
	protected void getMusicFileDown(String songLink, String songName)
			throws Exception {
		// 1 ׼���洢·�� mnt/sdcard
		// ��ȡ����SdCard�ĸ�Ŀ¼
		String path = Environment.getExternalStorageDirectory() + "/music/";
		// 2 ��װ·����URL��
		URL url = new URL(songLink);
		// 3 ͨ��url���HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Log.i("TAG", "�������ӳɹ�");
		// 4 ��ȡ����������
		InputStream is = conn.getInputStream();
		// 5 ���������
		// ���������ļ��Ķ���File
		File f = new File(path + songName + ".mp3");
		// �ж��ļ��Ƿ����
		if (f.exists()) {
			return;
		}
		// �����
		OutputStream os = new FileOutputStream(f);
		// �ȶ���һ���ֽڻ����
		byte[] arry = new byte[1024];
		// ѭ����ȡ���ݲ�д�뵽�ļ���
		int count = 0;
		int size = 0;
		Log.i("TAG", "��ʼ��ȡ����");
		while ((count = is.read(arry)) != -1) {
			// ��ȡarry���ֽ�����,��0��ʼ��count��Ч���ݸ���
			os.write(arry, 0, count);
			size = size + count;
			Log.i("TAG", "���ݶ�ȡ��" + size);
		}
		os.close();
		is.close();
		Log.i("TAG", "���سɹ�");

	}
}
