package com.qianlong.android.adapter;

import java.util.ArrayList;

import com.example.qianlong.R;
import com.example.qianlong.bean.TopicListBean.Topic;
import com.example.qianlong.utils.CommonUtil;
import com.example.qianlong.utils.Constants;
import com.example.qianlong.utils.SharePrefUtil;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TopicAdapter extends BaseAdapter {
	private ArrayList<Topic> datas;
	private Context context;
	private BitmapUtils bitmapUtils;
	private String countCommentUrl;
	int read_model;

	public TopicAdapter(Context context, ArrayList<Topic> datas,
			String countCommentUrl) {
		this.context = context;
		this.datas = datas;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadingImage(R.drawable.pic_item_list_default);
		this.countCommentUrl = countCommentUrl;
		read_model = SharePrefUtil.getInt(context, Constants.READ_MODEL, 1);
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.layout_item_topic, null);
			holder.title = (TextView) convertView
					.findViewById(R.id.tv_topic_title);
			holder.topic_iv = (ImageView) convertView.findViewById(R.id.iv_topic);
			int width = context.getResources().getDisplayMetrics().widthPixels-CommonUtil.dip2px(context, 20);
			int height = width*320/640;
			LayoutParams ivLp = holder.topic_iv.getLayoutParams();
			ivLp.width = width;
			ivLp.height= height;
			holder.topic_iv.setLayoutParams(ivLp);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Topic topic = datas.get(position);
		holder.title.setText(topic.title);
		if (TextUtils.isEmpty(topic.listimage)) {
			holder.topic_iv.setVisibility(View.GONE);
		} else {
			switch (read_model) {
			case 1:
				int type = CommonUtil.isNetworkAvailable(context);
				if (type == 1) {
					holder.topic_iv.setVisibility(View.VISIBLE);
					bitmapUtils.display(holder.topic_iv,
							topic.listimage);
				} else {
					holder.topic_iv.setImageResource(R.drawable.transparent);
				}
				break;
			case 2:
				holder.topic_iv.setVisibility(View.VISIBLE);
				bitmapUtils.display(holder.topic_iv,
						topic.listimage);
				break;
			case 3:
				holder.topic_iv.setImageResource(R.drawable.transparent);
				break;

			default:
				break;
			}

		}
		return convertView;
	}

	public void notifyData() {
		read_model = SharePrefUtil.getInt(context, Constants.READ_MODEL, 1);
		notifyDataSetChanged();
	}
	class ViewHolder {
		TextView title;
		ImageView topic_iv;
	}

}
