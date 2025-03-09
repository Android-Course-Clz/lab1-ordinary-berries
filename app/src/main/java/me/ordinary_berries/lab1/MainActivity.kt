package me.ordinary_berries.lab1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.ordinary_berries.lab1.databinding.ActivityMainBinding
import me.ordinary_berries.lab1.util.smallRandom
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var postsRecyclerViewAdapter: PostsRecyclerViewAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        inject()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeProfile(binding)
        setOnClickListeners(binding)
        initializeRecyclerView()
    }

    private fun initializeProfile(binding: ActivityMainBinding) {
        with(binding) {
            profileName.text = DEFAULT_PROFILE_NAME
            profileUsername.text = DEFAULT_USERNAME_TAG
            profileSubscribersCount.text = "${smallRandom()}"
            profileSubscriptionsCount.text = "${smallRandom()}"
            profilePostsCount.text = "${smallRandom()}"
        }

        Glide.with(this)
            .load(DEFAULT_PROFILE_PICTURE)
            .circleCrop()
            .into(binding.profileImage)
    }

    private fun setOnClickListeners(binding: ActivityMainBinding) {
        binding.subscribeButton.setOnClickListener(
            ToastOnClickListener(this, "Subscribe button clicked")
        )

        binding.directMessageButton.setOnClickListener(
            ToastOnClickListener(this, "Message button clicked")
        )
    }

    private fun initializeRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.postsRecyclerView)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = postsRecyclerViewAdapter
    }

    private fun inject(): Unit = DaggerProfileActivityComponent.create().inject(this)
}

private const val DEFAULT_PROFILE_PICTURE = "https://i.imgur.com/ZWPTPmE.png"
private const val DEFAULT_PROFILE_NAME = "Roflan Tip4ik"
private const val DEFAULT_USERNAME_TAG = "@lena_golovach"