package com.User_Management_App.MiniPRJCT.Controllers;

import com.User_Management_App.MiniPRJCT.Binding.SearchCriteria;
import com.User_Management_App.MiniPRJCT.Entity.StudetEnqiry;
import com.User_Management_App.MiniPRJCT.Service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/enquiry")
    public String enqPage(Model model){
        model.addAttribute("enq",new StudetEnqiry());
        return "addEnquiryView";
    }
    @PostMapping("/enquiry")
    public String addEnquiry(@ModelAttribute("enq") StudetEnqiry enq, StudetEnqiry se, HttpServletRequest req, Model model){
        HttpSession session = req.getSession(false);
        Integer cid = (Integer) session.getAttribute("CID");

        if (cid == null) {
            return "redirect:/logout";
        }

        enq.setCid(cid);

        boolean addEnq = enquiryService.addEnq(enq);
        if (addEnq) {
            model.addAttribute("succMsg", "Enquiry Added");
        } else {
            model.addAttribute("errMsg", "Enquiry Failed To Add");
        }
        return "addEnquiryView";

    }
    @GetMapping("/enquiries")
    public String viewEnquiries(Model model,HttpServletRequest req){
        HttpSession session = req.getSession(false);
        Integer cid = (Integer) session.getAttribute("CID");

        if (cid == null) {
            return "redirect:/logout";
        }

        model.addAttribute("sc", new SearchCriteria());

        List<StudetEnqiry> enquiriesList = enquiryService.getEnquiries(cid, new SearchCriteria());
        model.addAttribute("enquiries", enquiriesList);

        return "displayEnquiriesView";
    }
    @PostMapping("/filter-enquiries")
    public String filterEnquiries(HttpServletRequest req,SearchCriteria sc, Model model){
        HttpSession session = req.getSession(false);
        Integer cid = (Integer) session.getAttribute("CID");


        List<StudetEnqiry> enquiriesList= enquiryService.getEnquiries(cid,sc);
        model.addAttribute("enquiries",enquiriesList);
        return "displayEnquiriesView";
    }
}
