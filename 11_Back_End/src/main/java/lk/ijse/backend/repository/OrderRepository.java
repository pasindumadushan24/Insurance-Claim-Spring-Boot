package lk.ijse.backend.repository;

import lk.ijse.backend.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
//public interface OrderRepository extends JpaRepository<Orders, Long> {
//    @Query("SELECT COALESCE(MAX(o.oid), 0) + 1 FROM Orders o")
//    Long findNextId();
//}


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    // Orders o ලෙස table එකේ alias එක නිවැරදිව පාවිච්චි කරන්න
    @Query("SELECT COALESCE(MAX(o.oid), 0) + 1 FROM Orders o")
    Long findNextId();
}