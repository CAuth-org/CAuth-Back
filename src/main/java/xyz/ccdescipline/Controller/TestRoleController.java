package xyz.ccdescipline.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.annotation.Role;
import xyz.ccdescipline.annotation.Roles;

@Role("user")
@RestController
public class TestRoleController {

    @Role("admin")
    @GetMapping("/admin")
    public Response testAdmin(){
        return Response.success("admin Role");
    }

//    @Roles({@Role("user"),@Role("admin")})
    @GetMapping("/user")
    public Response testUser(){
        return Response.success("user Role");
    }
}
