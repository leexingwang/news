package com.example.qianlong.view.adpter;

import java.util.List;

import com.example.qianlong.R;
import com.example.qianlong.bean.News;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.utils.StringUtils;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeNewsItemAdapter extends BaseAdapter {

	private List<News> news;
	private Context context;

	public HomeNewsItemAdapter(Context context, List<News> news) {
		super();
		this.news = news;
		this.context = context;
	}

	@Override
	public int getCount() {
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		News newitem = news.get(position);
		/**
		 * <p>
		 * NewsType - 新闻类型
		 * <p>
		 * 类型 说明
		 * <p>
		 * int 新闻类型分为 实体新闻 和 跳连新闻 两大类，有一个字段 IsEntity 用来快速判断是否是实体新闻
		 * 实体类型：实体类型+100=对应的跳链类型
		 * <p>
		 * 10=新闻；11=图集；12=视频；13=外链；14=专题；15=直播；16=通栏；17=报名；18=见面会；19=大事件
		 * <p>
		 * 跳链类型：跳链类型-100=对应的实体类型
		 * <p>
		 * 110=新闻跳链；111=图集跳链；112=视频跳链；113=外链跳链；114=专题跳链；115=直播跳链；116=通栏跳链；117=
		 * 报名跳链； 118=见面会跳链；119=大事件跳链
		 * <p>
		 * EntityPath 为实体新闻的 年/月 格式，用来拼接完整url用，如果是跳链则为实体新闻的年/月
		 * 
		 */
		switch (newitem.getNewsType()) {
		case Constants.NewsType_XINWEN:

			break;
		case Constants.NewsType_TUJI:

			break;
		case Constants.NewsType_SHIPING:

			break;
		case Constants.NewsType_WAILIAN:

			break;
		case Constants.NewsType_ZHUANTI:

			break;
		case Constants.NewsType_ZHIBO:

			break;
		case Constants.NewsType_TONGLAN:

			break;
		case Constants.NewsType_BAOMING:

			break;
		case Constants.NewsType_JIANMIANHUI:

			break;
		case Constants.NewsType_DASHIJIAN:

			break;

		default:

			break;
		}
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.context);
			convertView = inflater.inflate(
					R.layout.cbn_item_news_adpter_news, null);
			viewHolder = new ViewHolder();
			viewHolder.imageview_NewsThumbs = (ImageView) convertView
					.findViewById(R.id.imageview_NewsThumbs);
			viewHolder.textview_NewsNotes = (TextView) convertView
					.findViewById(R.id.textview_NewsNotes);
			viewHolder.textview_NewsTags = (TextView) convertView
					.findViewById(R.id.textview_NewsTags);
			viewHolder.textview_NewsTagsOrDate = (TextView) convertView
					.findViewById(R.id.textview_NewsTagsOrDate);
			viewHolder.textview_NewsTitle = (TextView) convertView
					.findViewById(R.id.textview_NewsTitle);
			viewHolder.textview_NewsNotes = (TextView) convertView
					.findViewById(R.id.textview_NewsNotes);
			viewHolder.textview_NewsTags = (TextView) convertView
					.findViewById(R.id.textview_NewsTags);
			viewHolder.textview_NewsTagsOrDate = (TextView) convertView
					.findViewById(R.id.textview_NewsTagsOrDate);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
			if (!newitem.getNewsThumbs().startsWith("http://")) {
				Picasso.with(context)
						.load(Constants.IMGURL + Constants.IMGURL_ORIGIN
								+ newitem.getNewsThumbs())
						.into(viewHolder.imageview_NewsThumbs);
			} else {
				Picasso.with(context).load(newitem.getNewsThumbs())
						.into(viewHolder.imageview_NewsThumbs);
			}

		} else {
			viewHolder.imageview_NewsThumbs.setVisibility(View.GONE);
		}
		viewHolder.textview_NewsNotes.setText(newitem.getNewsNotes());
		viewHolder.textview_NewsTitle.setText(newitem.getNewsTitle());
		viewHolder.textview_NewsTags.setText(newitem.getTags());
		viewHolder.textview_NewsTagsOrDate.setText(newitem.getCreateDate());
		return convertView;
	}

	static class ViewHolder {
		public ImageView imageview_NewsThumbs;
		public TextView textview_NewsTitle;
		public TextView textview_NewsNotes;
		public TextView textview_NewsTags;
		public TextView textview_NewsTagsOrDate;
	}
}
