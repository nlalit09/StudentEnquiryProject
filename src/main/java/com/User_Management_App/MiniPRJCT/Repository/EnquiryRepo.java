package com.User_Management_App.MiniPRJCT.Repository;

import com.User_Management_App.MiniPRJCT.Entity.StudetEnqiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepo extends JpaRepository<StudetEnqiry, Integer> {
    public List<StudetEnqiry> findByCid(Integer cid);
}
