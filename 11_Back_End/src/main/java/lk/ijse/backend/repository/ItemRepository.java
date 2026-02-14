package lk.ijse.backend.repository;

import lk.ijse.backend.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    @Query(value = "SELECT CASE " +
            "WHEN COUNT(iid) = 0 THEN 'I001' " +
            "ELSE CONCAT('I', LPAD(CAST(SUBSTRING(MAX(iid), 2) AS UNSIGNED) + 1, 3, '0')) " +
            "END FROM item", nativeQuery = true)
    String findNextId();
}