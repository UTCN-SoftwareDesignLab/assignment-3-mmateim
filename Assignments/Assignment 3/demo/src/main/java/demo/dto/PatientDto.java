package demo.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class PatientDto {

    @NotNull
    @Pattern(regexp = "[a-zA-Z ]")
    private String name;

    @NotNull
    @Digits(integer=3, fraction=0)
    private Integer idCardNr;

    @NotNull
    @Pattern(regexp = "[0-9]")
    private String cnp;

    @NotNull
    @Pattern(regexp = "[a-zA-Z ]")
    private String address;

    @NotNull
    @Pattern(regexp = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$")
    @Past
    private Date birthDate;
}
