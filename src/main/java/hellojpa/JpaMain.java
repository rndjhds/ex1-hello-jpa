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
            // 객체를 생성한 상태(비영속 상태)
/*
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
*/

            // 객체를 저장한 상태(영속 상태)
/*            System.out.println("BEFORE");
            em.persist(member);
            System.out.println("AFTER");
*/

            // 영속
/*
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println(findMember1 == findMember2);
*/
            // 영속
/*

            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
*/

            // 영속
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();

            System.out.println("================================");
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
