package curso.api.rest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Usuario")
public class Usuario implements UserDetails {

    private static  final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String senha;

    private String nome;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<Telefone>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_role", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id"}, name = "unique_role_user"),
        joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario", unique = false,
            foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)
    ),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role", unique = false,
            foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)
        )
    )
    private List<Role> roles; /* Os papeis ou acessos */

    /* São os acessos do usuário ROLE_ADMIN OU ROLE_VISITANTE */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
