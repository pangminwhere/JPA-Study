package hellojpa;

import hellojpa.domain.MemberEx;
import hellojpa.domain.MemberEx2;
import hellojpa.domain.TeamEx;
import hellojpa.domain.TeamEx2;
import jakarta.persistence.*;

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
            TeamEx teamEx = new TeamEx();
            teamEx.setName("TeamA");
            em.persist(teamEx);

            MemberEx memberEx = new MemberEx();
            memberEx.setUsername("member1");
            memberEx.chageTeam(teamEx);
            em.persist(memberEx);

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

            TeamEx findTeamEx = em.find(TeamEx.class, teamEx.getId()); // 영속성 컨택스트의 1차 캐시
            List<MemberEx> memberExes = findTeamEx.getMembers();
            for (MemberEx m : memberExes) {
                System.out.println("m = " + m.getUsername());
            }

            /*
            일대다 단방향 연관관계 연습
            team 설정 후 team의 members 리스트를 받아와 member를 추가하면
            Member 테이블에 Update 쿼리가 실행되면서 수정 실행
             */
            MemberEx2 memberEx2 = new MemberEx2();
            memberEx2.setName("member1");

            em.persist(memberEx2);

            TeamEx2 teamEx2 = new TeamEx2();
            teamEx2.setName("TeamA");
            teamEx2.getMembers().add(memberEx2);

            em.persist(teamEx2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
