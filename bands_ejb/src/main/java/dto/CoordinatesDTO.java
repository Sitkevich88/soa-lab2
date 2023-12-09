package dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO implements Serializable {
    @NotNull
    private Long x; //Поле не может быть null
    
    @NotNull
    @DecimalMax("967.0")
    private Double y; //Максимальное значение поля: 967, Поле не может быть null
}