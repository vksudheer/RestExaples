package getRequest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetData {
        @Test(priority = 1)
        public void testResponseCode(){
            Response response =  RestAssured.get("https://us-central1-triassic-app-37e33.cloudfunctions.net/getAllFeatures");
            int code          = response.getStatusCode();
            System.out.println("status code is  "+code );
            Assert.assertEquals(code,400);
            if(code==200){

                System.out.println("http have proper response");
            }
          else {

                System.out.println("http response failed");
            }
    }
    @Test (priority = 3)
       public void testBody() {
        Response response = RestAssured.get("https://us-central1-triassic-app-37e33.cloudfunctions.net/getAllFeatures");
        String data       = response.asString();
        System.out.println(data);
       String json        = response.asString();
       JsonPath jp        = new JsonPath(json);
      //  System.out.println(jp.get("name"));
    }

    @Test(priority = 4)

    public void sampleTest(){
        System.out.println("executing sample api test");
    }

    @Test(priority = 2)
    public void readArrayContents(){
        Response response = RestAssured.get("https://us-central1-triassic-app-37e33.cloudfunctions.net/getAllFeatures");
        String responsedata       = response.asString();
        JSONObject root           = new JSONObject(responsedata );
        System.out.println(root);
        JSONObject data           = root.getJSONObject("data");
        System.out.println(data);
        JSONArray  featureArray   = data.getJSONArray("feature");
        for (int i=0; i < featureArray.length(); i++) {
            System.out.println(featureArray.getJSONObject(i));

            JSONObject firstfeature   = featureArray.getJSONObject(i);
            String     name           = firstfeature.getString("name");
            String       id           = firstfeature.getString("id");
            System.out.println("name =  "+ name);
            System.out.println("id = "+ id);
        }


        }

}
