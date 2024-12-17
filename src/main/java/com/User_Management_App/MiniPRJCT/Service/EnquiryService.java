package com.User_Management_App.MiniPRJCT.Service;

import com.User_Management_App.MiniPRJCT.Binding.SearchCriteria;
import com.User_Management_App.MiniPRJCT.Entity.StudetEnqiry;

import java.util.List;

public interface EnquiryService {
    public boolean addEnq(StudetEnqiry se);

    public List<StudetEnqiry> getEnquiries(Integer cid, SearchCriteria s);

}
