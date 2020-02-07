package com.example.baselib.widget.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.baselib.R
import com.example.baselib.databinding.LoadingBinding
import com.example.baselib.i.IDialogService
import com.example.baselib.utils.ImageLoaderUtil

/**
 * @author: chenjiayou
 * @createBy: 2019/11/29
 * @descript: 加载loading
 */
class LoadingDialog(var context: Context) : IDialogService<LoadingBinding> {

    override fun getLayoutId(): Int {
        return R.layout.loading
    }

    override fun getDialog(): Dialog {
        return Dialog(context)
    }

    override fun init(title: String?, viewDataBinding: LoadingBinding, dialog: Dialog) {
        /**设置对话框背景透明*/
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)
        viewDataBinding.tvTitle.text = title ?: "loading..."
        ImageLoaderUtil.load(context, R.mipmap.ic_loading, viewDataBinding.ivLoading)
        dialog.setContentView(viewDataBinding.root)
    }

    override fun show(dialog: Dialog, viewDataBinding: LoadingBinding) {
        dialog.show()
    }

}