package fpt.aptech.springlab2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblStudent")
public class TblStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 5)
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;
    private String gender;
    private String note;
    @NotNull
    private long classId;
    @ManyToOne
    @JoinColumn(name = "classId", insertable = false, updatable = false)
    private TblClass tblClass;
}