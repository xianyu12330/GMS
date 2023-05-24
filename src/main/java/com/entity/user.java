package com.entity;

import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class user {
    private long user_id;
    private String name;
    private String password;
    private Date birthday;
    private String email;
    private String phone;
    private int role;


}
