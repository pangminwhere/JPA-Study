package hellojpa.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamEx2 {

    @Id @GeneratedValue
    @Column(name = "TEAMEX2_ID")
    private Long id;

    private String name;

    /*
    일대다 단방향 연관관계 설정 (JoinColumn을 무조건 사용해야 한다)
    -> 그렇지 않으면 조인테이블 방식을 사용해 중간에 테이블 하나 추가된다.
    외래키가 있는 테이블이 아닌 반대의 테이블을 주인으로 설정
    Team의 members가 추가될 시 DB의 MEMBER의 외래키 값이 수정
    객체와 테이블의 차이 때문에 반대편 테이블의 외래키를 관리하는 특이한 구조
     */
    @OneToMany
    @JoinColumn(name = "MEMBEREX2_ID")
    private List<MemberEx2> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberEx2> getMembers() {
        return members;
    }

    public void setMembers(List<MemberEx2> members) {
        this.members = members;
    }
}
