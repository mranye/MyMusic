package com.tarena.music.util;

/**
 * ������
 */
public class Utils {
	/**
	 * ͨ��name (KTV����,����ϸ�) ��ö�Ӧ������type
	 * 
	 * @param name
	 *            �������ı�����
	 * @return ��������ֵ֮ 1 ��ʾ�¸��
	 */
	public static int getTypeByName(String name) {
		/** ʹ��if-else-if����ж� */
		if ("KTV����".equals(name)) {
			return 6;
		} else if ("�����ϸ�".equals(name)) {
			return 22;
		} else if ("��ʿ����".equals(name)) {
			return 12;
		} else if ("���и���".equals(name)) {
			return 16;
		} else if ("ŷ������".equals(name)) {
			return 21;
		} else if ("���Գ�".equals(name)) {
			return 23;
		} else if ("�ȸ��".equals(name)) {
			return 2;
		} else if ("�������".equals(name)) {
			return 25;
		} else if ("�¸��".equals(name)) {
			return 1;
		} else if ("ҡ����".equals(name)) {
			return 11;
		} else if ("Ӱ�ӽ���".equals(name)) {
			return 24;
		}
		return 1;

	}

}
