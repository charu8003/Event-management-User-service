package com.bits.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
@Table(name ="users")
public class User {
   @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Integer mobile;
    private Date insertDate;
    private Date expireDate;
}
