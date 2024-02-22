package kz.bitlab.group1.security.securitypro1.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
