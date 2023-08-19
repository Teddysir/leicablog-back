package leica.blog.repository;

import leica.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
    @Query("SELECT c FROM Category c WHERE c.parent is NULL")
    List<Category> findAll();

}
