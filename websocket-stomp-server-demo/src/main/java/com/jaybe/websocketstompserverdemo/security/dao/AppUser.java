package com.jaybe.websocketstompserverdemo.security.dao;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
                    joinColumns = @JoinColumn(name = "USER_ID"),
                    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<AppRole> authorities;

    public AppUser addRole(AppRole appRole) {
        if (null == this.authorities) {
            authorities = new HashSet<>();
        }
        authorities.add(appRole);
        return this;
    }
}
