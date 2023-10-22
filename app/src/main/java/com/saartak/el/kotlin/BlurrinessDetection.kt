package com.saartak.el.kotlin


/*
 * Copyright (c) 2018. Uber Technologies
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

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.renderscript.*
import android.widget.Toast
import androidx.annotation.ColorInt

object BlurrinessDetection {

    private val CLASSIC_MATRIX = floatArrayOf(
            -1.0f, -1.0f, -1.0f,
            -1.0f, 8.0f, -1.0f,
            -1.0f, -1.0f, -1.0f
    )

    /**
     * This attempts to determine if a given [sourceBitmap] is blurry using a combination of
     * renderscript utilities.
     *
     * Operations go like this:
     * - Soft blur
     * - Greyscale
     * - 3x3 convolution
     *
     * @return if the source bitmap is "blurry"
     */
    fun runDetection(context: Context, sourceBitmap: Bitmap): Boolean {
        val rs = RenderScript.create(context)

        // First apply a soft blur to smoothen out visual artifacts
        val smootherBitmap = Bitmap.createBitmap(sourceBitmap.width,
                sourceBitmap.height,
                sourceBitmap.config
        )
        val blurIntrinsic = ScriptIntrinsicBlur.create(rs, Element.RGBA_8888(rs))
        val source = Allocation.createFromBitmap(rs,
                sourceBitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SHARED
        )
        val blurTargetAllocation = Allocation.createFromBitmap(rs,
                smootherBitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SHARED
        )
        blurIntrinsic.apply {
            setRadius(1f)
            setInput(source)
            forEach(blurTargetAllocation)
        }
        blurTargetAllocation.copyTo(smootherBitmap)

        // Greyscale so we're only dealing with white <--> black pixels, this is so we only need to
        // detect pixel
        // luminosity
        val greyscaleBitmap = Bitmap.createBitmap(sourceBitmap.width,
                sourceBitmap.height,
                sourceBitmap.config
        )
        val smootherInput = Allocation.createFromBitmap(rs,
                smootherBitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SHARED
        )
        val greyscaleTargetAllocation = Allocation.createFromBitmap(rs,
                greyscaleBitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SHARED
        )

        // Inverts and greyscales the image
        val colorIntrinsic = ScriptIntrinsicColorMatrix.create(rs)
        colorIntrinsic.setGreyscale()
        colorIntrinsic.forEach(smootherInput, greyscaleTargetAllocation)
        greyscaleTargetAllocation.copyTo(greyscaleBitmap)

        // Run edge detection algorithm using a laplacian matrix convolution
        // Apply 3x3 convolution to detect edges
        val edgesBitmap = Bitmap.createBitmap(sourceBitmap.width,
                sourceBitmap.height,
                sourceBitmap.config
        )
        val greyscaleInput = Allocation.createFromBitmap(rs,
                greyscaleBitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SHARED
        )
        val edgesTargetAllocation = Allocation.createFromBitmap(rs,
                edgesBitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SHARED
        )

        val convolve = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs))
        convolve.setInput(greyscaleInput)
        convolve.setCoefficients(CLASSIC_MATRIX) // Or use others
        convolve.forEach(edgesTargetAllocation)
        edgesTargetAllocation.copyTo(edgesBitmap)

        @ColorInt val mostLuminousColor = mostLuminousColorFromBitmap(edgesBitmap)
        val colorHex = "#" + Integer.toHexString(mostLuminousColor)
        val isBlurry = mostLuminousColor > Color.parseColor("#CECECE") // Demo threshold
        // Note - in Android, Color.BLACK is -16777216 and Color.WHITE is -1, so range is somewhere in between. Higher is more luminous
        Toast.makeText(context,
                "Most luminous color is $colorHex. Blurry = $isBlurry",
                Toast.LENGTH_SHORT
        ).show()
        return isBlurry
    }

    /**
     * Resolves the most luminous color pixel in a given bitmap.
     *
     * @param bitmap Source bitmap.
     * @return The most luminous color pixel in the `bitmap`
     */
    @ColorInt
    fun mostLuminousColorFromBitmap(bitmap: Bitmap): Int {
        bitmap.setHasAlpha(false)
        val pixels = IntArray(bitmap.height * bitmap.width)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        @ColorInt var mostLuminousColor = Color.BLACK

        for (pixel in pixels) {
            if (pixel > mostLuminousColor) {
                mostLuminousColor = pixel
            }
        }
        return mostLuminousColor
    }

    fun area(l: Int,b: Int):Int{
        return l*b
    }

}