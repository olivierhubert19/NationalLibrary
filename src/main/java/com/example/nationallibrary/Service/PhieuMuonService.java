package com.example.nationallibrary.Service;

import com.example.nationallibrary.Componet.DateConverter;
import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Repository.PhieuMuonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PhieuMuonService {
    @Autowired
    private PhieuMuonRepo phieuMuonRepo;
    @Autowired
    private DateConverter dateConverter;

    public void Delete(PhieuMuon pm){
        phieuMuonRepo.delete(pm);
    }

    public void Save(PhieuMuon pm){
        phieuMuonRepo.save(pm);
    }
    public List<PhieuMuon> getAll(){
        List<PhieuMuon> list = phieuMuonRepo.findAll();
        for(PhieuMuon pm : list){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pm.getNgayMuon());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            pm.setNgayMuon(calendar.getTime());
        }
        return phieuMuonRepo.findAll();
    }

    public PhieuMuon getPhieuMuonById(int id){
        return phieuMuonRepo.getPhieuMuonById(id);
    }

}
