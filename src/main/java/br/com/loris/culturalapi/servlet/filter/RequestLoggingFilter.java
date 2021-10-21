package br.com.loris.culturalapi.servlet.filter;

import br.com.loris.culturalapi.servlet.http.CachedBodyHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

//@Component
//@Order(1)
public class RequestLoggingFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) {
        logger.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Request
        logger.info("Logging Request  {} : {}", httpRequest.getMethod(), httpRequest.getRequestURI());
        final CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(httpRequest);
        logPostOrPutRequestBody(wrappedRequest);

        // Call next filter in the filter chain
        chain.doFilter(wrappedRequest, response);

        // Response
        logger.info("Logging Response :{}", httpResponse.getContentType());
    }

    @Override
    public void destroy() {
        logger.warn("Destructing filter :{}", this);
    }

    private void logPostOrPutRequestBody(HttpServletRequest httpRequest) throws IOException {
        if(Arrays.asList("POST", "PUT").contains(httpRequest.getMethod())) {
            String characterEncoding = httpRequest.getCharacterEncoding();
            Charset charset = Charset.forName(characterEncoding);
            String bodyInStringFormat = readInputStreamInStringFormat(httpRequest.getInputStream(), charset);
            logger.info("Request body: {}", bodyInStringFormat);
        }
    }

    private String readInputStreamInStringFormat(InputStream stream, Charset charset) throws IOException {
        final int MAX_BODY_SIZE = 1024;
        final StringBuilder bodyStringBuilder = new StringBuilder();
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }

        stream.mark(MAX_BODY_SIZE + 1);
        final byte[] entity = new byte[MAX_BODY_SIZE + 1];
        final int bytesRead = stream.read(entity);

        if (bytesRead != -1) {
            bodyStringBuilder.append(new String(entity, 0, Math.min(bytesRead, MAX_BODY_SIZE), charset));
            if (bytesRead > MAX_BODY_SIZE) {
                bodyStringBuilder.append("...");
            }
        }
        stream.reset();

        return bodyStringBuilder.toString();
    }
}