package hellojpa.domain;

import jakarta.persistence.Entity;

@Entity
public class Book extends Item2 {

    private String author;
    private String isbn;
}
