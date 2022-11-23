package com.qp.blogapp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertUserRequest {
    private String name;
    private String email;
    private String avatar;
    private String password;
    private List<String> roles;
}
