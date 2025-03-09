package me.ordinary_berries.lab1

import dagger.Module
import dagger.Provides
import me.ordinary_berries.lab1.service.RandomPostsService

@Module
class ProfileModule {
    @Provides
    fun randomPostsService(): RandomPostsService = RandomPostsService()

    @Provides
    fun postsService(randomPostsService: RandomPostsService): PostsRecyclerViewAdapter =
        PostsRecyclerViewAdapter(randomPostsService)
}