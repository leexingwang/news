package com.example.qianlong.modle;

import java.util.List;

import com.example.qianlong.bean.News;
import com.lidroid.xutils.exception.HttpException;

public interface GetNewsListByChannelModle {

	/**
	 * 获取二级栏目新闻列表 一级栏目是虚的分类用，所有新闻都要在二级栏目中真实存在
	 * <p>
	 * cid int 二级栏目ID，参见左侧列表里显示的栏目号ID
	 * <p>
	 * pagesize int 分页用，每页数量
	 * <p>
	 * page int 当前页数，从第1页开始起
	 * <p>
	 * check string 验证用，请将cid+pagesize+page+Key并用MD5小写
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
