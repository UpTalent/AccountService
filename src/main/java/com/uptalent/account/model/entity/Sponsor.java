package com.uptalent.account.model.entity;

import com.uptalent.account.model.entity.Account;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinTable(
            name = "sponsor_account",
            joinColumns = @JoinColumn(name = "sponsor_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Account account;

    @Column(length = 30, nullable = false, name = "fullname")
    private String fullname;
}