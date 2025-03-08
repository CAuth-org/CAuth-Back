package xyz.ccdescipline.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccdescipline.Util.Response;

@RestController
public class VersionController {
    @Value("${CAuth.version}")
    private String version;

    @GetMapping("/version")
    public Response getVersion(){
        return Response.success(version);
    }
}
