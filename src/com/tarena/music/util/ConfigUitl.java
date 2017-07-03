package com.tarena.music.util;

/***
 * 存放整个程序所需要的常量字符串
 */
public interface ConfigUitl {
	/** 获取从推荐Fragment到SearchActivity中的数据key */
	String SEARCH_KEY_NAME = "com.tarena.music.name.key";

	/** 通过歌手歌曲名称查询json */
	String BADU_URL_SEARCH = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.search.catalogSug&format=json&query=";
	/** 通过ID查询歌曲象形json 
	 * 连接当前歌曲的id值*/
	String BADU_URL_SEARCH_ID= "http://ting.baidu.com/data/music/links?songIds=";

	/** 请求网址的前半部分,后面连请求歌曲类型 */
	String BAIDU_URL_START = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=";
	/** 请求网络后半部分,offset size可以自行修正 */
	String BAIDU_URL_END = "&offset=0&size=50";

	/** KTV热歌榜 */
	int KTV = 6;
	/** 经典老歌 */
	int JDLG = 22;
	/** 爵士歌曲 */
	int JS = 12;
	/** 流行歌曲 */
	int LXGQ = 16;
	/** 欧美歌曲 */
	int OMJQ = 21;
	/** 情歌对唱 */
	int QGDC = 23;
	/** 热歌榜 */
	int RGB = 2;
	/** 网络歌曲 */
	int WLGQ = 25;
	/** 新歌榜 */
	int XGB = 1;
	/** 摇滚榜 */
	int YGB = 11;
	/** 影视金曲 */
	int YSJQ = 24;

	/** 启动播放界面时取出实体类的key值 */
	String ENTITY_KEY = "com.tarena.music.entity";
	
	
	/**查询歌曲名称或者歌手名称是广播意图的key值*/
	String BROAD_SERCAH_ACTION="com.tarena.music.key.search.action";
	/**发送广播中数据类型的key值*/
	String BROAD_SERCAH_TYPE_KEY="com.tarena.music.key.type";
	/***封装通过name查询歌曲列表的key值**/
	String BROAD_SERCAH_DATA_SONG_KEY="com.tarena.music.key.song";
	/***封装通过name查询唱片列表的key值**/
	String BROAD_SERCAH_DATA_ALBUM_KEY="com.tarena.music.key.album";
	
	
	/**读取封装查询数据集合时使用的key值*/
	String BROAD_SERCH_BUNDLE_KEY="com.taren.music.key.bundle";
	
	
	/** 播放意图key值 */
	String PLAY_ACTION = "com.tarena.music.paly";

	/** 播放进度的key值 */
	String PLAY_PROGESS_KEY = "com.tarena.music.progess";

	/** 意图参数 播放意图 0 */
	int MUSIC_PLAY = 0;
	/** 意图参数 暂停意图 1 */
	int MUSIC_PUASE = 1;
	/** 意图参数 上一首意图 2 */
	int MUSIC_PRO = 2;
	/** 意图参数 下一首意图 3 */
	int MUSIC_NEXT = 3;
	/** 意图参数 下一首意图 4 */
	int MUSIC_PLAYING = 4;
	/** 意图参数 更新播放位置意图 5 */
	int MUSIC_PROGESS = 5;
	/** 意图参数 通知连更新播放状态 6 */
	int MUSIC_NOTIFT_PALY_OR_PAUSE = 6;
	/** 意图参数 推出程序 7 */
	int MUSIC_EXIT = 7;

	/** 广播数据封装时的总时长的key */
	String BROD_KEY_TIME = "com.tarena.music.time";
	/** 广播数据封装时的总时长的key */
	String BROD_KEY_STATA = "com.tarena.music.stata";
	/** 广播数据封装时的当前时长的key */
	String BROD_KEY_CURR_TIME = "com.tarena.music.curr.time";

	/** 广播数据封装时更新歌曲名key */
	String BROD_KEY_UPDTA_SONG = "com.tarena.music.song";
	/** 广播数据封装时更新歌手名key */
	String BROD_KEY_UPDTA_SINGER = "com.tarena.music.singer";

	/** 广播接收器的action 频道 更新当前播放进度 */
	String BROD_ACTION_UPDATA = "com.tarena.music.aciton.updata";
	/** 广播接收器的action 频道 更新切换歌曲名和歌手名 */
	String BROD_ACTION_TITLE = "com.tarena.music.aciton.title";

}
