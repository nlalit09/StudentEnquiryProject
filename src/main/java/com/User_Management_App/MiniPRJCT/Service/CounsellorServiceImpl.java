package com.User_Management_App.MiniPRJCT.Service;

import com.User_Management_App.MiniPRJCT.Binding.DashboardResponse;
import com.User_Management_App.MiniPRJCT.Entity.Counsellor;
import com.User_Management_App.MiniPRJCT.Entity.StudetEnqiry;
import com.User_Management_App.MiniPRJCT.Repository.EnquiryRepo;
import com.User_Management_App.MiniPRJCT.Repository.consullerRepo;
import com.User_Management_App.MiniPRJCT.Utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class CounsellorServiceImpl implements CounsellorService {
    @Autowired
    private consullerRepo crepo;
    @Autowired
    private EnquiryRepo enquiryRepo;
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public String saveCounsellor(Counsellor c) {
        Counsellor findemail = crepo.findByEmail(c.getEmail());
        if (findemail != null) {
            return "Email Already Exists";
        }
        Counsellor savedObject = crepo.save(c);
        if (savedObject.getCid() != null) {
            return "Registration success";
        }

        return "Registration Failed";
    }

    @Override
    public Counsellor loginCheck(String email, String pwd) {
        return crepo.findByEmailAndPwd(email, pwd);
    }

    @Override
    public boolean recoverPwd(String email) {
        Counsellor c = crepo.findByEmail(email);
        if (c == null) {
            return false;
        }

        String subject = "Your Password Details - Lalitha Nali";
        String body = "<h1>Your Password is " + c.getPwd() + " </h1>";
        return emailUtils.sendMail(subject, body, email);
    }

    @Override
    public DashboardResponse getDashboardInfo(Integer cid) {
        // Retrieve all student inquiries by CID
        List<StudetEnqiry> allEnq = enquiryRepo.findByCid(cid);

        // Calculate the number of enrolled inquiries
        int enrolledEnqs = (int) allEnq.stream()
                .filter(e -> "Enrolled".equals(e.getEnqStatus())) // Null-safe check for Enrolled
                .count(); // Use count instead of collecting to save memory

        // Create DashboardResponse
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setTotalEnq(allEnq.size());
        dashboardResponse.setEnrolledEnq(enrolledEnqs);
        dashboardResponse.setLostEnq(allEnq.size() - enrolledEnqs);

        return dashboardResponse;
    }
}
