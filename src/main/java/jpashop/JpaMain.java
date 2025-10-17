package jpashop;

import jakarta.persistence.*;
import jpashop.domain.Member;
import jpashop.domain.Order;

public class JpaMain {

    /**
     * 데이터 중심 설계
     * 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
     * 테이블의 외래키를 객체에 그대로 가지고 옴
     * (객체 지향 설계라면, Member 는 Order 를 멤버로 가지고 있어야 맞는 설계이다)
     * 객체 그래프 탐색이 불편하다. 식별자만 가지고 있으므로,
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        try {

            Order order = em.find(Order.class, 1);
            Long memberId = order.getMemberId();

            Member member = em.find(Member.class, memberId); //너무 비효율적이다. Order 에서 바로 Member를 꺼내야하는데...

            Member findMember = order.getMember();

            em.persist(member);
            System.out.println("Members persisted");
            ;
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally { //하나의 트랜잭션이 끝나면, 무조건 리소스를 반납해주어야한다
            em.close();
        }
        emf.close();
    }
}
