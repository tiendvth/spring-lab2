package fpt.aptech.springlab2.service;

import fpt.aptech.springlab2.entity.TblClass;
import fpt.aptech.springlab2.entity.TblStudent;
import fpt.aptech.springlab2.repository.TblClassRepository;
import fpt.aptech.springlab2.repository.TblStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TblStudentService {
    final TblStudentRepository tblStudentRepository;
    final TblClassRepository tblClassRepository;

    public List<TblStudent> findAll() {
        return tblStudentRepository.findAll();
    }

    public Optional<TblStudent> findById(long id) {
        return tblStudentRepository.findById(id);
    }

    public TblStudent save(TblStudent tblStudent) {
        Optional<TblClass> tblClassOptional = tblClassRepository.findById(tblStudent.getClassId());
        if(tblClassOptional.isPresent()) {
            return tblStudentRepository.save(tblStudent);
        }
        return null;
    }

    public TblStudent update(long id, TblStudent tblStudentUpdate) {
        Optional<TblStudent> tblStudentOption = tblStudentRepository.findById(id);
        if(tblStudentOption.isPresent()) {
            TblStudent tblStudent = tblStudentOption.get();
            tblStudent.setName(tblStudentUpdate.getName());
            tblStudent.setEmail(tblStudentUpdate.getEmail());
            tblStudent.setPhone(tblStudentUpdate.getPhone());
            tblStudent.setGender(tblStudentUpdate.getGender());
            tblStudent.setNote(tblStudentUpdate.getNote());
            tblStudent.setClassId(tblStudentUpdate.getClassId());
            return tblStudentRepository.save(tblStudent);
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<TblStudent> tblStudentOption = tblStudentRepository.findById(id);
        if(tblStudentOption.isPresent()) {
            tblStudentRepository.delete(tblStudentOption.get());
            return true;
        }
        return false;
    }
}