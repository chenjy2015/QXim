package com.example.baselib.widget.reside_menu

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.baselib.R


class ResideMenuItem : LinearLayout {

    /** menu item  icon   */
    private var ivIcon: ImageView? = null
    /** menu item  title  */
    private var tvTitle: TextView? = null


    constructor (context: Context) : super(context) {
        initViews(context)
    }

    constructor (context: Context, icon: Int, title: Int) : super(context) {
        initViews(context)
        ivIcon!!.setImageResource(icon)
        tvTitle!!.setText(title)
    }

    constructor (context: Context, icon: Int, title: String) : super(context) {
        initViews(context)
        ivIcon!!.setImageResource(icon)
        tvTitle!!.text = title
    }

    private fun initViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.residemenu_item, this)
        ivIcon = findViewById(R.id.iv_icon)
        tvTitle = findViewById(R.id.tv_title)
    }

    /**
     * set the icon color;
     *
     * @param icon
     */
    fun setIcon(icon: Int) {
        ivIcon!!.setImageResource(icon)
    }

    /**
     * set the title with resource
     * ;
     * @param title
     */
    fun setTitle(title: Int) {
        tvTitle!!.setText(title)
    }

    /**
     * set the title with string;
     *
     * @param title
     */
    fun setTitle(title: String) {
        tvTitle!!.text = title
    }
}