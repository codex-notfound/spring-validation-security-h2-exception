package com.example.validationsecurityh2exception.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="contact_info")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @Column(name = "email")
    @NotBlank(message = "Email must not be blank.")
    @Email
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Phone number must not be blank.")
    @Pattern(regexp="(^$|[0-9]{10})", message="Phone number must be 10 digit.")
    private String phone;

    @Column(name = "message")
    @NotBlank(message = "Message must not be blank.")
    private String message;
}
