package com.User_Management_App.MiniPRJCT.Service;

import com.User_Management_App.MiniPRJCT.Binding.DashboardResponse;
import com.User_Management_App.MiniPRJCT.Entity.Counsellor;
import org.springframework.stereotype.Service;

@Service
public interface CounsellorService {

    public String saveCounsellor(Counsellor c);

    public Counsellor loginCheck(String email, String pwd);

    public boolean recoverPwd(String email);

    public DashboardResponse getDashboardInfo(Integer cid);

}
