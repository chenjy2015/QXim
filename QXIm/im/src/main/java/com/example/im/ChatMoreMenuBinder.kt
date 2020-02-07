package com.example.im

import com.example.baselib.i.OnBinderItemClickListener
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.ui.binder.BaseViewHolder
import com.example.baselib.utils.ImageLoaderUtil
import com.example.im.databinding.BinderChatMoreMenuBinding

class ChatMoreMenuBinder(var click: OnBinderItemClickListener<ChatMoreMenuVO>) :
    BaseItemViewBinder<ChatMoreMenuVO, BinderChatMoreMenuBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.binder_chat_more_menu
    }

    override fun bind(holder: BaseViewHolder<BinderChatMoreMenuBinding>, item: ChatMoreMenuVO) {
        ImageLoaderUtil.load(
            holder.viewDataBinding.root.context,
            item.drawableId,
            holder.viewDataBinding.ivMenu
        )

        holder.viewDataBinding.root.setOnClickListener {
            click.onItemClick(holder.adapterPosition, item)
        }

        holder.viewDataBinding.tvMenu.text = item.menu
    }

}