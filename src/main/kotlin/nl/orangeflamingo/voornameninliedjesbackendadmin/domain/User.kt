package nl.orangeflamingo.voornameninliedjesbackendadmin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.annotation.Generated

@Document(collection = "Users")
data class User(
        @Id
        @Generated
        val id: String? = null,

        @Field("username")
        var username: String,

        @Field("password")
        var password: String,

        @Field("roles")
        val roles: MutableSet<String>
)