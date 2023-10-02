package com.example.dashboardrest.BO.Members;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.jna.platform.unix.solaris.LibKstat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nom;
    @NotEmpty
    private String prenom;
    @NotEmpty(message = "phone is required")
    @Pattern(regexp = "^$|\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}$")
    private String phone;
    @NotEmpty
    @Email
    private String email;
    @NotNull(message = "Date of birth must not be null")
    @Pattern(regexp = "^$|\\d{4}-\\d{2}-\\d{2}$",message = "pattern should match")
    private String birthday;
    @NotNull
    @NotEmpty
    private String address;
    @NotNull
    private String gender;
    //@NotEmpty
    private String filename;
    @Transient
    private byte[] Imagebyte;

    public byte[] getImagebyte() {
        return Imagebyte;
    }

    public void setImagebyte(byte[] imagebyte) {
        Imagebyte = imagebyte;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
