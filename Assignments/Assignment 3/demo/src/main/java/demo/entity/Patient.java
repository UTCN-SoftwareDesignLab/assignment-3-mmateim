package demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Integer idCardNr;
    private String cnp;
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    public Patient() {
    }

    public Patient(String name, Integer idCardNr, String cnp, String address, Date birthDate) {
        this.name = name;
        this.idCardNr = idCardNr;
        this.cnp = cnp;
        this.address = address;
        this.birthDate = birthDate;
    }



//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    public Set<Consultation> getConsultations() {
//        return consultations;
//    }
//
//    public void setConsultations(Set<Consultation> consultations) {
//        this.consultations = consultations;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdCardNr() {
        return idCardNr;
    }

    public void setIdCardNr(Integer idCardNr) {
        this.idCardNr = idCardNr;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
