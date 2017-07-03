package com.tarena.music.util;

/***
 * ���������������Ҫ�ĳ����ַ���
 */
public interface ConfigUitl {
	/** ��ȡ���Ƽ�Fragment��SearchActivity�е�����key */
	String SEARCH_KEY_NAME = "com.tarena.music.name.key";

	/** ͨ�����ָ������Ʋ�ѯjson */
	String BADU_URL_SEARCH = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.search.catalogSug&format=json&query=";
	/** ͨ��ID��ѯ��������json 
	 * ���ӵ�ǰ������idֵ*/
	String BADU_URL_SEARCH_ID= "http://ting.baidu.com/data/music/links?songIds=";

	/** ������ַ��ǰ�벿��,����������������� */
	String BAIDU_URL_START = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=";
	/** ���������벿��,offset size������������ */
	String BAIDU_URL_END = "&offset=0&size=50";

	/** KTV�ȸ�� */
	int KTV = 6;
	/** �����ϸ� */
	int JDLG = 22;
	/** ��ʿ���� */
	int JS = 12;
	/** ���и��� */
	int LXGQ = 16;
	/** ŷ������ */
	int OMJQ = 21;
	/** ���Գ� */
	int QGDC = 23;
	/** �ȸ�� */
	int RGB = 2;
	/** ������� */
	int WLGQ = 25;
	/** �¸�� */
	int XGB = 1;
	/** ҡ���� */
	int YGB = 11;
	/** Ӱ�ӽ��� */
	int YSJQ = 24;

	/** �������Ž���ʱȡ��ʵ�����keyֵ */
	String ENTITY_KEY = "com.tarena.music.entity";
	
	
	/**��ѯ�������ƻ��߸��������ǹ㲥��ͼ��keyֵ*/
	String BROAD_SERCAH_ACTION="com.tarena.music.key.search.action";
	/**���͹㲥���������͵�keyֵ*/
	String BROAD_SERCAH_TYPE_KEY="com.tarena.music.key.type";
	/***��װͨ��name��ѯ�����б��keyֵ**/
	String BROAD_SERCAH_DATA_SONG_KEY="com.tarena.music.key.song";
	/***��װͨ��name��ѯ��Ƭ�б��keyֵ**/
	String BROAD_SERCAH_DATA_ALBUM_KEY="com.tarena.music.key.album";
	
	
	/**��ȡ��װ��ѯ���ݼ���ʱʹ�õ�keyֵ*/
	String BROAD_SERCH_BUNDLE_KEY="com.taren.music.key.bundle";
	
	
	/** ������ͼkeyֵ */
	String PLAY_ACTION = "com.tarena.music.paly";

	/** ���Ž��ȵ�keyֵ */
	String PLAY_PROGESS_KEY = "com.tarena.music.progess";

	/** ��ͼ���� ������ͼ 0 */
	int MUSIC_PLAY = 0;
	/** ��ͼ���� ��ͣ��ͼ 1 */
	int MUSIC_PUASE = 1;
	/** ��ͼ���� ��һ����ͼ 2 */
	int MUSIC_PRO = 2;
	/** ��ͼ���� ��һ����ͼ 3 */
	int MUSIC_NEXT = 3;
	/** ��ͼ���� ��һ����ͼ 4 */
	int MUSIC_PLAYING = 4;
	/** ��ͼ���� ���²���λ����ͼ 5 */
	int MUSIC_PROGESS = 5;
	/** ��ͼ���� ֪ͨ�����²���״̬ 6 */
	int MUSIC_NOTIFT_PALY_OR_PAUSE = 6;
	/** ��ͼ���� �Ƴ����� 7 */
	int MUSIC_EXIT = 7;

	/** �㲥���ݷ�װʱ����ʱ����key */
	String BROD_KEY_TIME = "com.tarena.music.time";
	/** �㲥���ݷ�װʱ����ʱ����key */
	String BROD_KEY_STATA = "com.tarena.music.stata";
	/** �㲥���ݷ�װʱ�ĵ�ǰʱ����key */
	String BROD_KEY_CURR_TIME = "com.tarena.music.curr.time";

	/** �㲥���ݷ�װʱ���¸�����key */
	String BROD_KEY_UPDTA_SONG = "com.tarena.music.song";
	/** �㲥���ݷ�װʱ���¸�����key */
	String BROD_KEY_UPDTA_SINGER = "com.tarena.music.singer";

	/** �㲥��������action Ƶ�� ���µ�ǰ���Ž��� */
	String BROD_ACTION_UPDATA = "com.tarena.music.aciton.updata";
	/** �㲥��������action Ƶ�� �����л��������͸����� */
	String BROD_ACTION_TITLE = "com.tarena.music.aciton.title";

}
