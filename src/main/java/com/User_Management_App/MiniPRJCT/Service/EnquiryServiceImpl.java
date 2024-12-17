package com.User_Management_App.MiniPRJCT.Service;

import com.User_Management_App.MiniPRJCT.Binding.SearchCriteria;
import com.User_Management_App.MiniPRJCT.Entity.StudetEnqiry;
import com.User_Management_App.MiniPRJCT.Repository.EnquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryServiceImpl implements EnquiryService {
    @Autowired
    private EnquiryRepo enqrepo;
    @Override
    public boolean addEnq(StudetEnqiry se) {
        StudetEnqiry savedEnq = enqrepo.save(se);

        return savedEnq.getEnqId() != null;

    }

    @Override
    public List<StudetEnqiry> getEnquiries(Integer cid, SearchCriteria sc) {
        StudetEnqiry enq=new StudetEnqiry();
        enq.setCid(cid);
        if(sc.getClassMode()!=null && sc.getClassMode().equals("")){
            enq.setClassMode(sc.getClassMode());
        }
        if(sc.getCourseName()!=null && !sc.getCourseName().equals("")) {
            enq.setCourseName(sc.getCourseName());
        }

        if(sc.getEnqStatus()!=null && !sc.getEnqStatus().equals("")) {
            enq.setEnqStatus(sc.getEnqStatus());
        }
        Example<StudetEnqiry> of = Example.of(enq);

        List<StudetEnqiry> enquiries = enqrepo.findAll(of);

        return enquiries;
    }
}
