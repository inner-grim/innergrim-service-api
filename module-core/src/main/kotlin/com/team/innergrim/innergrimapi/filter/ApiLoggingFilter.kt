package com.team.innergrim.innergrimapi.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.ServletOutputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponseWrapper
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.*

/**
 * 요청 응답 로깅 필터
 */
class ApiLoggingFilter : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(ApiLoggingFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {


            if (request.contentType == null
                || !request.contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                // 래퍼로 요청/응답을 감싸서 바디를 캐싱할 수 있게 함
                val wrappedRequest = CachedBodyHttpServletRequest(request)
                val wrappedResponse = CachedBodyHttpServletResponse(response)

                // 요청 바디 읽기
                val requestBody = wrappedRequest.reader.lines().reduce { acc, s -> acc + s }.orElse("")

                MDC.put("threadId", Thread.currentThread().threadId().toString())
                logger.info(
                """
                ::::: [Request] :::::
                [threadId] = ${MDC.get("threadId")}
                [uri] = ${wrappedRequest.requestURI}
                [method] = ${wrappedRequest.method}
                [body] = $requestBody
                """.trimIndent())

                // 필터 체인 계속 실행
                filterChain.doFilter(wrappedRequest, wrappedResponse)

                // 응답 바디 읽기
                val responseBody = wrappedResponse.getCapturedBody()

                // 응답 로깅 직전에 MDC 값 재설정
                MDC.put("threadId", Thread.currentThread().threadId().toString())
                logger.info(
                """
                ::::: [Response] :::::
                [threadId] = ${MDC.get("threadId")}
                [status] = ${wrappedResponse.status}
                [body] = $responseBody
                """.trimIndent())

                // 응답 데이터를 다시 클라이언트로 보내기 위해 출력 스트림에 기록
                response.outputStream.write(responseBody.toByteArray())
                response.outputStream.flush()
            } else {
                filterChain.doFilter(request, response)
                return
            }
        } finally {
            MDC.remove("threadId")
        }
    }
}

class CachedBodyHttpServletRequest(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val cachedBody: ByteArray

    init {
        // Body를 캐시합니다.
        cachedBody = request.inputStream.readBytes()
    }

    override fun getInputStream(): ServletInputStream {
        return CachedBodyServletInputStream(cachedBody)
    }

    override fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(getInputStream()))
    }

    class CachedBodyServletInputStream(private val cachedBody: ByteArray) : ServletInputStream() {
        private val byteArrayInputStream = ByteArrayInputStream(cachedBody)

        override fun read(): Int = byteArrayInputStream.read()

        override fun isFinished(): Boolean = byteArrayInputStream.available() == 0

        override fun isReady(): Boolean = true

        override fun setReadListener(readListener: ReadListener?) {}
    }
}

class CachedBodyHttpServletResponse(response: HttpServletResponse) : HttpServletResponseWrapper(response) {

    private val outputStreamCaptor = ByteArrayOutputStream()
    private val printWriter = PrintWriter(outputStreamCaptor)

    override fun getOutputStream(): ServletOutputStream {
        return CachedBodyServletOutputStream(outputStreamCaptor)
    }

    override fun getWriter(): PrintWriter {
        return printWriter
    }

    fun getCapturedBody(): String {
        printWriter.flush()
        return outputStreamCaptor.toString()
    }

    class CachedBodyServletOutputStream(private val outputStreamCaptor: ByteArrayOutputStream) : ServletOutputStream() {
        override fun write(b: Int) {
            outputStreamCaptor.write(b)
        }

        override fun isReady(): Boolean = true
        override fun setWriteListener(writeListener: jakarta.servlet.WriteListener?) {}
    }
}