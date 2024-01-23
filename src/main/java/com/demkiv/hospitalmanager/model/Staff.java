package com.demkiv.hospitalmanager.model;

import com.demkiv.hospitalmanager.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String phoneNumber;

    private String eMail;

    private String password;
}
