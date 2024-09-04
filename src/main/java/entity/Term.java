package entity;

import enumerations.TermSeason;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Term extends BaseEntity {

    @Column
    @NotBlank
    private Integer year;

    @Column
    @NotBlank
    @Enumerated(EnumType.STRING)
    private TermSeason termSeason;


}
