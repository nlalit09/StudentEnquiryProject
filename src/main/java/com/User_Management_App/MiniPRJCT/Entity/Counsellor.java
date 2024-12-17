package com.User_Management_App.MiniPRJCT.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Counsellor {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer cid;

private String name;
private String email;
private String pwd;
private String phone;

public String  getEmail(){
    return email;
}
public void setEmail(String email){
    this.email = email;
    }
public String getPwd() {
    return pwd;
}
public void setPwd(String pwd) {
    this.pwd = pwd;
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    this.phone = phone;
}
public Integer getCid() {
    return cid;
}
public void setCid(Integer cid) {
    this.cid = cid;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
}
