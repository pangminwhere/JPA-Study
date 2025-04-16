package hellojpa.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    /*
    일대일 양방향 설정
    다대일 양방향 매핑처럼 외래키가 있는 곳이 연관관계 주인
    주 테이블 설정 후 반대 테이블에 mappedBy 설정
    이 필드는 읽기 전용
     */
    @OneToOne(mappedBy = "locker")
    private MemberEx3 members;
}
