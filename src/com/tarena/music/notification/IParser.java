package com.tarena.music.notification;

import java.util.List;

import com.tarena.music.entity.BMusic;

/**
 * 用来让解析类ParserUtil解析后调用此接口中的抽象方法
 * 此接口中抽象方法用来实现跟新UI界面
 * 必须让TJFragment来实现,更新歌曲列表信息
 */
public interface IParser {
	/**
	 * 从调用处传递集合数据,实现处用此list集合更新界面
	 * @param list
	 */
	void parserSuccess(List<BMusic> list);
	
	
	
}
