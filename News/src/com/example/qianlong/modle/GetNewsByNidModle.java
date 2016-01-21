package com.example.qianlong.modle;

public interface GetNewsByNidModle {

	public void getNewsByNid(int nid, int type,
			OnGetNewsByNidListener onGetNewsByNidListener);

	public interface OnGetNewsByNidListener {
		public <T> void onGetNewsSuccess(T t, int loadType);

		public void onGetNewsError(int loadType);
	}
}
