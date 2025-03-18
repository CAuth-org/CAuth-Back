package xyz.ccdescipline.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccdescipline.Constant.RoleEnum;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.annotation.Role;
import xyz.ccdescipline.annotation.Roles;

//@Role(RoleEnum.ADMIN)
@RestController
public class TestRoleController {

    @Role(RoleEnum.ADMIN)
    @GetMapping("/admin")
    public Response testAdmin(){
        return Response.success("admin Role");
    }

    @Roles({@Role(RoleEnum.USER)})
    @PostMapping("/user")
    public Response testUser(){
        return Response.success("user Role");
    }
}
