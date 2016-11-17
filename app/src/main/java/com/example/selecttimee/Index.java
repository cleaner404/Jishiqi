package com.example.selecttimee;

import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.jishiqi.SaveRun;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Index extends ActivityGroup {
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	Button onetime, moretime;
	ImageView youbiao;
	Animation am;
	static SeekBar seekBar = null;
	int w;
	float prearg1 = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.index);
		viewPager = (ViewPager) findViewById(R.id.pager);
		youbiao = (ImageView) findViewById(R.id.youbiao);
		onetime = (Button) findViewById(R.id.onetime);
		moretime = (Button) findViewById(R.id.moretime);
		Button btns[] = { onetime, moretime };
		seekBar = new SeekBar(this);
		seekBar.setMax(5);
		InItView();
		viewPager.setAdapter(new myPagerView());

		ScreenInfo s = new ScreenInfo(Index.this);
		w = s.getWidth() / 2;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) youbiao
				.getLayoutParams();
		lp.width = w;
		youbiao.setLayoutParams(lp);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				selsct(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				if (arg1 != 0) {
					float all = w * arg1;
					am = new TranslateAnimation(prearg1, all, 0, 0);
					am.setFillAfter(true);
					am.setDuration(200);
					youbiao.startAnimation(am);
					prearg1 = all;
				}
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		selsct(0);
		for (int i = 0; i < 2; i++) {
			final int a = i;
			btns[a].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					viewPager.setCurrentItem(a);
					selsct(a);
				}
			});
		}
	}

	@Override
	protected void onResume() {
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (progress == 1) {
					getWindow().addFlags(
							WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				}
				if (progress == 2) {
					getWindow().clearFlags(
							WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				}
			}
		});
		super.onResume();
	}

	private void selsct(int arg0) {
		Button btns[] = { onetime, moretime };
		for (int i = 0; i < 2; i++) {
			final int a = i;
			if (a == arg0) {
				// btns[a].setBackgroundResource(R.drawable.selectbtn);
			} else {
				// btns[a].setBackgroundResource(R.drawable.startbutton);
			}
		}
	}
	//转换activity方法
	void InItView() {
		pageViews = new ArrayList<View>();

		View view02 = getLocalActivityManager().startActivity("activity02",
				new Intent(this, com.example.jishiqi.DuociTimer.class))
				.getDecorView();
		View view03 = getLocalActivityManager().startActivity("activity03",
				new Intent(this, com.example.selecttimee.MainActivity.class))
				.getDecorView();
		pageViews.add(view02);
		pageViews.add(view03);
	}

	class myPagerView extends PagerAdapter {
		// 显示数目
		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	
        	
        	
        	
        	
        	if(SaveRun.getisdaojishi() || SaveRun.getisjishi()){
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("正在计时中，确定要退出吗？")
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                	dialog.cancel();
                                }
                            })
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
                                    finish();
                                }
                            }).setNeutralButton("后台",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
                                	Intent i= new Intent(Intent.ACTION_MAIN);
                                	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
                                	i.addCategory(Intent.CATEGORY_HOME);
                                	startActivity(i);
                                }
                            }).create().show();
        	}else{
        		finish();
        	}
            return true;
            
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
