package UserInterface

import Model.LoginRequest
import Model.UserRequest
import Model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInformation
{
    @POST("/users/signUp")
    fun signUp(@Body userRequest: UserRequest): Call<UserResponse>

    @POST("/users/signIn")
    fun signIn(@Body loginRequest: LoginRequest):Call<UserResponse>
}