package com.zhangyan.chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie","zsxq_access_token=4D5D2BFC-9C6F-16F7-1FA3-747BC8F987CB_E0200ED3C51AAC76; zsxqsessionid=56101e7e299605e8cf25d78526e9d45a; abtest_env=product");
        get.addHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse respoonse = httpClient.execute(get);
        if(respoonse.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(respoonse.getEntity());
            System.out.println(res);
        }else{
            System.out.println(respoonse.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/5122858282158824/comments");

        post.addHeader("cookie","zsxq_access_token=4D5D2BFC-9C6F-16F7-1FA3-747BC8F987CB_E0200ED3C51AAC76; zsxqsessionid=56101e7e299605e8cf25d78526e9d45a; abtest_env=product");
        post.addHeader("Content-Type","application/json;charset=utf8");

        String paramJson = "{\"req_data\":{\"text\":\"nono\\n\\n\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";

//                "{req_data: {text: \"nonono\", image_ids: [], mentioned_user_ids: []}}";

//                {req_data: {text: "nono↵↵", image_ids: [], mentioned_user_ids: []}}

//                "{\n" +
//                " \"req_data \": {\n"+
//                " \"text\" : \" nonono \\n\", \n" +
//                " \"image_ids\":[],\n "+
//                " \"mentioned_user_ids \": []\n "+
//                " }\n" +
//                "}";


                //"{req_data: {text: \"nonono↵\", image_ids: [], mentioned_user_ids: []}}";

        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);
        CloseableHttpResponse respoonse = httpClient.execute(post);
        if(respoonse.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(respoonse.getEntity());
            System.out.println(res);
        }else{
            System.out.println(respoonse.getStatusLine().getStatusCode());
        }
    }

}
