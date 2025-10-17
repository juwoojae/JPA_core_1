package jpashop;

import jakarta.persistence.*;
import jpashop.domain.Member;
import jpashop.domain.Order;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        try {
            //구현
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally { //하나의 트랜잭션이 끝나면, 무조건 리소스를 반납해주어야한다
            em.close();
        }
        emf.close();
    }
}
