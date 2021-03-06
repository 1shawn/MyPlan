package com.andwho.myplan.activity;

import android.app.Activity;
import android.content.Intent;

import com.andwho.myplan.model.Plan;

public class IntentHelper {

	public static final void showPlanEdit(Activity act, String planType) {
		showPlanEdit(act, planType, null);
	}

	public static final void showPlanEdit(Activity act, String planType,
			Plan plan) {
		Intent intent = new Intent(act, PlanEditAct.class);
		intent.putExtra("planType", planType);
		intent.putExtra("plan", plan);
		act.startActivity(intent);
	}

	public static final void showPersonalSetting(Activity act) {
		Intent intent = new Intent(act, PersonalSettingAct.class);
		act.startActivity(intent);
	}

	public static final void showModifyInfo(Activity act, String type) {
		Intent intent = new Intent(act, ModifyInfoAct.class);
		intent.putExtra("type", type);
		act.startActivity(intent);
	}

	public static final void showMore(Activity act) {
		Intent intent = new Intent(act, MoreAct.class);
		act.startActivity(intent);
	}

	public static final void showProblems(Activity act) {
		Intent intent = new Intent(act, ProblemsAct.class);
		act.startActivity(intent);
	}

	public static final void showAboutUs(Activity act) {
		Intent intent = new Intent(act, AboutUsAct.class);
		act.startActivity(intent);
	}

	public static final void share(Activity act) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
		intent.putExtra(Intent.EXTRA_TEXT, "我有计划分享");

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		act.startActivity(Intent.createChooser(intent, "我有计划"));
	}
}
