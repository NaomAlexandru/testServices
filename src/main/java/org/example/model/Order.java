package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "T_Order")
public class Order {

    @Id
    private Integer id;

    private String productName;
    private Integer productPrice;
    private Integer productQuantity;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
