package com.tarena.music.notification;

import java.util.List;

import com.tarena.music.entity.BMusic;

/**
 * �����ý�����ParserUtil��������ô˽ӿ��еĳ��󷽷�
 * �˽ӿ��г��󷽷�����ʵ�ָ���UI����
 * ������TJFragment��ʵ��,���¸����б���Ϣ
 */
public interface IParser {
	/**
	 * �ӵ��ô����ݼ�������,ʵ�ִ��ô�list���ϸ��½���
	 * @param list
	 */
	void parserSuccess(List<BMusic> list);
	
	
	
}
