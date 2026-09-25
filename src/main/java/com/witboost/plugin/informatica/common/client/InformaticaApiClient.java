package com.witboost.plugin.informatica.common.client;

import com.witboost.plugin.informatica.common.config.InformaticaApiConfig;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.exceptions.SessionException;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InformaticaApiClient extends BaseClient {

    private static final long TOKEN_TTL_MS = 25L * 60L * 1000L;
    private static final long SESSION_TTL_MS = 25L * 60L * 1000L;

    protected final InformaticaApiConfig config;

    private volatile String sessionId;
    private volatile String orgId;
    private volatile String jwtToken;
    private volatile long accessSessionTime;
    private volatile long accessTokenTime;

    public InformaticaApiClient(InformaticaApiConfig config) {
        this.config = config;
    }

    public String getOrgId() {
        ensureValidSession();
        return orgId;
    }

    public String getSessionId() {
        ensureValidSession();
        return sessionId;
    }

    public String getJwtToken() {
        ensureValidJwtToken();
        return jwtToken;
    }

    private void ensureValidSession() {
        if (sessionId == null || isSessionExpired()) {
            synchronized (this) {
                if (sessionId == null || isSessionExpired()) {
                    refreshSession();
                }
            }
        }
    }

    private boolean isTokenExpired() {
        return accessTokenTime == 0
                || (System.currentTimeMillis() - accessTokenTime) >= TOKEN_TTL_MS;
    }

    private void refreshSession() {
        try {
            log.info("Requesting new Informatica session id");
            String body =
                    String.format(
                            "{\"username\":\"%s\", \"password\":\"%s\"}",
                            config.username(), config.password());

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(new URI(config.getLoginUrl()))
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .timeout(DEFAULT_TIMEOUT)
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "Login", SessionException::new);

            JSONObject responseJson = new JSONObject(response.body());
            sessionId = responseJson.optString("icSessionId");
            orgId = responseJson.optString("orgId");

            if (sessionId == null || orgId == null) {
                log.error("Invalid login response - missing fields. Response: {}", response.body());
                throw new SessionException(
                        String.format(
                                "Invalid login response: missing icSessionId or orgId. Response: %s",
                                response.body()));
            }
            this.accessSessionTime = System.currentTimeMillis();
        } catch (Exception e) {
            throw new SessionException(ExceptionFormatter.format(e));
        }
    }

    private void ensureValidJwtToken() {
        if (jwtToken == null || isJwtTokenExpired()) {
            synchronized (this) {
                if (jwtToken == null || isJwtTokenExpired()) {
                    ensureValidSession();
                    generateJwtToken();
                }
            }
        }
    }

    private boolean isJwtTokenExpired() {
        return accessTokenTime == 0
                || (System.currentTimeMillis() - accessTokenTime) >= TOKEN_TTL_MS;
    }

    private boolean isSessionExpired() {
        return accessSessionTime == 0
                || (System.currentTimeMillis() - accessSessionTime) >= SESSION_TTL_MS;
    }

    // simple helper to guarantee session existence and return it
    private String getSession() {
        ensureValidSession();
        return sessionId;
    }

    private void generateJwtToken() {
        try {
            log.info("Generating jwt token");
            String body =
                    String.format(
                            "{\"username\":\"%s\", \"password\":\"%s\"}",
                            config.username(), config.password());

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(new URI(config.getTokenUrl()))
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("IDS-SESSION-ID", sessionId)
                            .timeout(DEFAULT_TIMEOUT)
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "JWT token generation", SessionException::new);

            JSONObject json = new JSONObject(response.body());
            String token = json.optString("jwt_token", "");
            if (token.isEmpty()) {
                log.error(
                        "Invalid token response - missing jwt_token field. Response: {}",
                        response.body());
                throw new SessionException(
                        String.format(
                                "Invalid token response: missing jwt_token. Response: %s",
                                response.body()));
            }

            this.jwtToken = token;
            this.accessTokenTime = System.currentTimeMillis();
        } catch (Exception e) {
            throw new SessionException(ExceptionFormatter.format(e));
        }
    }
}
