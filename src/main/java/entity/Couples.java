package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Couples extends BaseEntity {
    @NotNull
    @OneToOne
    private Student husbandStudent;

    @NotNull
    @OneToOne
    private Student wifeStudent;

    @NotNull
    @Size(min = 3 , max = 7)
    private Integer contractNumber;

    @NotBlank
    @Size(min = 10 , max = 40)
    private String homeAddress;
}
