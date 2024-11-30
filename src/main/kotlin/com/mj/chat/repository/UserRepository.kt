package com.mj.chat.repository

import com.mj.chat.repository.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): Optional<User>

    fun existsByName(name: String): Boolean
}
