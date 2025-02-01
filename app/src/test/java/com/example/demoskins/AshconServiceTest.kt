package com.example.demoskins

import com.example.demoskins.data.AshconService
import com.example.demoskins.data.skin.response.SkinResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.mock.Calls

class AshconServiceTest {

    private lateinit var serviceMock: AshconServiceMock

    @Before
    fun setup() {
        serviceMock = AshconServiceMock()
    }

    @DelicateCoroutinesApi
    @Test
    fun validUsername() {
        runBlocking {
            launch(Dispatchers.IO) {
                val response = serviceMock.getSkin("069a79f4-44e9-4726-a5be-fca90e38aaf5").execute()
                Assert.assertEquals(response.isSuccessful, true)
                println(response.body())
            }
        }
    }

    @DelicateCoroutinesApi
    @Test
    fun invalidUsername() {
        runBlocking {
            launch(Dispatchers.IO) {
                val response = serviceMock.getSkin("").execute()
                Assert.assertEquals(response.isSuccessful, true)
                val skinResponse = response.body()
                Assert.assertNotNull(skinResponse)
                Assert.assertEquals(skinResponse?.code, 404)
                println(response.body())
            }
        }
    }
}

class AshconServiceMock : AshconService {
    override fun getSkin(usernameOrUuid: String): Call<SkinResponse> {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        if (usernameOrUuid == "069a79f4-44e9-4726-a5be-fca90e38aaf5") {
            val raw = """
           {"uuid":"069a79f4-44e9-4726-a5be-fca90e38aaf5","username":"Notch","username_history":[{"username":"Notch"}],"textures":{"custom":true,"slim":false,"skin":{"url":"http://textures.minecraft.net/texture/292009a4925b58f02c77dadc3ecef07ea4c7472f64e0fdc32ce5522489362680","data":"iVBORw0KGgoAAAANSUhEUgAAAEAAAAAgCAYAAACinX6EAAAB1UlEQVR42u2XMVLDQAxF9woUNDQUVFSho2Vo8AGoXFAzVG64AQfw0FDScQJOwgmYHIArGP4Ogh9FXk3WycSONzN/dkeSk9WLpGxCCKFL6ev9NanHm0XvsynfiFQADAbwcHWx9hxsBUABUADMB4Cl2QBIafQAPt+eu5Q8AEOfLwCmAMBqBbGVCpgDgOXPqh9c/voOHsDHy1MM1C/Y4JsNAEsFwCEA0Ia6rle06Rt6F6O+W6PIe/+7y/PudnEWhb3MIVlHASB1NR4KQJIXzRoAKoCTz2yp7QPIgZPbAoMBSKJVVcW1bdsVsU/7m6Yx+/s+nK59EGw4tJ4JMZlwHP0s2JCgN1MG/xmTBHMAQPgQHFQOK3skxmXLh5KYv9gxAOhL0PNzIjpJJI6KYJ9OPlaBA0DPD7Z5/p0DkAQhPpSUt7ZZ8SkAiEF/y4o+x15Wz78VAByDvmfJIa2Jb307VlwfAPi8i5XnzwIgyXEF9AHgZBiGlLvecwz21+GoFwB8ewGw6QzQAHS5WwCkDfYOQN8BLADe7z4PNA+AHoSjApAjDcAaeHoecPw/gJMVTQ6A9WeHv30PgKVJAOAZgVW3EAQ7A+ChiiRTmhQAa4YwBABIxUsM23YN4Bsrw+tBCbm7GwAAAABJRU5ErkJggg=="},"raw":{"value":"ewogICJ0aW1lc3RhbXAiIDogMTcwNTkyMjU5NDQ2MCwKICAicHJvZmlsZUlkIiA6ICIwNjlhNzlmNDQ0ZTk0NzI2YTViZWZjYTkwZTM4YWFmNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOb3RjaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yOTIwMDlhNDkyNWI1OGYwMmM3N2RhZGMzZWNlZjA3ZWE0Yzc0NzJmNjRlMGZkYzMyY2U1NTIyNDg5MzYyNjgwIgogICAgfQogIH0KfQ==","signature":"jaSXn5XEHDF7Ix7t2aw+mYVM3uxLSyUjdztcFDdsCBfrgxxBrhNZczRl7TwFx2qwW+45yJEFaX3Ex2eiHkL9N9qIEmVD6MZoVO65SbzIAMO++BH0rRDasbQAN8vubZUKy+e9cgdExC/gH5ZKsyHI+9tq15nbS5KpNe0Gh4WMjB1DOycXXEpOkrD1cd/visyfmF2pGUCY5jVbGGArtgivREDqQVOm4P1VwG/JqDQi0gfRghOjhozTIK8bAs+tB64u1fkeK3fj7c1sskzMHbawqqe5iWm1EBl8+tCPtM3jsZspZU4iXpfSIezLMIvPoPHrDRGaWOr3hYkh3SZDdVt44+tAEM5Sfj2elfeJUweCqcsa3jlj74rurnfUW25M0zJk4u6fJZZHgMaUFoFD5qEtoTIlAdLs/SRUcj6h7SUj/n27YeIWkgYwpP3N74m/odsbOLNKqACcw+aYfZWDMGaJhdPx7ziRCSp3GistFrS/5Sz6fq8cSTWepQKC9KQRv/Vilj6c8ZOcCV7MysZLSkxf8lInlWfXzZCqujil8lwg9wZ4aav0HmJD3Rjk5YiH7mcqMy6hGkv0uq40OWX0gFUmEiR39OjSxIIgl9gqnVEnSD91tQow0HiRFoTJ83IynrEM8R/MaLHoKsUL5MlWWNsX7wrKPuXKS9BdIyjlH9W6QZY="}},"created_at":"2010-01-10"} 
        """
            val skinResponse = moshi.adapter(SkinResponse::class.java).fromJson(raw)
            return Calls.response(skinResponse)
        }
        var raw = """
            {"code":404,"error":"Not Found","reason":"Unknown route"}
        """.trimIndent()
        val skinResponse = moshi.adapter(SkinResponse::class.java).fromJson(raw)
        return Calls.response(skinResponse)
    }
}