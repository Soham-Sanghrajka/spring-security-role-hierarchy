package com.security.poc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
<<<<<<< HEAD
@Table(name = "groups_new")
=======
@Table(name = "tbl_groups")
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

<<<<<<< HEAD
    private String name;

    private Long countryAdmin;

    private Long stateAdmin;

    private Long cityAdmin;

    private Long districtAdmin;

    private Boolean status;

    @ManyToMany
    @JoinTable(name = "groups_users", joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Collection<User> users;

    @ManyToOne
    @JoinColumn(name = "districts_id" , referencedColumnName = "id" ,nullable = false)
    private District district;
=======
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    private Boolean status;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGroupRole> userGroupRoles = new ArrayList<>();
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
}