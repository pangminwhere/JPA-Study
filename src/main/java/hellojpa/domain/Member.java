package hellojpa.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMEBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne // 관계가 어떻게 되는지
    @JoinColumn(name = "TEAM_ID") // Join 하는 컬럼은 무엇인지
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    /*
    연관관계의 주인의 setTeam을 할 때 반대의 값에도 member를 추가해주는 것을 set 메소드에 넣어둔다.
    이렇게 구성하면 깜빡해도 setTeam 시 알아서 반대의 테이블에서도 member가 저장되기 때문이다.
    또한, 객체지향설계 시 Getter, Setter 관례를 따라 Setter에는 닫혀있는 구조가 객체지향적 구조이다.
    따라서, setTeam 보다는 chageTeam 등으로 메소드 명을 수정하여 기존 Setter와 다르게 필요한 값을 수정하는
    것이다를 명시적으로 알려주는 것이다.
     */
    public void chageTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    /*
    양방향 매핑에서 toString() 조회 시
    Member에서 team을 호출하고 team에서도 members에 대한 각각의 컬렉션의
    toString()을 조회하므로 양쪽에서 계속 조회되는 무한 루프가 발생할 수 있으니 조심해야 한다.
    ∴ lombok의 toString() 쓰지 않는다.
    또한, JSON 생성 라이브러리 Controller에서  ResponseEntity도 절대 쓰지 않는다.
    이유 1. 무한 루프가 발생할 수 있다.
    이유 2. Entity는 변경될 수 있는데 API에 직접 반환되는 구조이면 스펙이 바뀌어버린다.
    ∴ 따라서 DTO를 만들어 값만 반환한다.
     */
//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", team=" + team +
//                '}';
//    }
}
