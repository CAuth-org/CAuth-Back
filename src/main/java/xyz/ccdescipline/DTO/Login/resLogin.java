package xyz.ccdescipline.DTO.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class resLogin {
    private String token;
    private String role;
}
