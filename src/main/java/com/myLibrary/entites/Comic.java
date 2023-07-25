package com.myLibrary.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Comic implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String series;
    private int number;
    private String summary;

    private String drawer;
    private String scriptwriter;
    private String colourist;
    private String publisher;

    @ManyToOne
    private Category category;
}
