package hellojpa.domain;

import jakarta.persistence.Entity;

@Entity
public class Album extends Item2{
    private String artist;
}
