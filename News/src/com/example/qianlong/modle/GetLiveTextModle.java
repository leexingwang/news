package com.example.qianlong.modle;

import java.util.List;

import com.example.qianlong.bean.Live;

public interface GetLiveTextModle {

	public void getLiveInfo(int pagesize, int page, final int loadTypefinal,
			OnLiveListener onLiveListener);

	public interface OnLiveListener {
		public void onLiveSuccess(List<Live> lives, int loadType);

		public void onLiveError(int loadType);
	}
}
