package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;
import jakarta.persistence.*;
import org.h2.engine.User;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            /*
            양방향 매팽 시 연관관계의 주인에 값을 설정
            (단, 객체지향의 관점에서 보면 항상 양쪽 다 값을 입력해줘야 한다.)
             */
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.chageTeam(team);
            em.persist(member);

            /*
            여기서 아래의 코드는 양방향 매핑 시 필요조건이 아니다.
            단, 2가지의 문제가 발생한다.
            아래 코드를 추가 후 em.flush() 및 em.clear()를 하면 문제가 안된다.
            하지만, flush(), clear()를 안해줬을 시 find() 메소드를 사용했을 때
            1차 캐시에서 조회해 오기 때문에 findTeam.getMembers() 시 출력되는 게 없다.
            ∴ 양뱡향 연관관계 세팅 시 연관관계의 주인 및 가짜 주인 모두 값을 세팅해주는게 맞다.
             */
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 영속성 컨택스트의 1차 캐시
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
