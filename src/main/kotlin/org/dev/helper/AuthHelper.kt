package org.dev.helper

import java.util.Base64

object AuthHelper {
    fun basicAuthHeader(username: String, password: String = ""): String {
        val encoded = Base64.getEncoder()
            .encodeToString("$username:$password".toByteArray(Charsets.UTF_8))
        return "Basic $encoded"
    }
}