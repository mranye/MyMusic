package com.tarena.music.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tarena.music.entity.Album;
import com.tarena.music.entity.BMusic;
import com.tarena.music.entity.SearMusic;
import com.tarena.music.notification.IParser;
import com.tarena.music.util.ConfigUitl;

/**
 * 
 * �������HttpUtil�����ʱ���ݹ�����JSON�ַ��� ����������,�ص��ӿ�IParser��ɸ��½���
 * 
 */
public class ParserUtil implements ConfigUitl {
	/**
	 * ����ͨ�����������߸��������JSON���� 1 ��õĸ����б� 2 ��ó�Ƭ�б�
	 * 
	 * @param json
	 *            JSON����
	 * @param context
	 *            �������͹㲥
	 */
	public void parserJSONSearch(String json, Context context) {
		try {
			// �����صĸ����б�
			ArrayList<SearMusic> sList = new ArrayList<SearMusic>();
			// ��������JSONObject����
			JSONObject object = new JSONObject(json);
			// ���ص�ר�������Ϣ
			ArrayList<Album> aList = new ArrayList<Album>();
			getAlbumMusic(context, aList, object);
			getSongMusic(context, sList, object);

		} catch (Exception e) {
			e.printStackTrace();

			/** ���͹㲥֪ͨ�����б���� */
			Intent intent = new Intent(BROAD_SERCAH_ACTION);
			// ���붯��
			intent.putExtra(BROAD_SERCAH_TYPE_KEY, -1);// 1 ��ʾ�����Ǹ����б�
			intent.putExtra("text", "�鲻������");
			context.sendBroadcast(intent);

		}

	}

	/**
	 * ��������װ��Ƭ��Ϣ�б�,����͹㲥֪ͨ�������
	 * 
	 * @param context
	 *            �������͹㲥
	 * @param aList
	 *            �����洢��Ƭ��Ϣ
	 * @param object
	 *            �����JSONObject����
	 * @throws JSONException
	 */
	private void getAlbumMusic(Context context, ArrayList<Album> aList,
			JSONObject object) throws JSONException {
		// ��ó�Ƭ��Ϣ�������
		JSONArray arr = object.getJSONArray("album");
		// ����arr��Ƭ����,��װ����
		for (int i = 0; i < arr.length(); i++) {
			Album a = new Album();
			JSONObject o = arr.getJSONObject(i);
			// ��ʼ��װ
			// ��Ƭ����
			String albumname = o.getString("albumname");
			a.setAlbumname(albumname);
			// ��Ƭ��������
			String artistname = o.getString("artistname");
			a.setArtistname(artistname);
			// ��ƬͼƬ��ַ
			String artistpic = o.getString("artistpic");
			a.setArtistpic(artistpic);
			// ��Ƭid
			String albumid = o.getString("albumid");
			a.setAlbumid(albumid);
			// ��aʵ���������뵽������
			aList.add(a);
		}
		// ////////���͹㲥֪ͨ���½���//////////////
		// ʹ��Intent��װ�㲥��ͼ
		Intent intent = new Intent(BROAD_SERCAH_ACTION);
		// ���붯�� ��Ƭ��Ϣ 2 ������Ϣ 1
		intent.putExtra(BROAD_SERCAH_TYPE_KEY, 2);
		// ʹ��Bundle��װ���϶���
		Bundle bundle = new Bundle();
		bundle.putSerializable(BROAD_SERCAH_DATA_ALBUM_KEY, aList);
		// /����bundle���뵽intent��
		intent.putExtra(BROAD_SERCH_BUNDLE_KEY, bundle);
		// ���͹㲥
		context.sendBroadcast(intent);

	}

	/**
	 * ��������װsong������Ϣ�б�
	 * 
	 * @param context
	 *            �������͹㲥ʹ��
	 * @param sList
	 *            ��װsong������Ϣ�б�
	 * @param object
	 *            JSONObject �������������json����
	 * @throws JSONException
	 *             JSON�����쳣
	 */
	private void getSongMusic(Context context, ArrayList<SearMusic> sList,
			JSONObject object) throws JSONException {
		// /���song��Ӧ��JSONArray����
		JSONArray array = object.getJSONArray("song");
		// /ѭ��������ǰ��array
		for (int i = 0; i < array.length(); i++) {
			JSONObject o = array.getJSONObject(i);
			SearMusic sm = new SearMusic();
			// songname
			String songname = o.getString("songname");
			sm.setSongname(songname);
			// songid
			String songid = o.getString("songid");
			sm.setSongid(songid);
			// artistname
			String artistname = o.getString("artistname");
			sm.setArtistname(artistname);
			// ������ѭ����ø���������뵽������
			sList.add(sm);
		}
		/** ���͹㲥֪ͨ�����б���� */
		Intent intent = new Intent(BROAD_SERCAH_ACTION);
		// ���붯��
		intent.putExtra(BROAD_SERCAH_TYPE_KEY, 1);// 1 ��ʾ�����Ǹ����б�
		// ������Դ����sList���뵽Bundle
		// Bundle �ڰ�׿�в����������֮�����ݵķ�װʹ��
		Bundle bundle = new Bundle();
		bundle.putSerializable(BROAD_SERCAH_DATA_SONG_KEY, sList);
		// ��Bundle���뵽intent��
		intent.putExtra(BROAD_SERCH_BUNDLE_KEY, bundle);
		// ���͹㲥���½���
		context.sendBroadcast(intent);
	}

	/**
	 * �������json�ַ�,�����ɹ������parser�� parserSuccess���½���
	 * 
	 * @param json
	 *            json����
	 * @param parser
	 *            �ص��ӿ�
	 */
	public void parserJSON(String json, IParser parser) {
		try {
			// ����������
			JSONObject object = new JSONObject(json);
			// ���song_list������JSON����
			JSONArray ja = object.getJSONArray("song_list");
			/** ȡ��ja���������е����ݷ�װ��List���� */
			List<BMusic> list = new ArrayList<BMusic>();
			// ����ja����
			for (int i = 0; i < ja.length(); i++) {
				BMusic b = new BMusic();
				// ��õ�ǰiλ���ϵ�JSONObject����
				JSONObject o = ja.getJSONObject(i);
				// ��ȡ������������
				try {
					String language = o.getString("language");
					b.setLanguage(language);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// ��ø�����ͼƬ��ַ
					String pic_big = o.getString("pic_big");
					b.setPic_big(pic_big);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// ��ø���СͼƬ��ַ
					String pic_small = o.getString("pic_small");
					b.setPic_small(pic_small);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ��ȡ������Ϣ
					String country = o.getString("country");
					b.setCountry(country);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ��������publishtime
					String publishtime = o.getString("publishtime");
					b.setPublishtime(publishtime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ������ص�ַ
					String lrclink = o.getString("lrclink");
					b.setLrclink(lrclink);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ��ע��
					String hot = o.getString("hot");
					b.setHot(hot);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ����id
					String song_id = o.getString("song_id");
					b.setSong_id(song_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ��������
					String title = o.getString("title");
					b.setTitle(title);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ��������
					String author = o.getString("author");
					b.setAuthor(author);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ר��id
					String album_id = o.getString("album_id");
					b.setAlbum_id(album_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// ר������
					String album_title = o.getString("album_title");
					b.setAlbum_title(album_title);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// /���Ͻ�����Ҫ�����ݷ��뵽ʵ����b��
				// ����װ��ʵ����b���뵽������
				list.add(b);
			}
			// �����ɹ��Ժ�ص��ӿڷ���
			parser.parserSuccess(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
