package testHooks;

import utils.RequestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;


import java.util.HashMap;
import java.util.Map;

public class ServiceHooks {

    public static RequestUtils manager;

    @Before
    public void setupBase () {
        manager = new RequestUtils();
        manager.createPlaywright ();
        ConfigReader.initConfig();
        String baseUrl = ConfigReader.getProperty("api.url");
        Map<String, String> headers = new HashMap<>();
        headers.put ("content-type", "application/json");
        headers.put ("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
        manager.setApiRequestContext (baseUrl, headers);
    }

    @After
    public void tearDown () {
        manager.disposeAPIRequestContext ();
        manager.closePlaywright ();
    }

    public static RequestUtils getManager(){
        return manager;
    }
}