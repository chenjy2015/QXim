package com.example.im

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.emoji.text.EmojiCompat
import androidx.emoji.widget.EmojiTextView
import com.blankj.utilcode.util.StringUtils
import com.example.baselib.ui.binder.BaseItemViewBinder
import com.example.baselib.ui.binder.BaseViewHolder
import com.example.baselib.utils.EncodeUtil
import com.example.baselib.utils.ImageLoaderUtil
import com.example.im.databinding.BinderChatTextBinding
import java.util.*

class ChatTextBinder : BaseItemViewBinder<ChatVO, BinderChatTextBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.binder_chat_text
    }

    override fun bind(holder: BaseViewHolder<BinderChatTextBinding>, item: ChatVO) {
        var id = item.userId
        var headUrl = ""
        val context: Context = holder.viewDataBinding.root.context
        val head: ImageView?
        val content: EmojiTextView?
        val random = Random().nextInt(10)
        val orientation: Orientation
        if (random % 2 == 0) {
            orientation = Orientation.LEFT
            head = holder.viewDataBinding.left.ivHead
            content = holder.viewDataBinding.left.tvContent
            headUrl = ChatConstant.url
        } else {
            orientation = Orientation.RIGHT
            head = holder.viewDataBinding.right.ivHead
            content = holder.viewDataBinding.right.tvContent
            headUrl = ChatConstant.gif
        }
        toggle(holder, orientation)
        load(context, headUrl, head)
        loadContent(item.content, content)

    }

    private fun toggle(holder: BaseViewHolder<BinderChatTextBinding>, orientation: Orientation) {
        holder.viewDataBinding.left.parent.visibility = View.GONE
        holder.viewDataBinding.right.parent.visibility = View.GONE
        when (orientation) {
            Orientation.LEFT -> holder.viewDataBinding.left.parent.visibility = View.VISIBLE
            Orientation.RIGHT -> holder.viewDataBinding.right.parent.visibility = View.VISIBLE
        }
    }

    private fun load(context: Context, headUrl: String, ivHead: ImageView) {
        ImageLoaderUtil.load(context, headUrl, ivHead)
    }

    private fun loadContent(content: CharSequence, tvContext: EmojiTextView) {
//        tvContext.text = StringUtils.null2Length0(content)
//        tvContext.text = EmojiCompat.get().process(content)
//        val string  = "包含\uD83D\uDE00Emoji\uD83D\uDE01的字符串\uD83D\uDE02"
//        val processed = EmojiCompat.get().process("neutral face \uD83D\uDE10")
        val processed: CharSequence = EmojiCompat.get().process(content)
        tvContext.text = processed

    }

}