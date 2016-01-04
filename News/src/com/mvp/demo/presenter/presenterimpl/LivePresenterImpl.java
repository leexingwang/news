package com.mvp.demo.presenter.presenterimpl;

import java.util.List;

import com.example.qianlong.bean.Live;
import com.lidroid.xutils.exception.HttpException;
import com.mvp.demo.model.modelimpl.Live7_24Model.OnLiveListener;
import com.mvp.demo.model.modelimpl.Live7_24ModelImpl;
import com.mvp.demo.presenter.LivePresenter;
import com.mvp.demo.view.LiveView;

public class LivePresenterImpl implements OnLiveListener, LivePresenter {

	Live7_24ModelImpl live7_24ModelImpl=new Live7_24ModelImpl();
	LiveView liveView;
	
	public LivePresenterImpl(LiveView liveView) {
        this.liveView = liveView;
        live7_24ModelImpl=new Live7_24ModelImpl();
    }
	@Override
	public void onSuccess(List<Live> lives) {
		liveView.setLiveInfo(lives);

	}

	@Override
	public void onError(HttpException arg0, String arg1) {
		liveView.setLiveErrorInfo(arg0, arg1);
	}

	@Override
	public void getLiveInfo(String command, String pagesize, String page,
			String type) {
		live7_24ModelImpl.getLiveInfo(command, pagesize, page, type, this);
	}

}
