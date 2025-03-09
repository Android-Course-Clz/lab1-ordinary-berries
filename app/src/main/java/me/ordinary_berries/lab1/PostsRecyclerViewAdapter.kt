package me.ordinary_berries.lab1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.ordinary_berries.lab1.databinding.PostLayoutBinding
import me.ordinary_berries.lab1.service.RandomPostsService
import me.ordinary_berries.lab1.util.intoGeneralTextTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRecyclerViewAdapter @Inject constructor(
    private val randomPostsService: RandomPostsService,
) : RecyclerView.Adapter<PostsRecyclerViewAdapter.PostsViewHolder>() {

    private lateinit var commentButtonOnClickListener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostLayoutBinding.inflate(inflater, parent, false)

        commentButtonOnClickListener =
            ToastOnClickListener(parent.context, "Comment button clicked")

        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postDto = randomPostsService.getPost(position)

        val context = holder.itemView.context

        with(holder.binding) {
            postProfileName.text = postDto.profileName
            postProfileCreatedAt.text = postDto.createdAt.intoGeneralTextTime()
            postBodyText.text = postDto.postBody
            postLikeCount.text = "${postDto.likesCount}"
            postCommentCount.text = "${postDto.commentsCount}"
            postLikeSwitch.isChecked = postDto.isLiked

            Glide.with(context)
                .load(postDto.profileImageUrl)
                .circleCrop()
                .into(postProfileImage)

            postDto.postImageUrl?.let {
                postImage.visibility = View.VISIBLE
                Glide.with(context)
                    .load(postDto.postImageUrl)
                    .into(postImage)
            } ?: apply { postImage.visibility = View.GONE }

            postLikeSwitch.setOnClickListener(
                PostLikeSwitchOnClickListener(postLikeCount)
            )

            postCommentButton.setOnClickListener(
                commentButtonOnClickListener
            )
        }
    }

    private class PostLikeSwitchOnClickListener(
        private val postsCountTextView: TextView,
    ) : View.OnClickListener {
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        override fun onClick(v: View) {
            val switch = v as? Switch
                ?: throw IllegalArgumentException("Expected switch type view, got: ${v::class.simpleName}")

            postsCountTextView.text = postsCountTextView.text.toString().toInt().let {
                if (switch.isChecked) {
                    it + 1
                } else {
                    it - 1
                }
            }.let { "$it" }
        }
    }

    override fun getItemCount(): Int = 20

    class PostsViewHolder(val binding: PostLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}