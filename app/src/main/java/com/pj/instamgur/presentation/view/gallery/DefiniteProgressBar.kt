package com.pj.instamgur.presentation.view.gallery

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.Transformation
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.Nullable
import com.pj.instamgur.R

private const val DEFAULT_PROGRESS_DURATION = 2000

internal class DefiniteProgressBar(
    context: Context,
    @Nullable attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int
) :
    FrameLayout(context, attrs, defStyleAttr) {
    private var animation: DefiniteScaleAnimation? = null
    var duration = DEFAULT_PROGRESS_DURATION.toLong()
    private var callback: Callback? = null
    private var frontProgressView: View
    private var maxProgressView: View


    init {
        LayoutInflater.from(context).inflate(R.layout.view_definite_progress, this)
        frontProgressView = findViewById(R.id.view_front_progress)
        maxProgressView = findViewById(R.id.view_max_progress)
    }

    internal interface Callback {
        fun onStartProgress()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, @Nullable attrs: AttributeSet?) : this(context, attrs, 0)

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun finishProgress() {
        maxProgressView.setBackgroundResource(R.color.white)
        maxProgressView.visibility = VISIBLE
        animation?.let {
            it.setAnimationListener(null)
            it.cancel()
        }
    }

    fun startProgress() {
        maxProgressView.visibility = View.GONE
        animation =
            DefiniteScaleAnimation(
                0f,
                1f,
                1f,
                1f,
                Animation.ABSOLUTE,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f
            )
        animation?.let {
            it.duration = duration
            it.interpolator = LinearInterpolator()
            it.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    frontProgressView.visibility = View.VISIBLE
                    callback?.onStartProgress()
                }

                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {}
            })
            it.fillAfter = true
            frontProgressView.startAnimation(animation)
        }
    }

    fun clear() {
        animation?.setAnimationListener(null)
        animation?.cancel()
        animation = null
    }

    private class DefiniteScaleAnimation constructor(
        fromX: Float, toX: Float, fromY: Float,
        toY: Float, pivotXType: Int, pivotXValue: Float, pivotYType: Int,
        pivotYValue: Float
    ) :
        ScaleAnimation(
            fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType,
            pivotYValue
        ) {
        private var mElapsedAtPause: Long = 0
        private var mPaused = false
        override fun getTransformation(
            currentTime: Long,
            outTransformation: Transformation?,
            scale: Float
        ): Boolean {
            if (mPaused && mElapsedAtPause == 0L) {
                mElapsedAtPause = currentTime - startTime
            }
            if (mPaused) {
                startTime = currentTime - mElapsedAtPause
            }
            return super.getTransformation(currentTime, outTransformation, scale)
        }
    }
}