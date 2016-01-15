package com.example.qianlong.view.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.qianlong.R;
import com.example.qianlong.base.BaseActivity;
import com.example.qianlong.utils.NetWorkUtils;
import com.example.qianlong.utils.ScreenUtils;
import com.example.qianlong.utils.SharePrefUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CBNLiveBroadCastActivity extends BaseActivity implements
		OnPreparedListener, OnCompletionListener, OnErrorListener {

	private TextView textView_nowContent;
	private TextView textView_nextContent;
	private ImageView imageView_LiveBroadCastPause;
	private ImageView imageView_LiveBroadCastPlay;
	private ImageView imageView_LiveTop;
	/** 播放列表 */
	private Vector<PlaylistFile> playlistItems;
	/** 媒体播放器 */
	private MediaPlayer mediaPlayer;
	/** 播放列表中的数目 */
	private int currentPlaylistItemNumber = 0;

	private boolean loadError = false;

	private static String BroadCastURL = "http://fmradio.smgtech.net:1935/live/977/playlist.m3u8";

	@Override
	protected void initView() {
		setContentView(R.layout.cbn_live_broadcast_layout);
		initTitleBar();
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		initViews();
		initBroadCast();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.cbn_live_gb_pause:
			pauseStatus();
			break;
		case R.id.cbn_live_gb_play:
			if (playAvailabl()) {
				playStatus();
			}
			break;
		default:
			break;
		}
	}

	private void initViews() {
		textView_nowContent = (TextView) findViewById(R.id.text_live_broadcast_now_content);
		textView_nextContent = (TextView) findViewById(R.id.text_live_broadcast_next_content);
		imageView_LiveBroadCastPause = (ImageView) findViewById(R.id.cbn_live_gb_pause);
		imageView_LiveBroadCastPlay = (ImageView) findViewById(R.id.cbn_live_gb_play);
		imageView_LiveTop = (ImageView) findViewById(R.id.cbn_live_gb_img_top);
		imageView_LiveBroadCastPause.setOnClickListener(this);
		imageView_LiveBroadCastPlay.setOnClickListener(this);
		LayoutParams ps = (LayoutParams) imageView_LiveTop.getLayoutParams();
		int width = 1020;
		int height = 680;
		ps.height = ScreenUtils.getScreenWidth(this) * height / width;
		imageView_LiveTop.setLayoutParams(ps);

	}

	private void initBroadCast() {
		parsePlaylistFile();
		// 实例化mediaPlayer
		mediaPlayer = new MediaPlayer();
		// mediaplayer播放完第一次后又重新播放方法
		mediaPlayer.setOnCompletionListener(this);
		mediaPlayer.setOnErrorListener(this);
	}

	private void playStatus() {
		imageView_LiveBroadCastPause.setVisibility(View.VISIBLE);
		imageView_LiveBroadCastPlay.setVisibility(View.GONE);
		mediaPlayer.reset();
		playPlaylistItems();
	}

	private void pauseStatus() {
		imageView_LiveBroadCastPause.setVisibility(View.GONE);
		imageView_LiveBroadCastPlay.setVisibility(View.VISIBLE);
		mediaPlayer.pause();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// 播放
		mediaPlayer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mediaPlayer != null) {
			pauseStatus();
		}
	}

	/** 解析m3u8格式地址 */
	private void parsePlaylistFile() {
		playlistItems = new Vector<PlaylistFile>();
		// 为了从Web获取M3U文件，可以使用Apache软件基金会的HttpClient库，
		// 首先创建一个HttpClient对象，其代表类似Web浏览器的事物；
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				// 然后创建一个HttpGet对象，其表示指向一个文件的具体请求。
				HttpGet getRequest = new HttpGet(BroadCastURL);
				// HttpClient将执行HttpGet，并返回一个HttpResponse
				try {
					HttpResponse httpResponse = httpClient.execute(getRequest);
					if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
						Log.v("HTTP ERROR", httpResponse.getStatusLine()
								.getReasonPhrase());
					} else {
						// 在发出请求之后，可以从HttpRequest中获取一个InputStream，
						// 其包含了所请求文件的内容
						InputStream inputStream = httpResponse.getEntity()
								.getContent();
						// 借助一个BufferedReader可以逐行得遍历该文件
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(inputStream));
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							Log.v("PLAYLISTLINE", "ORIG:" + line);
							if (line.startsWith("#")) {
								// 元数据，可以做更多的处理，但现在忽略它
							} else if (line.length() > 0) {
								// 如果它的长度大于0，那么就假设它是一个播放列表条目
								String filePath = "";
								if (line.startsWith("http://")) {
									// 如果行以“http://”开头那么就把它作为流的完整URL
									filePath = line;
								} else {
									// 否则把它作为一个相对的URL，
									// 同时把针对该M3U文件的原始请求的URL附加上去
									filePath = getRequest.getURI()
											.resolve(line).toString();
								}
								// 将其添加到播放列表条目的容器中去
								PlaylistFile playlistFile = new PlaylistFile(
										filePath);
								// 添加播放文件到播放列表
								playlistItems.add(playlistFile);
							}
						}
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	/** 播放列表 */
	private void playPlaylistItems() {
		currentPlaylistItemNumber = 0;
		// 判断是否有播放的文件
		if (playlistItems.size() > 0) {
			// 在提取出流的或者文件的路径之后，就可以在MediaPlayer上的setDataSource方法使用它了
			String path = ((PlaylistFile) playlistItems
					.get(currentPlaylistItemNumber)).getFilePath();
			try {
				// 设置文件路径
				mediaPlayer.setDataSource(path);
				// 设置侦听器准备
				mediaPlayer.setOnPreparedListener(this);
				// 异步准备
				mediaPlayer.prepareAsync();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
		broadCastPlayerOver();
	}

	private void broadCastPlayerOver() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public boolean playAvailabl() {
		boolean isAllowPlay = true;
		if (NetWorkUtils.isNetworkAvailable(this)) {
			if (!SharePrefUtil.getBoolean(this,
					SharePrefUtil.KEY.SETTINGS_3G_4G_GUANKAN, true)
					&& !NetWorkUtils.isWifiDataEnable(this)) {// true：wifi才能观看(默认)
				new AlertDialog.Builder(this)
						.setTitle("提示")
						.setMessage("当前设置为wifi状态下才可收听广播")
						.setNegativeButton("退出",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										return;
									}
								}).create().show();
				return isAllowPlay = false;
			} else {
				return isAllowPlay;
			}

		} else {
			new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("当前网络不可用")
					.setNegativeButton("退出",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									return;
								}
							}).create().show();
			return isAllowPlay = false;
		}
	}

	/** 列表文件实体类 */
	public class PlaylistFile {
		/** 文件路径 */
		String filePath;

		public PlaylistFile(String _filePath) {
			filePath = _filePath;
		}

		public void setFilePath(String _filePath) {
			filePath = _filePath;
		}

		public String getFilePath() {
			return filePath;
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// 重置
		mediaPlayer.reset();
		// 判断播放列表和列表数目，如果播放列表不大于列表数目重新加载播放列表
		if (playlistItems.size() > currentPlaylistItemNumber + 1) {
			currentPlaylistItemNumber++;
			// 路径
			String path = ((PlaylistFile) playlistItems
					.get(currentPlaylistItemNumber)).getFilePath();
			try {
				// 设置文件路径
				mediaPlayer.setDataSource(path);
				// 异步准备
				mediaPlayer.prepareAsync();

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			parsePlaylistFile();
			// 重置
			mediaPlayer.reset();
			playPlaylistItems();
		}

	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		loadError = true;
		pauseStatus();
		return false;
	}

	@Override
	protected void finishChild() {
		// TODO Auto-generated method stub
		
	}
}
