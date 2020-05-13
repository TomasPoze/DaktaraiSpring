package lt.codeacademy.springwork.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "docname")
    private String docname;

    @Column(name = "region")
    private String region;

    @Column(name = "spec")
    private String spec;
}
