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
	/** �����б� */
	private Vector<PlaylistFile> playlistItems;
	/** ý�岥���� */
	private MediaPlayer mediaPlayer;
	/** �����б��е���Ŀ */
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
		// ʵ����mediaPlayer
		mediaPlayer = new MediaPlayer();
		// mediaplayer�������һ�κ������²��ŷ���
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
		// ����
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

	/** ����m3u8��ʽ��ַ */
	private void parsePlaylistFile() {
		playlistItems = new Vector<PlaylistFile>();
		// Ϊ�˴�Web��ȡM3U�ļ�������ʹ��Apache���������HttpClient�⣬
		// ���ȴ���һ��HttpClient�������������Web����������
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				// Ȼ�󴴽�һ��HttpGet�������ʾָ��һ���ļ��ľ�������
				HttpGet getRequest = new HttpGet(BroadCastURL);
				// HttpClient��ִ��HttpGet��������һ��HttpResponse
				try {
					HttpResponse httpResponse = httpClient.execute(getRequest);
					if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
						Log.v("HTTP ERROR", httpResponse.getStatusLine()
								.getReasonPhrase());
					} else {
						// �ڷ�������֮�󣬿��Դ�HttpRequest�л�ȡһ��InputStream��
						// ��������������ļ�������
						InputStream inputStream = httpResponse.getEntity()
								.getContent();
						// ����һ��BufferedReader�������еñ������ļ�
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(inputStream));
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							Log.v("PLAYLISTLINE", "ORIG:" + line);
							if (line.startsWith("#")) {
								// Ԫ���ݣ�����������Ĵ��������ں�����
							} else if (line.length() > 0) {
								// ������ĳ��ȴ���0����ô�ͼ�������һ�������б���Ŀ
								String filePath = "";
								if (line.startsWith("http://")) {
									// ������ԡ�http://����ͷ��ô�Ͱ�����Ϊ��������URL
									filePath = line;
								} else {
									// ���������Ϊһ����Ե�URL��
									// ͬʱ����Ը�M3U�ļ���ԭʼ�����URL������ȥ
									filePath = getRequest.getURI()
											.resolve(line).toString();
								}
								// ������ӵ������б���Ŀ��������ȥ
								PlaylistFile playlistFile = new PlaylistFile(
										filePath);
								// ��Ӳ����ļ��������б�
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

	/** �����б� */
	private void playPlaylistItems() {
		currentPlaylistItemNumber = 0;
		// �ж��Ƿ��в��ŵ��ļ�
		if (playlistItems.size() > 0) {
			// ����ȡ�����Ļ����ļ���·��֮�󣬾Ϳ�����MediaPlayer�ϵ�setDataSource����ʹ������
			String path = ((PlaylistFile) playlistItems
					.get(currentPlaylistItemNumber)).getFilePath();
			try {
				// �����ļ�·��
				mediaPlayer.setDataSource(path);
				// ����������׼��
				mediaPlayer.setOnPreparedListener(this);
				// �첽׼��
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
					&& !NetWorkUtils.isWifiDataEnable(this)) {// true��wifi���ܹۿ�(Ĭ��)
				new AlertDialog.Builder(this)
						.setTitle("��ʾ")
						.setMessage("��ǰ����Ϊwifi״̬�²ſ������㲥")
						.setNegativeButton("�˳�",
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
					.setTitle("��ʾ")
					.setMessage("��ǰ���粻����")
					.setNegativeButton("�˳�",
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

	/** �б��ļ�ʵ���� */
	public class PlaylistFile {
		/** �ļ�·�� */
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
		// ����
		mediaPlayer.reset();
		// �жϲ����б���б���Ŀ����������б������б���Ŀ���¼��ز����б�
		if (playlistItems.size() > currentPlaylistItemNumber + 1) {
			currentPlaylistItemNumber++;
			// ·��
			String path = ((PlaylistFile) playlistItems
					.get(currentPlaylistItemNumber)).getFilePath();
			try {
				// �����ļ�·��
				mediaPlayer.setDataSource(path);
				// �첽׼��
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
			// ����
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
