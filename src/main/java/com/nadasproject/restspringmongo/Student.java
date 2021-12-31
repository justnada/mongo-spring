package com.nadasproject.restspringmongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubject;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created_at;

    public Student(String firstName,
                   String lastName,
                   String email,
                   Gender gender,
                   Address address,
                   List<String> favouriteSubject,
                   BigDecimal totalSpentInBooks,
                   LocalDateTime created_at) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubject = favouriteSubject;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getFavouriteSubject() {
        return favouriteSubject;
    }

    public void setFavouriteSubject(List<String> favouriteSubject) {
        this.favouriteSubject = favouriteSubject;
    }

    public BigDecimal getTotalSpentInBooks() {
        return totalSpentInBooks;
    }

    public void setTotalSpentInBooks(BigDecimal totalSpentInBooks) {
        this.totalSpentInBooks = totalSpentInBooks;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

}
