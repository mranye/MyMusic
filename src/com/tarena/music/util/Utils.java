package com.tarena.music.util;

/**
 * 工具类
 */
public class Utils {
	/**
	 * 通过name (KTV歌曲,金典老歌) 获得对应的请求type
	 * 
	 * @param name
	 *            歌曲榜文本内容
	 * @return 请求类型之值 1 表示新歌榜
	 */
	public static int getTypeByName(String name) {
		/** 使用if-else-if语句判断 */
		if ("KTV歌曲".equals(name)) {
			return 6;
		} else if ("经典老歌".equals(name)) {
			return 22;
		} else if ("爵士歌曲".equals(name)) {
			return 12;
		} else if ("流行歌曲".equals(name)) {
			return 16;
		} else if ("欧美金曲".equals(name)) {
			return 21;
		} else if ("情歌对唱".equals(name)) {
			return 23;
		} else if ("热歌榜".equals(name)) {
			return 2;
		} else if ("网络歌曲".equals(name)) {
			return 25;
		} else if ("新歌榜".equals(name)) {
			return 1;
		} else if ("摇滚榜".equals(name)) {
			return 11;
		} else if ("影视金曲".equals(name)) {
			return 24;
		}
		return 1;

	}

}
