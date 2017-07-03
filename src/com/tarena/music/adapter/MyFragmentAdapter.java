package com.tarena.music.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mfragments;
	public MyFragmentAdapter(FragmentManager fm,
			Context context,List<Fragment> fragments) {
		super(fm);
		mContext=context;
		mfragments=fragments;
	}
/**当前显示的那个Fragment*/
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return mfragments.get(arg0);
	}
/**有几个Fragment对象*/
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mfragments==null?0:mfragments.size();
	}

}
