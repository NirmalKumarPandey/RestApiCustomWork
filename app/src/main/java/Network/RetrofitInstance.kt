package Network

import UserInterface.UserInformation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance
{
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://noteapitesting-5.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: UserInformation = retrofit.create(UserInformation::class.java)
}