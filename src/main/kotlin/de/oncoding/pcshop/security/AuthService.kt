package de.oncoding.pcshop.security

import org.springframework.stereotype.Service

@Service
class AuthService {
    fun isProductAdmin(): Boolean {
        return false
    }

}
