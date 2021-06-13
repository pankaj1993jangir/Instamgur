package com.pj.instamgur.presentation.view.gallery

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.Nullable


class GalleryProgressView : LinearLayout {
    private val progressBars: MutableList<DefiniteProgressBar> = ArrayList()
    private var storiesCount = -1

    private var current = -1
    private var storiesListener: StoriesListener? = null
    var isComplete = false
    private val mainHandler by lazy { Handler(Looper.getMainLooper()) }

    interface StoriesListener {
        fun onNext()
        fun onComplete()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs)
    constructor(
        context: Context,
        @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)


    private fun bindViews() {
        progressBars.clear()
        removeAllViews()
        for (i in 0 until storiesCount) {
            val p: DefiniteProgressBar = createProgressBar()
            progressBars.add(p)
            addView(p)
            if (i + 1 < storiesCount) {
                addView(createSpace())
            }
        }
    }

    private fun createProgressBar(): DefiniteProgressBar {
        return DefiniteProgressBar(context).apply {
            layoutParams = (LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))
        }
    }

    private fun createSpace(): View {
        return View(context).apply { layoutParams = LayoutParams(5, LayoutParams.WRAP_CONTENT) }
    }


    fun setStoriesCount(storiesCount: Int) {
        this.storiesCount = storiesCount
        bindViews()
    }

    fun setStoriesListener(storiesListener: StoriesListener?) {
        this.storiesListener = storiesListener
    }

    fun setStoryDuration(duration: Long) {
        for (i in progressBars.indices) {
            progressBars[i].duration = duration
            progressBars[i].setCallback(callback(i))
        }
    }


    fun destroy() {
        for (pb in progressBars) {
            pb.clear()
        }
    }

    private fun callback(index: Int): DefiniteProgressBar.Callback {
        return object : DefiniteProgressBar.Callback {
            override fun onStartProgress() {
                current = index
                mainHandler.removeCallbacksAndMessages(null)
                mainHandler.postDelayed(swipeNextRunnable, progressBars[current].duration)
            }
        }
    }

    private fun onProgressFinish() {
        val next = current + 1
        if (next <= progressBars.size - 1) {
            storiesListener?.onNext()
        } else {
            isComplete = true
            storiesListener?.onComplete()
        }
    }

    fun startPrev() {
        if (current > 0) {
            progressBars[current].finishProgress()
            progressBars[current - 1].startProgress()
        }
    }

    fun startNext() {
        if(current >=0) {
            progressBars[current].finishProgress()
        }
        progressBars[current + 1].startProgress()
    }

    private val swipeNextRunnable = Runnable {
        //Log.e("GalleryProgressView", "Update again")
        onProgressFinish()
    }
}