package com.xilixir.fortniteapi.OAuth;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.xilixir.fortniteapi.Endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OAuthRequest {
    private static HttpRequestFactory factory = new NetHttpTransport().createRequestFactory();
    private RequestType requestType;
    private String endpoint;
    private List<OAuthField> fields;
    private HttpRequest request;

    public OAuthRequest(RequestType requestType, Endpoint endpoint){
        this.requestType = requestType;
        this.endpoint = endpoint.toString();
        this.fields = new ArrayList<>();
    }

    public OAuthRequest(RequestType requestType, String endpoint){
        this.requestType = requestType;
        this.endpoint = endpoint;
        this.fields = new ArrayList<>();
    }

    public OAuthRequest addField(String name, String value){
        this.fields.add(new OAuthField(name, value));
        return this;
    }

    public HttpHeaders getHeaders(){
        return this.request.getHeaders();
    }

    private String getContent() {
        StringBuilder sb = new StringBuilder();
        if (fields != null) {
            if (fields.size() > 0){
                OAuthField field = fields.get(0);
                sb.append(field.getName());
                sb.append("=");
                sb.append(field.getValue());
            }
            if (fields.size() > 1) {
                for (int i = 1; i < fields.size(); i++) {
                    OAuthField field = fields.get(i);
                    sb.append("&");
                    sb.append(field.getName());
                    sb.append("=");
                    sb.append(field.getValue());
                }
            }
        }
        return sb.toString();
    }

    public OAuthRequest build(){
        GenericUrl url = new GenericUrl(this.endpoint.toString());
        try {
            if (this.requestType == RequestType.POST) {
                ByteArrayContent content = new ByteArrayContent("application/x-www-form-urlencoded", this.getContent().getBytes());
                this.request = factory.buildPostRequest(url, content);
            } else if (this.requestType == RequestType.GET) {
                this.request = factory.buildGetRequest(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String execute(){
        if (request != null){
            try {
                return request.execute().parseAsString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<OAuthField> getFields() {
        return fields;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public static HttpRequestFactory getFactory() {
        return factory;
    }
}
