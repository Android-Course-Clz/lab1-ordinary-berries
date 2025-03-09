package me.ordinary_berries.lab1.service

import me.ordinary_berries.lab1.dto.PostDto
import me.ordinary_berries.lab1.util.smallRandom
import java.time.Instant
import javax.inject.Singleton
import kotlin.math.absoluteValue
import kotlin.random.Random

@Singleton
class RandomPostsService {
    fun getPost(position: Int): PostDto {
        return generateSinglePost()
    }

    private fun generateSinglePost(): PostDto {
        return PostDto(
            profileName = profileNames.pickRandom(),
            createdAt = Instant.ofEpochSecond(Random.nextInt().toLong().absoluteValue),
            postBody = posts.pickRandom(),
            likesCount = smallRandom(),
            commentsCount = smallRandom(),
            profileImageUrl = profileImages.pickRandom(),
            postImageUrl = if (Random.nextBoolean()) null else postImages.pickRandom(),
            isLiked = Random.nextBoolean(),
        )
    }

    private fun <T> List<T>.pickRandom(): T =
        get(Random.nextInt(0, size))

    companion object {
        private val profileNames = listOf(
            "Melany Cross",
            "Maximus Kramer",
            "Kieran Livingston",
            "Ty Mccann",
            "Elliot Henderson",
            "Jacqueline Knox",
            "Sloane Farrell",
            "Shyann Tate",
            "Sebastian Holden",
            "Kaitlin Greer",
            "Marcel Hays",
            "Justus York",
        )

        private val posts = listOf(
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt.",
            "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "Nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore. Nisi ut aliquip ex ea commodo consequat.",
            "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        )

        private val postImages = listOf(
            "https://i.pinimg.com/736x/f4/99/74/f49974497bbcf0930f775f52512c08e1.jpg",
            "https://i.pinimg.com/736x/d0/76/cc/d076ccdc9c6aba5447f5181180389031.jpg",
            "https://i.pinimg.com/736x/0c/04/a6/0c04a651ba34b8d44b32434f54244f15.jpg",
            "https://i.pinimg.com/736x/88/4a/0f/884a0f34391eaef8bfa5d75ef6fa5a08.jpg",
        )

        private val profileImages = listOf(
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_2369151434.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_2890368131.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_3156504080.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_3244887018.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_3259370999.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_3377864339.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_3555474750.jpg",
            "https://github.com/acani/fake_profile_pictures/raw/master/lib/fake_profile_pictures/photos/square_3679345595.jpg",
        )
    }
}