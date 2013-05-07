package com.closet;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private ViewPager awesomePager;
	private LinearLayout lin1, lin2;
	private Context cxt;
	private AwesomePagerAdapter awesomeAdapter;
	private LayoutInflater mInflater;
	private List<View> mListViews;
	boolean result = true;
	private static final String TAG = "CLOSET_MainActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cxt = this;

		lin1 = (LinearLayout) findViewById(R.id.lin1);
		lin2 = (LinearLayout) findViewById(R.id.lin2);

		awesomeAdapter = new AwesomePagerAdapter();
		awesomePager = (ViewPager) findViewById(R.id.awesomepager);
		awesomePager.setAdapter(awesomeAdapter);

		mListViews = new ArrayList<View>();
		mInflater = getLayoutInflater();
		mListViews.add(mInflater.inflate(R.layout.tab1, null));
		mListViews.add(mInflater.inflate(R.layout.tab2, null));
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}

	private class AwesomePagerAdapter extends PagerAdapter {
		public int getCount() {
			return mListViews.size();
		}

		/**
		 * Create the page for the given position. The adapter is responsible
		 * for adding the view to the container given here, although it only
		 * must ensure this is done by the time it returns from
		 * {@link #finishUpdate()}.
		 * 
		 * @param container
		 *            The containing View in which the page will be shown.
		 * @param position
		 *            The page position to be instantiated.
		 * @return Returns an Object representing the new page. This does not
		 *         need to be a View, but can be some other container of the
		 *         page.
		 */
		public Object instantiateItem(View collection, int position) {

			((ViewPager) collection).addView(mListViews.get(position), 0);
			if (position == 0) {
				ImageView download_btn = (ImageView) collection
						.findViewById(R.id.download_btn);
				download_btn.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						if (result == true) {
							lin1.scrollBy(0, 0);
							lin1.scrollTo(80, 0);
							lin1.setPadding(0, 30, 0, 30);
							lin2.setVisibility(View.VISIBLE);
							result = false;
						} else {
							lin1.setPadding(10, 30, 0, 30);
							lin1.scrollBy(80, 0);
							lin1.scrollTo(0, 0);
							lin2.setVisibility(View.GONE);
							result = true;
						}
					}
				});
			} else {
				ImageView download_btn = (ImageView) collection
						.findViewById(R.id.download_btn);

				download_btn.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						new AlertDialog.Builder(MainActivity.this)
								.setTitle("说明")
								.setMessage("单个页卡内按钮事件测试")
								.setNegativeButton("确定",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int which) {
											}
										}).show();
					}
				});
			}

			return mListViews.get(position);
		}

		/**
		 * Remove a page for the given position. The adapter is responsible for
		 * removing the view from its container, although it only must ensure
		 * this is done by the time it returns from {@link #finishUpdate()}.
		 * 
		 * @param container
		 *            The containing View from which the page will be removed.
		 * @param position
		 *            The page position to be removed.
		 * @param object
		 *            The same object that was returned by
		 *            {@link #instantiateItem(View, int)}.
		 */
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView(mListViews.get(position));
		}

		public boolean isViewFromObject(View view, Object object) {
			return view == (object);
		}

		/**
		 * Called when the a change in the shown pages has been completed. At
		 * this point you must ensure that all of the pages have actually been
		 * added or removed from the container as appropriate.
		 * 
		 * @param container
		 *            The containing View which is displaying this adapter's
		 *            page views.
		 */
		public void finishUpdate(View arg0) {
		}

		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		public Parcelable saveState() {
			return null;
		}

		public void startUpdate(View arg0) {
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}