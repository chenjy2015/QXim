package com.example.im

import androidx.emoji.text.EmojiCompat
import com.example.baselib.i.OnBinderItemClickListener
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.ui.binder.BaseViewHolder
import com.example.im.databinding.BinderChatEmojiBinding

class ChatEmojiBInder(var click:OnBinderItemClickListener<String>) : BaseItemViewBinder<String, BinderChatEmojiBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.binder_chat_emoji
    }

    override fun bind(holder: BaseViewHolder<BinderChatEmojiBinding>, item: String) {
        val processed: CharSequence = EmojiCompat.get().process(item)
        holder.viewDataBinding.tvEmoji.text = processed
        holder.viewDataBinding.root.setOnClickListener {
            click.onItemClick(holder.adapterPosition, item)
        }
    }

}