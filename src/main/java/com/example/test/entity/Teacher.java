package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 2, message = "Too short")
    @Length(max = 255, message = "Too long")
    private String firstName;

    @NotBlank
    @Length(min = 2, message = "Too short")
    @Length(max = 255, message = "Too long")
    private String lastName;

    @NotBlank
    @Length(min = 2, message = "Too short")
    @Length(max = 255, message = "Too long")
    private String middleName;

    @OneToOne(cascade = CascadeType.ALL)
    private Subject subject;
}
