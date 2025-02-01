package com.example.demoskins.data.skin.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SkinResponse(
    val uuid: String?,
    val username: String?,
    @Json(name = "username_history") val usernameHistory: List<UsernameHistory>?,
    val textures: Textures?,
    val legacy: Boolean?,
    val demo: Boolean?,
    @Json(name = "created_at") val createdAt: String?,

    val code: Int?,
    val error: String?,
    val reason: String?
)

@JsonClass(generateAdapter = true)
data class UsernameHistory(
    val username: String,
    @Json(name = "changed_at") val changedAt: String?
)

@JsonClass(generateAdapter = true)
data class Textures(
    val slim: Boolean,
    val custom: Boolean,
    val skin: UrlBase64Data,
    val cape: UrlBase64Data?,
    val raw: ValueSignatureData
)

@JsonClass(generateAdapter = true)
data class UrlBase64Data(
    val url: String?,
    val data: String?
)

@JsonClass(generateAdapter = true)
data class ValueSignatureData(
    val value: String,
    val signature: String
)

/*
SkinResponse(
uuid=93b459be-ce4f-4700-b457-c1aa91b3b687,
username=Etho,
usernameHistory=[UsernameHistory(username=Etho, changedAt=null)],
textures=Textures(
    slim=false,
    custom=true,
    skin=UrlBase64Data(
        url=http://textures.minecraft.net/texture/820fadacbc57ad12f66cf03bb62b73eade59b66fd336795abb311f40ad196ee,
        data=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
    ),
    cape=null,
    raw=ValueSignatureData(
        value=ewogICJ0aW1lc3RhbXAiIDogMTcwNjA0NDk3NTU5OSwKICAicHJvZmlsZUlkIiA6ICI5M2I0NTliZWNlNGY0NzAwYjQ1N2MxYWE5MWIzYjY4NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJFdGhvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzgyMGZhZGFjYmM1N2FkMTJmNjZjZjAzYmI2MmI3M2VhZGU1OWI2NmZkMzM2Nzk1YWJiMzExZjQwYWQxOTZlZSIKICAgIH0KICB9Cn0=,
        signature=bQ4c5t7+5aLXmDEmPK2IhZPYAfPRt6GG4R5v0FQpEzmGTwtozjr/skWQF4xfiYuXhLXg3yTcbQi1Em7alB4eX8RMdbmGf9pspDb1QTjYu8C7TnnJrDlPNOcStSUG683TxCJRFi6UAODH3VSCD5uZJa77PRDtUYFe2zS1Dt3Ay1bjnhpQu0wQ5n0YXPOdiWArxluZ/Nhf7fh0GyIUdO7Gcaq60+GzE9B7hzZVcqckGOKpWYvsGIxvS0N4HWfUzMxc7Ph9rclFiNr0ZS71fzlrQlKDIdyPjlMpsDQJPQb6rqFx5YxnX/nHHHve8Rx0TeT3UdrQ119HUKqGy2zkE7jZ0scFpoGPOvQ2sz/B7YB/6GC1GNF+BM7V2/rKYYjdy999LLUJdzP0JcUsfj/kk+sZgzMHHF1FL3JKfOti9QDWRARz9vR4iwq6zNvkD2JlOTu2dFZrXwvsKz0d6ViFDD+jdH9jVa5F81u+qR+B5b2r6b9q6YdDnE3AvNhPbyyBllD1yM9B+7Ro2SpFUloiJz9WKA9+BChaWhd/861zB2XCAhU/ofMjALfC8dKxWCwUVER4yMykPPgOyqacfxodEe++Gf155NUC4ONle4z85ZpPvcRLkmPhbfiu7x0/YYaj3rLvIPj2FU+Dq/5fuKKN8d1Xou6I6O/aoNVgwaMZRWn6HSk=)
    ),
legacy=null,
demo=null,
createdAt=2010-10-08
)
 */