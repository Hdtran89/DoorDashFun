package com.example.doordashfun.network

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.Okio
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets


@RunWith(JUnit4::class)
open class ApiAbstract<T> {

    private var mockWebServer: MockWebServer? = null

    @Before
    @Throws(IOException::class)
    fun mockServer() {
        mockWebServer = MockWebServer()
        mockWebServer!!.start()
    }

    @After
    @Throws(IOException::class)
    fun stopServer() {
        mockWebServer!!.shutdown()
    }

    @Throws(IOException::class)
    fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }


    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = ApiAbstract::class.java.classLoader!!.getResourceAsStream(
            String.format(
                "api-response/%s",
                fileName
            )
        )
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer!!.enqueue(
            mockResponse.setBody(
                (source as BufferedSource).readString(
                    StandardCharsets.UTF_8
                )
            )
        )
    }

    fun createService(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(mockWebServer!!.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(clazz)
    }

    @Throws(InterruptedException::class)
    fun assertRequestPath(path: String) {
        val request = mockWebServer!!.takeRequest()
        MatcherAssert.assertThat(request.getPath(), CoreMatchers.`is`(path))
    }
}
