package com.example.baselib.utils

import android.content.Context
import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import com.example.baselib.R
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.arouter.ARouterManager
import com.example.baselib.constants.Constant
import com.example.baselib.i.Orientation

class ActivityControlHelper {

    companion object {


        fun intentMainActivity() {
            ARouterManager.instance.navigation(ARouterConstants.router_path_activity.ACTIVITY_PATH_SPLASH)
        }

        fun intentLoginActivity(context: Context, orientation: Orientation) {
            val postcard = ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_activity.ACTIVITY_PATH_LOGIN)
                .withInt(Constant.OVERRIDE_TRANSITION_ORIENTATION, orientation.value)
            withTransition(postcard, Orientation.BOTTOM).navigation(context)
        }

        fun intentHomeActivity() {
            ARouterManager.instance.navigation(ARouterConstants.router_path_activity.ACTIVITY_PATH_HOME)
        }

        fun intentBrowserActivity(context: Context, url: String, orientation: Orientation) {
            val postcard = ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_activity.ACTIVITY_PATH_BROWSER)
                .withString(ARouterConstants.intent_key.BROWSER_URL_KEY, url)
            withTransition(postcard, orientation).navigation(context)
        }

        fun intentSystemSubjectSelectActivity(context: Context, platformCode: String, orientation: Orientation) {
            val postcard = ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_activity.ACTIVITY_PATH_SYSTEM_SUBJECT_SELECT)
                .withString(ARouterConstants.intent_key.ORGANIZATION_CODE, platformCode)
            withTransition(postcard, orientation).navigation(context)
        }

        fun intentOrganizationSelectActivity(context: Context, orientation: Orientation) {
            val postcard = ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_activity.ACTIVITY_PATH_ORGANIZATION_SELECT)
                .withInt(Constant.OVERRIDE_TRANSITION_ORIENTATION, orientation.value)
            withTransition(postcard, orientation).navigation(context)
        }

        fun intentRegisterAgreementActivity(context: Context, type: Int, url: String) {
            val postcard = ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_activity.ACTIVITY_REGISTER_AGREEMENT)
                .withInt(ARouterConstants.intent_key.REGISTER_AGREEMENT_TYPE, type)
                .withString(ARouterConstants.intent_key.BROWSER_URL_KEY, url)
            withTransition(postcard, Orientation.RIGHT).navigation(context)
        }

        fun intentActivityByPath(path: String, orientation: Orientation) {
            withTransition(ARouterManager.instance.getPostcard(path), orientation).navigation()
        }

        fun intentActivityByParcelable(path: String, key: String, parcelable: Parcelable, orientation: Orientation) {
            withTransition(ARouterManager.instance.getPostcard(path), orientation)
                .withParcelable(key, parcelable)
                .navigation()
        }

        fun intentSetNewPasswordActivity(sysMainBodyId: String, verifyCode: String, orientation: Orientation) {
            withTransition(
                ARouterManager.instance.getPostcard(ARouterConstants.router_path_activity.ACTIVITY_SET_PASSWORD),
                orientation
            )
                .withString(ARouterConstants.intent_key.SYS_MAIN_BODY_ID, sysMainBodyId)
                .withString(ARouterConstants.intent_key.VERIFY_CODE, verifyCode)
                .navigation()
        }

        fun intentActivityWithObject(path: String, key: String, any: Any, orientation: Orientation) {
            withTransition(ARouterManager.instance.getPostcard(path), orientation)
                .withObject(key, any)
                .navigation()
        }


        fun intentSingleStringParam(key: String, value: String, path: String, orientation: Orientation) {
            val postcard =
                ARouterManager.instance.getPostcard(path)
                    .withString(key, value)
            withTransition(postcard, orientation).navigation()
        }

        private fun withTransition(postcard: Postcard, orientation: Orientation): Postcard {
            return when (orientation) {
                Orientation.RIGHT -> postcard.withTransition(R.anim.slide_right_in, R.anim.slide_right_out)
                Orientation.LEFT -> postcard.withTransition(R.anim.slide_left_in, R.anim.slide_left_out)
                else -> postcard.withTransition(R.anim.slide_bottom_in, R.anim.slide_bottom_out)
            }
        }
    }
}