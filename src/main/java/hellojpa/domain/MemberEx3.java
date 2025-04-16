package hellojpa.domain;

import jakarta.persistence.*;

@Entity
public class MemberEx3 {

    @Id @GeneratedValue
    @Column(name = "MEMBEREX3_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "LOKCER_ID")
    private Locker locker;

    private String username;
}
