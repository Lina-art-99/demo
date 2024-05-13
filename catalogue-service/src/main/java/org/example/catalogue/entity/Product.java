package org.example.catalogue.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "catalogue", name = "t_product")
@NamedQueries(
        //name должно быть уникальным для всего приложения
        @NamedQuery(name ="Product.findAllByDetailsLikeIgnoreCase",
        query = "select p from Product p where p.title ilike :filter")
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "c_title")
    @Size(min = 3, max = 50)
    @NotNull
    private String title;
    @Column(name = "c_details")
    @Size(max = 1000)
    private String details;
}
