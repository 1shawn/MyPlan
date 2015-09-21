package com.andwho.myplan.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.andwho.myplan.R;
import com.andwho.myplan.fragment.MineFrag;
import com.andwho.myplan.fragment.PlanFrag;

/**
 * 欢迎页 ouyyx
 */
public class IndexAct extends BaseAct implements OnClickListener {

	private static final String TAG = IndexAct.class.getSimpleName();

	private Activity myselfContext;

	private RelativeLayout rl_mine, rl_plan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_index);

		myselfContext = this;

		findViews();
		setListener();
		init();
	}

	private void findViews() {
		rl_mine = (RelativeLayout) this.findViewById(R.id.rl_mine);
		rl_plan = (RelativeLayout) this.findViewById(R.id.rl_plan);
	}

	private void setListener() {
		rl_mine.setOnClickListener(this);
		rl_plan.setOnClickListener(this);
	}

	private void init() {
		switchItemSelected(0);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int id = view.getId();
		switch (id) {
		case R.id.rl_mine:
			switchItemSelected(0);
			break;
		case R.id.rl_plan:
			switchItemSelected(1);
			break;
		default:
			break;
		}
	}

	private void switchItemSelected(int position) {
		Fragment content = null;
		switch (position) {
		case 0:
			rl_mine.setSelected(true);
			rl_plan.setSelected(false);
			content = new MineFrag();
			break;
		case 1:
			rl_mine.setSelected(false);
			rl_plan.setSelected(true);
			content = new PlanFrag();
			break;
		default:
			break;
		}

		if (content != null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.ll_content, content).commit();
		}

	}

}
