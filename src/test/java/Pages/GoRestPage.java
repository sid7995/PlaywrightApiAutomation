package Pages;

import DTO.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import testHooks.ServiceHooks;
import utils.ConfigReader;

import java.io.File;
import java.nio.file.Files;

public class GoRestPage {

    APIResponse apiResponse;
    String endpoint;

    String baseUrl = ConfigReader.getProperty("api.url");
    Users users = Users.builder()
            .name("dev")
            .email("dev@gmail.com")
            .gender("male")
            .status("active").build();

    public void createGetEndpoint(String endpoint){
        this.endpoint = baseUrl+endpoint;
    }
    public void sendGetCall(){

        apiResponse = ServiceHooks.getManager().getRequest(endpoint);
    }

    public void verifyResponse(){
        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assertions.assertThat(statusCode).isEqualTo(200);

        String statusResText = apiResponse.statusText();
        System.out.println(statusResText);
        System.out.println("----print api response with plain text----");
        System.out.println(apiResponse.text());
    }

    public void postRequest(){

        apiResponse = ServiceHooks.getManager().postRequest(endpoint,RequestOptions.create().setData(users));
    }

    public void verifyPostResponse() throws JsonProcessingException {
        Assert.assertEquals(201,apiResponse.status());
        Assert.assertEquals(apiResponse.statusText(), "Created");

        String responseText = apiResponse.text();
        System.out.println(responseText);

        //convert response text/json to POJO -- desrialization
        ObjectMapper objectMapper = new ObjectMapper();
        Users actUser = objectMapper.readValue(responseText, Users.class);
        System.out.println("actual user from the response---->");
        System.out.println(actUser);



        Assert.assertEquals(actUser.getName(), users.getName());
        Assert.assertEquals(actUser.getEmail(), users.getEmail());
        Assert.assertEquals(actUser.getStatus(), users.getStatus());
        Assert.assertEquals(actUser.getGender(), users.getGender());
        Assert.assertNotNull(actUser.getId());
    }
}
