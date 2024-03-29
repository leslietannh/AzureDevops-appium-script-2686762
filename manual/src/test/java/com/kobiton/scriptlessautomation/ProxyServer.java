package com.kobiton.scriptlessautomation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.util.SocketUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ProxyServer extends NanoHTTPD {
    public long currentCommandId;
    public long kobitonSessionId;
    public Gson gson = new Gson();

    private final String authString = Config.getBasicAuthString();
    private final int socketTimeoutInSecond = 15 * 60;

    public ProxyServer() throws IOException {
        super(SocketUtils.findAvailableTcpPort());
        start(socketTimeoutInSecond * 1000, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            okhttp3.Response response = makeAppiumRequest(session);
            ResponseStatus status = new ResponseStatus(response.code(), response.message());
            String contentType = response.header(HttpHeaders.CONTENT_TYPE, "application/json");
            String body = response.body().string();

            try {
                if (kobitonSessionId == 0 && "/session".equals(session.getUri()) && session.getMethod() == NanoHTTPD.Method.POST) {
                    JsonObject object = gson.fromJson(body, JsonObject.class);
                    kobitonSessionId = object.get("value").getAsJsonObject().get("kobitonSessionId").getAsLong();
                }
            } catch (Exception ignored) {
            }

            return newFixedLengthResponse(status, contentType, body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.serve(session);
    }

    public okhttp3.Response makeAppiumRequest(IHTTPSession session) throws Exception {
        Method method = session.getMethod();
        HashMap<String, String> requestBodyMap = new HashMap<>();
        session.parseBody(requestBodyMap);

        String requestBodyString = null;
        if (method == NanoHTTPD.Method.POST) {
            requestBodyString = requestBodyMap.get("postData");
        } else if (method == NanoHTTPD.Method.PUT) {
            requestBodyString = requestBodyMap.get("putData");
        } else if (method == NanoHTTPD.Method.PATCH) {
            requestBodyString = requestBodyMap.get("patchData");
        }

        RequestBody requestBody = null;
        if (requestBodyString != null) {
            requestBody = RequestBody.create(MediaType.parse("application/json"), requestBodyString);
        }

        String uri = session.getUri();
        if (uri.startsWith("/wd/hub")) {
            uri = uri.replace("/wd/hub", "");
        }

        URIBuilder uriBuilder = new URIBuilder(Config.APPIUM_SERVER_URL + uri);
        if (Config.DEVICE_SOURCE == Config.DEVICE_SOURCE_ENUMS.KOBITON && currentCommandId > 0) {
            uriBuilder.addParameter("baseCommandId", String.valueOf(currentCommandId));
        }

        Request.Builder requestBuilder = new Request.Builder()
                .header(HttpHeaders.AUTHORIZATION, authString)
                .method(method.toString(), requestBody)
                .url(uriBuilder.build().toURL());

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(socketTimeoutInSecond, TimeUnit.SECONDS)
                .writeTimeout(socketTimeoutInSecond, TimeUnit.SECONDS)
                .readTimeout(socketTimeoutInSecond, TimeUnit.SECONDS)
                .build();

        return httpClient.newCall(requestBuilder.build()).execute();
    }

    public String getServerUrl() {
        return "http://localhost:" + getListeningPort();
    }

    public static class ResponseStatus implements NanoHTTPD.Response.IStatus {
        public int requestStatus;
        public String description;

        public ResponseStatus(int requestStatus, String description) {
            this.requestStatus = requestStatus;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return "" + requestStatus + " " + description;
        }

        @Override
        public int getRequestStatus() {
            return requestStatus;
        }
    }
}
