package fpt.aptech.springlab2.api;

import fpt.aptech.springlab2.entity.TblStudent;
import fpt.aptech.springlab2.repository.TblClassRepository;
import fpt.aptech.springlab2.service.TblStudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
public class TblStudentRestApi {
    final TblStudentService tblStudentService;
    final TblClassRepository tblClassRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(tblStudentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        Optional<TblStudent> tblStudentOptional = tblStudentService.findById(id);
        if(!tblStudentOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Class doesn't not exist");
        }
        return ResponseEntity.ok(tblStudentOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TblStudent tblStudent) {
        TblStudent student = tblStudentService.save(tblStudent);
        if(student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.badRequest().body("Class not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody TblStudent tblStudent) {
        TblStudent tblStudent1 = tblStudentService.update(id, tblStudent);
        if(tblStudent1 == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tblStudentService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if(tblStudentService.delete(id)) {
            return ResponseEntity.ok().body("Delete successfully !");
        }
        return ResponseEntity.badRequest().body("Delete failed !");
    }
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName()  + " "
                    + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

}
