package hellojpa.domain;

import jakarta.persistence.*;

@Entity
public class MemberEx2 {

    @Id @GeneratedValue
    @Column(name = "MEMBEREX2_ID")
    private Long id;

    private String name;


    /*
    일대다 양방향 설정 가능
    스펙상 공식적으로 존재하지 않음
    읽기 전용 필드를 사용해서 양방향 처럼 사용하는 것임
    -> 다대일 양방향 사용 권장 (무조건!!)
     */
    @ManyToOne
    @JoinColumn(name = "TEAMEX2_ID", insertable = false, updatable = false)
    private TeamEx2 team;

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
}
