package com.security.poc.entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import com.security.poc.constant.OperationType;
import com.security.poc.constant.ResourceType;
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "privileges")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

<<<<<<< HEAD
    @ManyToMany(mappedBy = "privileges")
    @JsonIgnore
    private Collection<Role> roles;
=======
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    private OperationType operation;

>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
}
