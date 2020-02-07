package com.example.baselib.utils

import android.view.View
import com.example.baselib.R
import android.animation.*


class AnimUtils {


    companion object {

        const val cancel = false

        /**
         * 抖动动画
         */

        fun startShakeByProperty(
            view: View,
            scaleSmall: Float,
            scaleLarge: Float,
            shakeDegrees: Float,
            duration: Long
        ): ObjectAnimator {
            //先变小后变大
            val scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(
                View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
            )
            val scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(
                View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
            )

            //先往左再往右
            val rotateValuesHolder = PropertyValuesHolder.ofKeyframe(
                View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegrees),
                Keyframe.ofFloat(0.2f, shakeDegrees),
                Keyframe.ofFloat(0.3f, -shakeDegrees),
                Keyframe.ofFloat(0.4f, shakeDegrees),
                Keyframe.ofFloat(0.5f, -shakeDegrees),
                Keyframe.ofFloat(0.6f, shakeDegrees),
                Keyframe.ofFloat(0.7f, -shakeDegrees),
                Keyframe.ofFloat(0.8f, shakeDegrees),
                Keyframe.ofFloat(0.9f, -shakeDegrees),
                Keyframe.ofFloat(1.0f, 0f)
            )

            val objectAnimator =
                ObjectAnimator.ofPropertyValuesHolder(view, scaleXValuesHolder, scaleYValuesHolder, rotateValuesHolder)
            objectAnimator.duration = duration
            return objectAnimator
        }

        /**
         * 上下抖动
         */
        fun tada(view: View, shakeFactor: Float): ObjectAnimator {

            val pvhScaleX = PropertyValuesHolder.ofKeyframe(
                View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
            )

            val pvhScaleY = PropertyValuesHolder.ofKeyframe(
                View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
            )

            val pvhRotate = PropertyValuesHolder.ofKeyframe(
                View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0f)
            )

            return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).setDuration(1000)
        }

        /**
         * 左右抖动
         */
        fun nope(view: View): ObjectAnimator {
            val delta = view.resources.getDimensionPixelOffset(R.dimen.spacing_medium)

            val pvhTranslateX = PropertyValuesHolder.ofKeyframe(
                View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.10f, (-delta).toFloat()),
                Keyframe.ofFloat(.26f, delta.toFloat()),
                Keyframe.ofFloat(.42f, (-delta).toFloat()),
                Keyframe.ofFloat(.58f, delta.toFloat()),
                Keyframe.ofFloat(.74f, (-delta).toFloat()),
                Keyframe.ofFloat(.90f, delta.toFloat()),
                Keyframe.ofFloat(1f, 0f)
            )

            return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).setDuration(500)
        }
    }

    /**
     * @author: chenjiayou
     * @createBy: 2019/11/18
     * @descript: ObjectAnimator没有恢复原位方法 外部调用cancel方法后
     * view停留位置会在最后一次动画结束位置 不会恢复到初始位置
     * 这里自定义实现适配器 外部调用cancel时这里就可以将view恢复到初始位置
     */
    open class CancelAnimatorListenerAdapter : AnimatorListenerAdapter {
        var isCanceled = false
        var view: View

        constructor(isCanceled: Boolean, view: View) {
            this.isCanceled = isCanceled
            this.view = view
        }

        override fun onAnimationCancel(animation: Animator) {
            super.onAnimationCancel(animation)
            isCanceled = true
        }

        override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
            super.onAnimationEnd(animation, isReverse)
            if (isCanceled) {
                view.translationX = 0.0f
                view.alpha = 1.0f
                view.scaleX = 1.0f
                view.scaleY = 1.0f
//                startMatchTipsAnimation();
            }
        }
    }

}