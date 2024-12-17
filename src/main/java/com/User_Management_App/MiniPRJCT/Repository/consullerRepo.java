package com.User_Management_App.MiniPRJCT.Repository;

import com.User_Management_App.MiniPRJCT.Entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface consullerRepo extends JpaRepository<Counsellor,Integer> {
    public Counsellor findByEmail(String email);
    public Counsellor findByEmailAndPwd(String email, String password);
}
