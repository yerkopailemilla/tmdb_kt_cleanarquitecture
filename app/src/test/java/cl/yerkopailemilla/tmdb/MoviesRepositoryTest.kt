package cl.yerkopailemilla.tmdb

import cl.yerkopailemilla.tmdb.data.dataSource.MovieApi
import cl.yerkopailemilla.tmdb.data.repository.MoviesRepositoryImpl
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

class MoviesRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val newsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)

    private val moviesRepositoryImpl = MoviesRepositoryImpl(newsProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Popular movies response is correct`() {
        mockWebServer.enqueueResponse("popular_movies.json")

        runBlocking {
            val movies = moviesRepositoryImpl.getMovies()
            assertEquals(1, movies.collect(FlowCollector { it.data?.size }))
            assertEquals("Sophie Lewis", "Sophie Lewis")
        }
    }

}

fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}