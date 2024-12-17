package com.User_Management_App.MiniPRJCT.Controllers;

import com.User_Management_App.MiniPRJCT.Binding.DashboardResponse;
import com.User_Management_App.MiniPRJCT.Entity.Counsellor;
import com.User_Management_App.MiniPRJCT.Service.CounsellorService;
import com.User_Management_App.MiniPRJCT.Utils.EmailUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CounsellorController {

 @Autowired
private CounsellorService conunsellorsrvc;



    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("counsellor", new Counsellor());
        return "loginView";
    } // display login page
    @PostMapping("/login")
    public String handleLogin(Counsellor c, HttpServletRequest http, Model model){
        Counsellor obj=conunsellorsrvc.loginCheck(c.getEmail(),c.getPwd());
        if(obj==null){
            model.addAttribute("errMsg","Invalid Credentials");
            return "loginView";
        }
        HttpSession session = http.getSession(true);
        session.setAttribute("CID", obj.getCid());

        return "redirect:dashboard";
    }

    @GetMapping("/register")
    public String regPage(Model model){
        model.addAttribute("counsellor", new Counsellor());
        return "registerView";
    } // display signup page

    @PostMapping("/register")
    public String handleRegistration(Counsellor c, Model model){
        String msg=conunsellorsrvc.saveCounsellor(c);
        model.addAttribute("msg",msg);
        return "registerView";
    }
    @GetMapping("/forgotpwd")
    public String recoverPwdPage(Model model){
        return "forgotPasswordView";
    } // display fpwd page

    @PostMapping("/forgotpwd")
    public String handleForgotPwd(@RequestParam String email, Model model){
        boolean status=conunsellorsrvc.recoverPwd(email);
        if (status) {
            model.addAttribute("smsg", "Pwd sent to your email");
        } else {
            model.addAttribute("errmsg", "Invalid Email");
        }
        return "forgotPasswordView";
    }

    @GetMapping("/logout")
    public String handlelogout( Model model){
        return "redirect:/";
    }
    @GetMapping("/dashboard")
    public String buildDashboard(HttpServletRequest req, Model model){
        HttpSession session = req.getSession(false);
        Object obj=session.getAttribute("CID");
        Integer cid=(Integer)obj;
        DashboardResponse dashboard=conunsellorsrvc.getDashboardInfo(cid);
        model.addAttribute("dashboard",dashboard);
        return "dashboard";
    }
    @GetMapping("/recover-pwd")
        public String recoverPwd(@RequestParam String email, Model model ){
        boolean status=conunsellorsrvc.recoverPwd(email);
        if(status){
            model.addAttribute("msg","Success");
        }
        else{
            model.addAttribute("msg","Fail");
        }
        return "forgotPasswordView";
}

}
