package com.dimowner.audiorecorder

import android.content.Context
import android.os.Handler
import com.dimowner.audiorecorder.util.CommonAndroidUtils

class ARHandler {

    companion object {
        var debugBuild: Boolean = false
            private set
        var packageName: String = ""
            private set
        private var screenWidthDp: Float = 0f
        lateinit var applicationHandler: Handler
            private set

        fun init(context: Context, debug: Boolean) {
            applicationHandler = Handler(context.mainLooper)
            screenWidthDp = CommonAndroidUtils.getScreenWidth(context).toFloat()
            packageName = context.packageName
            debugBuild =  debug
        }

        /**
         * Calculate density pixels per second for record duration.
         * Used for visualisation waveform in view.
         * @param durationSec record duration in seconds
         */
        fun getDpPerSecond(durationSec: Float): Float {
            return if (durationSec > CommonConstants.LONG_RECORD_THRESHOLD_SECONDS) {
                CommonConstants.WAVEFORM_WIDTH * screenWidthDp / durationSec
            } else {
                CommonConstants.SHORT_RECORD_DP_PER_SECOND.toFloat()
            }
        }

        fun getLongWaveformSampleCount(): Int {
            return (CommonConstants.WAVEFORM_WIDTH * screenWidthDp).toInt()
        }


    }
}