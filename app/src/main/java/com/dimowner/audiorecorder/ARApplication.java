/*
 * Copyright 2018 Dmitriy Ponomarenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dimowner.audiorecorder;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.dimowner.audiorecorder.audio.player.PlayerContract;
import com.dimowner.audiorecorder.data.Prefs;

import info.hannes.timber.DebugTree;
import timber.log.Timber;

public class ARApplication extends Application {

	final static String AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";
	private AudioOutputChangeReceiver audioOutputChangeReceiver;

	private static String PACKAGE_NAME ;

	public static Injector injector;

	public static Injector getInjector() {
		return injector;
	}

	public static String appPackage() {
		return PACKAGE_NAME;
	}

	@Override
	public void onCreate() {
		if (BuildConfig.DEBUG) {
			//Timber initialization
			Timber.plant(new DebugTree());
		}

		super.onCreate();

		ARHandler.Companion.init(getApplicationContext(), BuildConfig.DEBUG);

		PACKAGE_NAME = getApplicationContext().getPackageName();
		injector = new Injector(getApplicationContext());
		Prefs prefs = injector.providePrefs();
		if (!prefs.isMigratedSettings()) {
			prefs.migrateSettings();
		}

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(AUDIO_BECOMING_NOISY);
		audioOutputChangeReceiver = new AudioOutputChangeReceiver();
		registerReceiver(audioOutputChangeReceiver, intentFilter);

		try {
			TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			mTelephonyMgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		} catch (Exception e) {
			Timber.e(e);
		}
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		//This method is never called on real Android devices
		injector.releaseMainPresenter();
		injector.closeTasks();

		unregisterReceiver(audioOutputChangeReceiver);
	}

	private static class AudioOutputChangeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String actionOfIntent = intent.getAction();
			if (actionOfIntent != null && actionOfIntent.equals(AUDIO_BECOMING_NOISY)){
				PlayerContract.Player player = injector.provideAudioPlayer();
				if (player.isPlaying()) {
					player.pause();
				}
			}
		}
	}

	private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			if ((state == TelephonyManager.CALL_STATE_RINGING)
					|| (state == TelephonyManager.CALL_STATE_OFFHOOK)) {
				//Pause playback when incoming call or on hold
				PlayerContract.Player player = injector.provideAudioPlayer();
				if (player.isPlaying()) {
					player.pause();
				}
			}
			super.onCallStateChanged(state, incomingNumber);
		}
	};
}
