/*
 * Copyright 2020 Dmitriy Ponomarenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dimowner.audiorecorder.app.settings;

import android.content.Context;
import android.content.res.Resources;

import com.dimowner.audiorecorder.CommonConstants;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import nativ.hannes.info.recorderlib.R;

/**
 * Created on 26.04.2020.
 * @author Dimowner
 */
public class SettingsMapper {

	private static SettingsMapper singleton;

	public static SettingsMapper getInstance(Context context) {
		if (singleton == null) {
			singleton = new SettingsMapper(context);
		}
		return singleton;
	}

	public SettingsMapper(Context context) {
		loadResources(context);

		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator('.');
		format = new DecimalFormat("#.##", formatSymbols);
	}

	private static DecimalFormat format;

	public final static String SAMPLE_RATE_8000 = "8000";
	public final static String SAMPLE_RATE_16000 = "16000";
	public final static String SAMPLE_RATE_22050 = "22050";
	public final static String SAMPLE_RATE_32000 = "32000";
	public final static String SAMPLE_RATE_44100 = "44100";
	public final static String SAMPLE_RATE_48000 = "48000";

	public final static String BITRATE_12000 = "12000";
//	public final static String BITRATE_24000 = "24000";
	public final static String BITRATE_48000 = "48000";
	public final static String BITRATE_96000 = "96000";
	public final static String BITRATE_128000 = "128000";
	public final static String BITRATE_192000 = "192000";
	public final static String BITRATE_256000 = "256000";

	public final static String CHANNEL_COUNT_STEREO = "stereo";
	public final static String CHANNEL_COUNT_MONO = "mono";

	private Resources resources;
	private String[] formats;
	private String[] formatsKeys;
	private String[] sampleRates;
	private String[] sampleRatesKeys;
	private String[] rates;
	private String[] rateKeys;
	private String[] recChannels;
	private String[] recChannelsKeys;

	public void loadResources(Context context) {
		resources = context.getResources();
		formats = resources.getStringArray(R.array.formats2);
		formatsKeys = new String[] {
				CommonConstants.FORMAT_M4A,
				CommonConstants.FORMAT_WAV,
				CommonConstants.FORMAT_3GP
		};
		sampleRates = resources.getStringArray(R.array.sample_rates2);
		sampleRatesKeys = new String[] {
				SettingsMapper.SAMPLE_RATE_8000,
				SettingsMapper.SAMPLE_RATE_16000,
				SettingsMapper.SAMPLE_RATE_22050,
				SettingsMapper.SAMPLE_RATE_32000,
				SettingsMapper.SAMPLE_RATE_44100,
				SettingsMapper.SAMPLE_RATE_48000,
		};
		rates = resources.getStringArray(R.array.bit_rates2);
		rateKeys = new String[] {
//				SettingsMapper.BITRATE_24000,
				SettingsMapper.BITRATE_48000,
				SettingsMapper.BITRATE_96000,
				SettingsMapper.BITRATE_128000,
				SettingsMapper.BITRATE_192000,
				SettingsMapper.BITRATE_256000,
		};
		recChannels = resources.getStringArray(R.array.channels);
		recChannelsKeys = new String[] {
				SettingsMapper.CHANNEL_COUNT_STEREO,
				SettingsMapper.CHANNEL_COUNT_MONO
		};
	}

	public static String positionToColorKey(int position) {
		switch (position) {
			case 0:
				return CommonConstants.THEME_BLUE_GREY;
			case 1:
				return CommonConstants.THEME_BLACK;
			case 2:
				return CommonConstants.THEME_TEAL;
			case 3:
				return CommonConstants.THEME_BLUE;
			case 4:
				return CommonConstants.THEME_PURPLE;
			case 5:
				return CommonConstants.THEME_PINK;
			case 6:
				return CommonConstants.THEME_ORANGE;
			case 7:
				return CommonConstants.THEME_RED;
			case 8:
				return CommonConstants.THEME_BROWN;
			default:
				return CommonConstants.DEFAULT_THEME_COLOR;
		}
	}

	public static int colorKeyToPosition(String colorKey) {
		switch (colorKey) {
			default:
			case CommonConstants.THEME_BLUE_GREY:
				return 0;
			case CommonConstants.THEME_BLACK:
				return 1;
			case CommonConstants.THEME_TEAL:
				return 2;
			case CommonConstants.THEME_BLUE:
				return 3;
			case CommonConstants.THEME_PURPLE:
				return 4;
			case CommonConstants.THEME_PINK:
				return 5;
			case CommonConstants.THEME_ORANGE:
				return 6;
			case CommonConstants.THEME_RED:
				return 7;
			case CommonConstants.THEME_BROWN:
				return 8;
		}
	}

	public static int namingFormatToPosition(String namingFormat) {
		switch (namingFormat) {
			case CommonConstants.NAME_FORMAT_TIMESTAMP:
				return 2;
			case CommonConstants.NAME_FORMAT_DATE:
				return 1;
			case CommonConstants.NAME_FORMAT_RECORD:
			default:
				return 0;
		}
	}

	public static String positionToNamingFormat(int position) {
		switch (position) {
			case 0:
				return CommonConstants.NAME_FORMAT_RECORD;
			case 1:
				return CommonConstants.NAME_FORMAT_DATE;
			case 2:
				return CommonConstants.NAME_FORMAT_TIMESTAMP;
			default:
				return CommonConstants.DEFAULT_NAME_FORMAT;
		}
	}

