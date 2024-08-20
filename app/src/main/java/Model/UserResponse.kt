package Model

import Model.User

data class UserResponse(val token: String,
                        val user: User
)
