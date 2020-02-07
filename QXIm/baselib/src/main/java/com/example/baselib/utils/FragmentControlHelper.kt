package com.example.baselib.utils

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.example.baselib.R
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.arouter.ARouterManager
import com.example.baselib.bean.Record
import com.example.baselib.enums.MsgFrom
import com.example.baselib.i.Orientation
import kotlin.collections.ArrayList

/**
 * @author: chenjiayou
 * @createBy: 2019/11/8
 * @descript: Fragment调用辅助类
 */
class FragmentControlHelper {
    companion object {
        fun navigation(routePath: String): Fragment {
            return ARouterManager.instance.navigation(routePath) as Fragment
        }

        fun getBrowserFragment(bean: Parcelable): Fragment {
            return ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_BROWSER)
                .withParcelable(ARouterConstants.intent_key.BROWSER_BEAN, bean)
                .navigation() as Fragment
        }

        fun getProjectFragment(projects: List<Parcelable>): Fragment {
            return ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_PROJECT)
                .withParcelableArrayList(ARouterConstants.intent_key.PROJECTS, projects as ArrayList<out Parcelable>)
                .navigation() as Fragment
        }

        fun getSettingFragment(): Fragment {
            return ARouterManager.instance.navigation(ARouterConstants.router_path_fragment.FRAGMENT_PATH_SETTING) as Fragment
        }

        fun getWorkbenchFragment(): Fragment {
            return ARouterManager.instance.navigation(ARouterConstants.router_path_fragment.FRAGMENT_PATH_WORKBENCH) as Fragment
        }

        fun getWorkbenchNextFragment(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_WORKBENCH_NEXT_LEVEL)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getEnterpriseWorkbenchFragment(): Fragment {
            return ARouterManager.instance.navigation(ARouterConstants.router_path_fragment.FRAGMENT_PATH_ENTERPRISE_WORKBENCH) as Fragment
        }

        fun getEnterpriseWorkbenchFragment(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_ENTERPRISE_WORKBENCH)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getAppManagerCenterFragment(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_APP_MANAGER_CENTER)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getAppManagerCenterNextLevelFragment(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_APP_MANAGER_NEXT_LEVEL)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getAppManagerCenterDetailsFragment(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_APP_MANAGER_DETAILS)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getEnterpriseWorkbenchFragmentNativeTop(): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_ENTERPRISE_WORKBENCH_NATIVE_TOP)
                .navigation() as Fragment
        }

        fun getEnterpriseWorkbenchFragmentNativeTop(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_ENTERPRISE_WORKBENCH_NATIVE_TOP)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getEnterpriseWorkbenchNextLevelFragment(project: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_ENTERPRISE_WORKBENCH_NEXT_LEVEL)
                .withParcelable(ARouterConstants.intent_key.PROJECT_KEY, project)
                .navigation() as Fragment
        }

        fun getSystemSubjectSelectFragment(): Fragment {
            return ARouterManager.instance.navigation(ARouterConstants.router_path_fragment.FRAGMENT_PATH_SYSTEM_SUBJECT_SELECT) as Fragment
        }

        fun getDialogFragment(): Fragment {
            return ARouterManager.instance.navigation(ARouterConstants.router_path_fragment.FRAGMENT_PATH_DIALOG) as Fragment
        }

        fun getNewsFragment(): Fragment {
            return ARouterManager.instance.navigation(ARouterConstants.router_path_fragment.FRAGMENT_PATH_NEWS) as Fragment
        }

        fun getDefaultOpenFragment(drawableId: Int, serviceId: Long): Fragment {

            return ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_PATH_DIALOG_OPEN)
                .withInt(ARouterConstants.intent_key.WAIT_OPEN_BACKGROUND, drawableId)
                .withLong(ARouterConstants.intent_key.APP_SERVICE_ID, serviceId)
                .navigation() as Fragment
        }

        /**
         * 消息中心 - 二级列表
         */
        fun getMsgSecondFragment(record: Record, msgFrom: MsgFrom): Fragment {
            return ARouterManager.instance
                .getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_MESSAGE_SECOND)
                .withParcelable(ARouterConstants.intent_key.MSG_VO, record)
                .withParcelable(ARouterConstants.intent_key.MSG_FROM, msgFrom)
                .navigation() as Fragment
        }

        /**
         * 身份验证方式 - 邮箱
         */
        fun getAuthenticationByEmailFragment(operatorType: Int, email: String): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_AUTHENTICATION_BY_EMAIL)
                .withInt(ARouterConstants.intent_key.OPERATOR_TYPE, operatorType)
                .withString(ARouterConstants.intent_key.EMAIL, email)
                .navigation() as Fragment
        }

        /**
         * 身份验证方式 - 手机
         */
        fun getAuthenticationByPhoneFragment(operatorType: Int, mobile: String): Fragment {
            return ARouterManager.instance.getPostcard(ARouterConstants.router_path_fragment.FRAGMENT_AUTHENTICATION_BY_PHONE)
                .withInt(ARouterConstants.intent_key.OPERATOR_TYPE, operatorType)
                .withString(ARouterConstants.intent_key.MOBILE, mobile)
                .navigation() as Fragment
        }

        fun getFragmentByPath(path: String): Fragment {
            return ARouterManager.instance.getPostcard(path).navigation() as Fragment
        }

        fun getFragmentByParcelable(path: String, key: String, parcelable: Parcelable): Fragment {
            return ARouterManager.instance.getPostcard(path).withParcelable(key, parcelable).navigation() as Fragment
        }

        fun getFragmentByParcelableList(path: String, key: String, parcelables: ArrayList<Parcelable>): Fragment {
            return ARouterManager.instance.getPostcard(path).withParcelableArrayList(key, parcelables).navigation() as Fragment
        }

        fun getFragmentWithObject(path: String, key: String, value: String): Fragment {
            return ARouterManager.instance.getPostcard(path).withObject(key, value).navigation() as Fragment
        }

        fun getBySingleStringParam(key: String, value: String, path: String): Fragment {
            return ARouterManager.instance.getPostcard(path).withString(key, value).navigation() as Fragment
        }

        fun getBySingleIntParam(key: String, value: Int, path: String): Fragment {
            val fragment = ARouterManager.instance.getPostcard(path).withInt(key, value).navigation()
            return fragment as Fragment
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