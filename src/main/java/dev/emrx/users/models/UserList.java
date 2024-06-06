package dev.emrx.users.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserList {

    private String nickname;
    private String username;
    private String password;

}
