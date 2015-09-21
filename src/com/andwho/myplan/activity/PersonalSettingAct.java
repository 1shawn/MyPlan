package com.andwho.myplan.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andwho.myplan.R;
import com.andwho.myplan.contentprovider.DbManger;
import com.andwho.myplan.model.Plan;
import com.andwho.myplan.preference.MyPlanPreference;
import com.andwho.myplan.utils.DateUtil;
import com.andwho.myplan.view.SpDatePickerDialog;

/**
 * @author ouyyx 个人设置
 */
public class PersonalSettingAct extends BaseAct implements OnClickListener {

	private static final String TAG = PersonalSettingAct.class.getSimpleName();

	private Activity myselfContext;

	private LinearLayout ll_leftIcon;
	private TextView tv_leftIcon;
	private TextView tv_title;
	private ImageView iv_rightIcon;

	private LinearLayout ll_headicon, ll_nickname, ll_gender, ll_birthday,
			ll_life;
	private ImageView iv_headicon, iv_male, iv_female;
	private TextView tv_nickname, tv_birthday, tv_lifespan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_personal_setting);

		myselfContext = this;

		initHeader();
		findViews();
		setListener();
		init();
	}

	private void initHeader() {
		ll_leftIcon = (LinearLayout) this.findViewById(R.id.ll_leftIcon);
		tv_leftIcon = (TextView) this.findViewById(R.id.tv_leftIcon);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
		iv_rightIcon = (ImageView) this.findViewById(R.id.iv_rightIcon);

		ll_leftIcon.setOnClickListener(this);

		tv_leftIcon.setText("个人设置");

		tv_title.setVisibility(View.INVISIBLE);
		ll_leftIcon.setVisibility(View.VISIBLE);
	}

	private void findViews() {
		ll_headicon = (LinearLayout) this.findViewById(R.id.ll_headicon);
		ll_nickname = (LinearLayout) this.findViewById(R.id.ll_nickname);
		ll_gender = (LinearLayout) this.findViewById(R.id.ll_gender);
		ll_birthday = (LinearLayout) this.findViewById(R.id.ll_birthday);
		ll_life = (LinearLayout) this.findViewById(R.id.ll_life);

		iv_headicon = (ImageView) this.findViewById(R.id.iv_headicon);
		iv_male = (ImageView) this.findViewById(R.id.iv_male);
		iv_female = (ImageView) this.findViewById(R.id.iv_female);

		tv_nickname = (TextView) this.findViewById(R.id.tv_nickname);
		tv_birthday = (TextView) this.findViewById(R.id.tv_birthday);
		tv_lifespan = (TextView) this.findViewById(R.id.tv_lifespan);

	}

	private void setListener() {
		ll_headicon.setOnClickListener(this);
		ll_nickname.setOnClickListener(this);
		ll_gender.setOnClickListener(this);
		ll_birthday.setOnClickListener(this);
		ll_life.setOnClickListener(this);
	}

	private void init() {

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initData();
	}

	public void initData() {
		String nickname = MyPlanPreference.getInstance(myselfContext)
				.getNickname();
		tv_nickname.setText(nickname);
		initGender();
		initBirthday();
		String lifeSpan = MyPlanPreference.getInstance(myselfContext)
				.getLifeSpan();
		tv_lifespan.setText(lifeSpan);
	}

	private void initGender() {
		String gender = MyPlanPreference.getInstance(myselfContext).getGender();
		if ("1".equals(gender)) {
			iv_male.setImageResource(R.drawable.icon_male_press);
			iv_female.setImageResource(R.drawable.icon_female_unpress);
		} else {
			iv_male.setImageResource(R.drawable.icon_male_unpress);
			iv_female.setImageResource(R.drawable.icon_female_press);
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int id = view.getId();
		switch (id) {
		case R.id.ll_leftIcon:
			finish();
			break;
		case R.id.ll_headicon:

			break;
		case R.id.ll_nickname:
			IntentHelper.showModifyInfo(myselfContext, "nickname");
			break;
		case R.id.ll_gender:
			switchGender();
			break;
		case R.id.ll_birthday:
			showDateDialog();
			break;
		case R.id.ll_life:
			IntentHelper.showModifyInfo(myselfContext, "lifespan");
			break;

		default:
			break;
		}
	}

	private void switchGender() {
		String gender = MyPlanPreference.getInstance(myselfContext).getGender();
		if ("1".equals(gender)) {
			MyPlanPreference.getInstance(myselfContext).setGender("0");
			iv_male.setImageResource(R.drawable.icon_male_unpress);
			iv_female.setImageResource(R.drawable.icon_female_press);
		} else {
			MyPlanPreference.getInstance(myselfContext).setGender("1");
			iv_male.setImageResource(R.drawable.icon_male_press);
			iv_female.setImageResource(R.drawable.icon_female_unpress);
		}
	}

	private int startYear, startMonth, startDay;

	private void initBirthday() {
		String birthday = MyPlanPreference.getInstance(myselfContext)
				.getBirthday();
		if (!TextUtils.isEmpty(birthday)) {
			HashMap<String, Integer> startTimeMap = DateUtil
					.getYearMonthDayMap2(birthday);
			startYear = startTimeMap.get("year");
			startMonth = startTimeMap.get("month") + 1;
			startDay = startTimeMap.get("day");

			tv_birthday.setText(startYear + "年"
					+ DateUtil.formatNumber(startMonth) + "月"
					+ DateUtil.formatNumber(startDay) + "日");
		} else {
			startYear = Calendar.getInstance().get(Calendar.YEAR);
			startMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
			startDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

			tv_birthday.setText(startYear + "年"
					+ DateUtil.formatNumber(startMonth) + "月"
					+ DateUtil.formatNumber(startDay) + "日");
		}

	}

	private void showDateDialog() {
		Locale.setDefault(Locale.CHINA);// 设置当前环境为中文

		final SpDatePickerDialog spDateDialog = new SpDatePickerDialog(
				myselfContext, null, startYear, startMonth - 1, startDay);

		spDateDialog.setCancelable(true);
		spDateDialog.setCanceledOnTouchOutside(true);

		spDateDialog.setButton(-2, (CharSequence) "取消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						spDateDialog.dismiss();
					}

				});
		//
		spDateDialog.setButton(-1, (CharSequence) "确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String year = spDateDialog.getYear();
						String month = spDateDialog.getMonth();
						String dayOfmonth = spDateDialog.getDayOfMonth();
						if (TextUtils.isEmpty(year) || TextUtils.isEmpty(month)
								|| TextUtils.isEmpty(dayOfmonth)) {
							return;
						}

						String startDate = spDateDialog.getYear()
								+ "-"
								+ String.valueOf(Integer.parseInt(spDateDialog
										.getMonth()) + 1) + "-"
								+ spDateDialog.getDayOfMonth();

						Calendar calendar = Calendar.getInstance();
						String endDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DAY_OF_MONTH);
						// 如果满足条件
						if (!DateUtil.isDate1Earlier(startDate, endDate)) {
							Toast.makeText(myselfContext, "您的生日必须早于当前日期",
									Toast.LENGTH_SHORT).show();
							return;
						}

						String birthday = spDateDialog.getYear()
								+ "-"
								+ DateUtil.formatNumber(Integer
										.parseInt(spDateDialog.getMonth()) + 1)
								+ "-"
								+ DateUtil.formatNumber(Integer
										.parseInt(spDateDialog.getDayOfMonth()));

						MyPlanPreference.getInstance(myselfContext)
								.setBirthday(birthday);
						initBirthday();
						spDateDialog.dismiss();
					}

				});
		spDateDialog.show();

	}

}
