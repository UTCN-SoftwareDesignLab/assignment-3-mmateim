package demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class PatientDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message ="Name Can only contain letters and .,-'")
    private String name;

    @NotNull
    @Digits(integer=3, fraction=0, message ="idCardNr is a number of max 3 digits")
    private Integer idCardNr;

    @NotNull
    @Pattern(regexp = "[0-9]+", message ="CNP can contain only digits")
    private String cnp;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9., ]+", message ="Address can contain only letters, digits, .,")
    private String address;

    @NotNull
   // @Pattern(regexp = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$")
    @Past(message = "Date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

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
