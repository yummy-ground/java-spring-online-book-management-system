package yummy_ground.yummygyudon.obms.external.db.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yummy_ground.yummygyudon.obms.external.db.entity.BookEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByTitle(String title);

    List<BookEntity> findAllByAuthor(String author);

    @Modifying
    @Query("UPDATE BookEntity be SET be.title = :title, be.author = :author, be.tags = :tags, be.publishDate = :publishDate WHERE be.id = :id")
    void update(@Param("id") long id, @Param("title") String title, @Param("author") String author, @Param("tags") List<String> tags, @Param("publishDate") LocalDate publishAt);

    void deleteById(long id);

}
