package com.example.baselib.widget.timeline

import android.R.color
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.PorterDuff.Mode
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.SizeUtils
import com.example.baselib.R


/** 时间轴
 * Created by HP-HP on 05-12-2015.
 */
class TimelineView : View {

    var mContext: Context

    constructor(context: Context) : super(context) {
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mContext = context
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, def: Int) : super(context, attrs, def) {
        this.mContext = context
        init(attrs)
    }

    private var mMarker: Drawable? = null
    private var mStartLine: Drawable? = null
    private var mEndLine: Drawable? = null
    private var mMarkerSize = 0
    private var mLineSize = 0
    private var mLineOrientation = 0
    private var mLinePadding = 0
    private var mMarkerInCenter = false
    private var mBounds: Rect? = null
    @SuppressLint("CustomViewStyleable")
    private fun init(attrs: AttributeSet) {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.timeline_style)
        mMarker = typedArray.getDrawable(R.styleable.timeline_style_marker)
        mStartLine = typedArray.getDrawable(R.styleable.timeline_style_line)
        mEndLine = typedArray.getDrawable(R.styleable.timeline_style_line)
        mMarkerSize = typedArray.getDimensionPixelSize(
            R.styleable.timeline_style_markerSize,
            SizeUtils.dp2px(20f)
        )
        mLineSize = typedArray.getDimensionPixelSize(
            R.styleable.timeline_style_lineSize,
            SizeUtils.dp2px(2f)
        )
        mLineOrientation = typedArray.getInt(R.styleable.timeline_style_lineOrientation, 1)
        mLinePadding = typedArray.getDimensionPixelSize(R.styleable.timeline_style_linePadding, 0)
        mMarkerInCenter = typedArray.getBoolean(R.styleable.timeline_style_markerInCenter, true)
        typedArray.recycle()
        if (mMarker == null) {
            mMarker = mContext.resources.getDrawable(R.drawable.marker)
        }
        if (mStartLine == null && mEndLine == null) {
            mStartLine =
                ColorDrawable(ContextCompat.getColor(mContext, color.darker_gray))
            mEndLine =
                ColorDrawable(ContextCompat.getColor(mContext, color.darker_gray))
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //Width measurements of the width and height and the inside view of child controls


        val w = mMarkerSize + paddingLeft + paddingRight
        val h = mMarkerSize + paddingTop + paddingBottom

        // Width and height to determine the final view through a systematic approach to decision-making


        val widthSize = resolveSizeAndState(w, widthMeasureSpec, 0)
        val heightSize = resolveSizeAndState(h, heightMeasureSpec, 0)
        setMeasuredDimension(widthSize, heightSize)
        initDrawable()
    }

    override fun onSizeChanged(
        w: Int,
        h: Int,
        oldw: Int,
        oldh: Int
    ) {
        super.onSizeChanged(w, h, oldw, oldh)
        // When the view is displayed when the callback
        // Positioning Drawable coordinates, then draw


        initDrawable()
    }

