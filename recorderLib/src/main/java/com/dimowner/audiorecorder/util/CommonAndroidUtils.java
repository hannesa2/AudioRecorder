/*
 * Copyright 2018 Dmitriy Ponomarenko
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

package com.dimowner.audiorecorder.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.dimowner.audiorecorder.ARHandler;

/**
 * Android related utilities methods.
 */
public class CommonAndroidUtils {

    //Prevent object instantiation
    private CommonAndroidUtils() {
    }


    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            ARHandler.Companion.getApplicationHandler().post(runnable);
        } else {
            ARHandler.Companion.getApplicationHandler().postDelayed(runnable, delay);
        }
    }

    /**
     * Convert density independent pixels value (dip) into pixels value (px).
     *
     * @param dp Value needed to convert
     * @return Converted value in pixels.
     */
    public static float dpToPx(float dp) {
        return (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int convertMillsToPx(long mills, float pxPerSec) {
        // 1000 is 1 second evaluated in milliseconds
        return (int) (mills * pxPerSec / 1000);
    }

    public static int convertPxToMills(long px, float pxPerSecond) {
        return (int) (1000 * px / pxPerSecond);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

}
