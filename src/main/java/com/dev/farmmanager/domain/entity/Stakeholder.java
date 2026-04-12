package com.dev.farmmanager.domain.entity;

import com.dev.farmmanager.domain.enumeration.StakeholderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name = Stakeholder.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class Stakeholder extends AbstractEntity {

    protected static final String TABLE_NAME = "stakeholder";

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @CNPJ
    @Column(name = "cnpj")
    private String cnpj;

    @CPF
    @Column(name = "cpf")
    private String cpf;

    @Enumerated(value = EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "type", columnDefinition = "stakeholder_type")
    private StakeholderType type;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
}
