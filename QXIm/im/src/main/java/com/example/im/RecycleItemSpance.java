package com.example.im;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @route:
 * @descript: 自定义item 实现边距对齐
 * @create: chenjiayou
 * create at 2020-02-07 19:22
 */

public class RecycleItemSpance extends RecyclerView.ItemDecoration {

    public static final String TOP_DECORATION = "top_decoration";
    public static final String BOTTOM_DECORATION = "bottom_decoration";
    public static final String LEFT_DECORATION = "left_decoration";
    public static final String RIGHT_DECORATION = "right_decoration";

    int top = 0;
    int bottom = 0;
    int left = 0;
    int right = 0;
    int spcount = 4;

    public RecycleItemSpance(int left, int top, int right, int bottom, int spcount) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.spcount = spcount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        int total = parent.getChildCount();
        int index = parent.getChildAdapterPosition(view);


        //行首或中间位置的item 只要top和left边距
        outRect.top = top;
        outRect.left = left;
        outRect.bottom = 0;
        outRect.right = 0;

        //行末 加上right边距
        if (total % (index + 1) == 0) {
            outRect.right = right;
        }

        //最后一行 需要加上bottom边距
        if (index / spcount == spcount - 1) {
            outRect.bottom = bottom;
        }
    }


}
