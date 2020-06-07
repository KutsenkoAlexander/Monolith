package ua.kay.online.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.model.Consumer;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@Repository
@Transactional
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query("select p.consumer from Product p " +
            "where p.category.id = ?1 " +
            "group by p.consumer.name, p.consumer.id")
    Stream<Consumer> findConsumerByProductCategoryId(Long id);

    @Async
    void deleteById(Long id);
}