package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "participates")
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Participates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @EqualsAndHashCode.Include
    @ManyToMany
    @JoinColumn(name = "auto_user_id")
    private User user;
    @EqualsAndHashCode.Include
    @ManyToMany
    @JoinColumn(name = "post_id")
    private Post post;
}
