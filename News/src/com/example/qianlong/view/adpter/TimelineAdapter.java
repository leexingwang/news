package com.example.qianlong.view.adpter;

import java.util.List;

import com.example.qianlong.R;
import com.example.qianlong.bean.Live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimelineAdapter extends BaseAdapter {

	private Context context;
	private List<Live> list;
	private LayoutInflater inflater;

	public TimelineAdapter(Context context, List<Live> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		Live live = list.get(position);
		if (convertView == null) {
			inflater = LayoutInflater.from(this.context);
			convertView = inflater.inflate(R.layout.cbn_timeline_listview_item,
					null);
			viewHolder = new ViewHolder();
			viewHolder.live_content = (TextView) convertView
					.findViewById(R.id.text_live_content);
			viewHolder.year_month_day = (TextView) convertView
					.findViewById(R.id.text_year_month_day);
			viewHolder.live_time = (TextView) convertView
					.findViewById(R.id.text_live_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (position == 0) {
			viewHolder.year_month_day.setVisibility(View.VISIBLE);
			viewHolder.year_month_day.setText(live.getLiveDate().subSequence(0,
					10));
		} else {
			viewHolder.year_month_day.setVisibility(View.GONE);
		}
		viewHolder.live_content.setText(live.getLiveContent());
		viewHolder.live_time.setText(live.getLiveDate().subSequence(11,
				live.getLiveDate().length()));
		return convertView;
	}

	static class ViewHolder {
		public TextView year_month_day;
		public TextView live_content;
		public TextView live_time;
	}
}
