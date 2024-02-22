package kz.bitlab.group1.security.securitypro1.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Permission extends BaseEntity implements GrantedAuthority {
    private String permission;

    @Override
    public String getAuthority() {
        return permission;
    }
}
