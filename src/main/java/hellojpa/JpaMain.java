package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setName("member1");
            member1.changeTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.changeTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            // Member m = em.find(Member.class, member1.getId());

            em.createQuery("select m from Member m", Member.class).getResultList();

            //System.out.println("m = " + m.getTeam().getClass());

            System.out.println("==========================");
            //System.out.println("team.name = " + m.getTeam().getName()); // 초기화
            //System.out.println(m.getTeam().getMembers());
            System.out.println("==========================");

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getName());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getName();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("Team = " + team.getName());

    }
}
