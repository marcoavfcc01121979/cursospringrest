package curso.api.rest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Role")
@Table(name = "role")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements GrantedAuthority {

    private static  final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    private Long id;

    private String nomeRole;

    @Override
    public String getAuthority() { /* Retorna nome no papel ou autorização exemplo ROLE_GERENTE */
        return this.nomeRole;
    }
}
