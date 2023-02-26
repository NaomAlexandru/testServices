package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id  //mapare la baza de date
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String name;
    private String mobile;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private final List<Order> orders = new ArrayList<>();
}
