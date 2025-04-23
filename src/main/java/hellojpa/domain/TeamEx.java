package hellojpa.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamEx {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;


    /*
    양방향 연관관계에서 Team은 member의 리스트를 가져서 양방향 조회가 가능하도록 함.
    new ArrayList<>()로 객체를 생성해놔야. add 시 NullPointException 방지
    mappedBy 키워드로 다른 객체의 어느 부분과 연관되어 있는지 설정
    (team으로 mapping되어 있음)
    Team의 member는 읽기 전용으로
    수정 시 연관관계의 주인이 아닌 가짜 주인의 내용 수정 시 DB에 반영 X

     */
    @OneToMany(mappedBy = "teamEx")
    private List<MemberEx> memberExes = new ArrayList<>();

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

    public List<MemberEx> getMembers() {
        return memberExes;
    }

    public void setMembers(List<MemberEx> memberExes) {
        this.memberExes = memberExes;
    }

//    @Override
//    public String toString() {
//        return "Team{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", members=" + members +
//                '}';
//    }
}