	public static int keyToSampleRate(String sampleRateKey) {
		switch (sampleRateKey) {
			case SAMPLE_RATE_8000:
				return CommonConstants.RECORD_SAMPLE_RATE_8000;
			case SAMPLE_RATE_16000:
				return CommonConstants.RECORD_SAMPLE_RATE_16000;
			case SAMPLE_RATE_22050:
				return CommonConstants.RECORD_SAMPLE_RATE_22050;
			case SAMPLE_RATE_32000:
				return CommonConstants.RECORD_SAMPLE_RATE_32000;
			case SAMPLE_RATE_44100:
				return CommonConstants.RECORD_SAMPLE_RATE_44100;
			case SAMPLE_RATE_48000:
				return CommonConstants.RECORD_SAMPLE_RATE_48000;
			default:
				return CommonConstants.DEFAULT_RECORD_SAMPLE_RATE;
		}
	}

	public static String sampleRateToKey(int sampleRate) {
		switch (sampleRate) {
			case CommonConstants.RECORD_SAMPLE_RATE_8000:
				return SAMPLE_RATE_8000;
			case CommonConstants.RECORD_SAMPLE_RATE_16000:
				return SAMPLE_RATE_16000;
			case CommonConstants.RECORD_SAMPLE_RATE_22050:
				return SAMPLE_RATE_22050;
			case CommonConstants.RECORD_SAMPLE_RATE_32000:
				return SAMPLE_RATE_32000;
			case CommonConstants.RECORD_SAMPLE_RATE_44100:
			default:
				return SAMPLE_RATE_44100;
			case CommonConstants.RECORD_SAMPLE_RATE_48000:
				return SAMPLE_RATE_48000;
		}
	}

	public static int keyToBitrate(String bitrateKey) {
		switch (bitrateKey) {
//			case BITRATE_24000:
//				return CommonConstants.RECORD_ENCODING_BITRATE_24000;
			case BITRATE_48000:
				return CommonConstants.RECORD_ENCODING_BITRATE_48000;
			case BITRATE_96000:
				return CommonConstants.RECORD_ENCODING_BITRATE_96000;
			case BITRATE_128000:
				return CommonConstants.RECORD_ENCODING_BITRATE_128000;
			case BITRATE_192000:
				return CommonConstants.RECORD_ENCODING_BITRATE_192000;
			case BITRATE_256000:
				return CommonConstants.RECORD_ENCODING_BITRATE_256000;
			default:
				return CommonConstants.DEFAULT_RECORD_ENCODING_BITRATE;
		}
	}

	public static String bitrateToKey(int bitrate) {
		switch (bitrate) {
			case CommonConstants.RECORD_ENCODING_BITRATE_12000:
				return BITRATE_12000;
//			case CommonConstants.RECORD_ENCODING_BITRATE_24000:
//				return BITRATE_24000;
			case CommonConstants.RECORD_ENCODING_BITRATE_48000:
				return BITRATE_48000;
			case CommonConstants.RECORD_ENCODING_BITRATE_96000:
				return BITRATE_96000;
			case CommonConstants.RECORD_ENCODING_BITRATE_128000:
			default:
				return BITRATE_128000;
			case CommonConstants.RECORD_ENCODING_BITRATE_192000:
				return BITRATE_192000;
			case CommonConstants.RECORD_ENCODING_BITRATE_256000:
				return BITRATE_256000;
		}
	}

	public static int keyToChannelCount(String key) {
		switch (key) {
			case CHANNEL_COUNT_MONO:
				return CommonConstants.RECORD_AUDIO_MONO;
			case CHANNEL_COUNT_STEREO:
				return CommonConstants.RECORD_AUDIO_STEREO;
			default:
				return CommonConstants.DEFAULT_CHANNEL_COUNT;
		}
	}

	public static String channelCountToKey(int count) {
		switch (count) {
			case CommonConstants.RECORD_AUDIO_MONO:
				return CHANNEL_COUNT_MONO;
			case CommonConstants.RECORD_AUDIO_STEREO:
			default:
				return CHANNEL_COUNT_STEREO;
		}
	}

	public String formatBitrate(int bitrate) {
		return resources.getString(R.string.value_kbps, bitrate);
	}

	public String convertSampleRateToString(int sampleRate) {
		String key = sampleRateToKey(sampleRate);
		for (int i = 0; i < sampleRatesKeys.length; i++) {
			if (key.equals(sampleRatesKeys[i])) {
				return sampleRates[i];
			}
		}
		return "";
	}

	public String convertBitratesToString(int bitrate) {
		String key = bitrateToKey(bitrate);
		for (int i = 0; i < rateKeys.length; i++) {
			if (key.equals(rateKeys[i])) {
				return rates[i];
			}
		}
		return "";
	}

	public String convertChannelsToString(int count) {
		String key = channelCountToKey(count);
		for (int i = 0; i < recChannelsKeys.length; i++) {
			if (key.equals(recChannelsKeys[i])) {
				return recChannels[i];
			}
		}
		return "";
	}

	public String convertFormatsToString(String formatKey) {
		for (int i = 0; i < formatsKeys.length; i++) {
			if (formatKey.equals(formatsKeys[i])) {
				return formats[i];
			}
		}
		return "";
	}

	public String formatSize(long size) {
		return resources.getString(R.string.size, format.format((float)size/(1024*1024)));
	}
}
