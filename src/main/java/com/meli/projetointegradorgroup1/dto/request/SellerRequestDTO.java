package com.meli.projetointegradorgroup1.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 * @author Hugo Victor
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDTO {

    @NotBlank
    @Size(min = 3, message = "minimo 3 letras")
    private String name;

    @CPF
    private String cpf;

    @Email
    private String email;

}
