package com.skyview.scheduler.model;
import jakarta.persistence.OneToOne;
import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    private String firstName;
    private String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "staff")
    private Account account;


    private String fullName;

    private String email;

    private String phoneNumber;

    private int age;

    private boolean canCoachGame;

    public Staff() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCanCoachGame() {
        return canCoachGame;
    }

    public void setCanCoachGame(boolean canCoachGame) {
        this.canCoachGame = canCoachGame;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
