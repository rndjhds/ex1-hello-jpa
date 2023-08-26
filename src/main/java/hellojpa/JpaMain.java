package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");

            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);
            em.persist(member);

            /*em.flush();
            em.clear();*/

            /*Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
*/
            Member member1 = new Member();
            member1.setName("member2");
            member1.changeTeam(team);
            em.persist(member1);

            List<Member> members = team.getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }


            /*Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();
            System.out.println("members = " + members);

            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }*/

            // findMember의 Team을 update
/*          Team team1 = new Team();
            team1.setName("TeamB");
            em.persist(team1);
            findMember.setTeam(team1);*/


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