    private fun initDrawable() {
        val pLeft = paddingLeft
        val pRight = paddingRight
        val pTop = paddingTop
        val pBottom = paddingBottom
        val width = width// Width of current custom view

        val height = height
        val cWidth = width - pLeft - pRight// Circle width

        val cHeight = height - pTop - pBottom
        val markSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight))
        if (mMarkerInCenter) { //Marker in center is true


            if (mMarker != null) {
                mMarker!!.setBounds(
                    width / 2 - markSize / 2,
                    height / 2 - markSize / 2,
                    width / 2 + markSize / 2,
                    height / 2 + markSize / 2
                )
                mBounds = mMarker!!.bounds
            }
        } else { //Marker in center is false


            if (mMarker != null) {
                mMarker!!.setBounds(pLeft, pTop, pLeft + markSize, pTop + markSize)
                mBounds = mMarker!!.bounds
            }
        }
        val centerX = mBounds!!.centerX()
        val lineLeft = centerX - (mLineSize shr 1)
        if (mLineOrientation == 0) {

            //Horizontal Line

            if (mStartLine != null) {
                mStartLine!!.setBounds(
                    0,
                    pTop + mBounds!!.height() / 2,
                    mBounds!!.left - mLinePadding,
                    mBounds!!.height() / 2 + pTop + mLineSize
                )
            }
            if (mEndLine != null) {
                mEndLine!!.setBounds(
                    mBounds!!.right + mLinePadding,
                    pTop + mBounds!!.height() / 2,
                    width,
                    mBounds!!.height() / 2 + pTop + mLineSize
                )
            }
        } else {

            //Vertical Line

            if (mStartLine != null) {
                mStartLine!!.setBounds(lineLeft, 0, mLineSize + lineLeft, mBounds!!.top - mLinePadding)
            }
            if (mEndLine != null) {
                mEndLine!!.setBounds(lineLeft, mBounds!!.bottom + mLinePadding, mLineSize + lineLeft, height)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mMarker != null) {
            mMarker!!.draw(canvas!!)
        }
        if (mStartLine != null) {
            mStartLine!!.draw(canvas!!)
        }
        if (mEndLine != null) {
            mEndLine!!.draw(canvas!!)
        }
    }

    /**
     * Sets marker.
     *
     * @param marker will set marker drawable to timeline
     */
    fun setMarker(marker: Drawable?) {
        mMarker = marker
        initDrawable()
    }

    /**
     * Sets marker.
     *
     * @param marker will set marker drawable to timeline
     * @param color  with a color
     */
    fun setMarker(marker: Drawable?, color: Int) {
        mMarker = marker
//        mMarker!!.setColorFilter(color, Mode.SRC)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mMarker!!.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            mMarker!!.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
        initDrawable()
    }

    /**
     * Sets marker color.
     *
     * @param color the color
     */
    fun setMarkerColor(color: Int) {
        mMarker!!.setColorFilter(color, Mode.SRC)
        initDrawable()
    }

    /**
     * Sets start line.
     *
     * @param color    the color
     * @param viewType the view type
     */
    fun setStartLine(color: Int, viewType: Int) {
        initLine(ColorDrawable(color), viewType)
    }

    /**
     * Sets end line.
     *
     * @param color    the color
     * @param viewType the view type
     */
    fun setEndLine(color: Int, viewType: Int) {
        initLine(ColorDrawable(color), viewType)
    }

    /**
     * Sets marker size.
     *
     * @param markerSize the marker size
     */
    fun setMarkerSize(markerSize: Int) {
        mMarkerSize = markerSize
        initDrawable()
    }

    /**
     * Sets line size.
     *
     * @param lineSize the line size
     */
    fun setLineSize(lineSize: Int) {
        mLineSize = lineSize
        initDrawable()
    }

    /**
     * Sets line padding
     * @param padding the line padding
     */
    fun setLinePadding(padding: Int) {
        mLinePadding = padding
        initDrawable()
    }

    private fun setStartLine(startLine: Drawable?) {
        mStartLine = startLine
        initDrawable()
    }

    private fun setEndLine(endLine: Drawable?) {
        mEndLine = endLine
        initDrawable()
    }

    /**
     * Init line.
     *
     * @param viewType the view type
     */
    fun initLine(drawable: Drawable, viewType: Int) {
        when (viewType) {
            LineType.BEGIN -> setStartLine(drawable)
            LineType.END -> setEndLine(drawable)
            LineType.ONLYONE -> {
                setStartLine(drawable)
                setEndLine(drawable)
            }
        }
    }

    companion object {
        /**
         * Gets timeline view type.
         *
         * @param position   the position
         * @param total_size the total size
         * @return the time line view type
         */
        fun getTimeLineViewType(position: Int, total_size: Int): Int {
            return if (total_size == 1) {
                LineType.ONLYONE
            } else if (position == 0) {
                LineType.BEGIN
            } else if (position == total_size - 1) {
                LineType.END
            } else {
                LineType.NORMAL
            }
        }
    }

}