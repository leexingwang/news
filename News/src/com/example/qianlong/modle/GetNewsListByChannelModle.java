package com.example.qianlong.modle;

import java.util.List;

import com.example.qianlong.bean.News;
import com.lidroid.xutils.exception.HttpException;

public interface GetNewsListByChannelModle {

	/**
	 * ��ȡ������Ŀ�����б� һ����Ŀ����ķ����ã��������Ŷ�Ҫ�ڶ�����Ŀ����ʵ����
	 * <p>
	 * cid int ������ĿID���μ�����б�����ʾ����Ŀ��ID
	 * <p>
	 * pagesize int ��ҳ�ã�ÿҳ����
	 * <p>
	 * page int ��ǰҳ�����ӵ�1ҳ��ʼ��
	 * <p>
	 * check string ��֤�ã��뽫cid+pagesize+page+Key����MD5Сд
	 * 
	 * @param cid
	 * @param pagesize
	 * @param page
	 */
	public void getNewsListByChannel(int cid, int pageSize, int page,
			int loadType, OnNewsListByChannelListener newsListByChannelListener);

	public interface OnNewsListByChannelListener {
		public void onSuccess(List<News> lives, int loadType);

		public void onError(int loadType);
	}
}
