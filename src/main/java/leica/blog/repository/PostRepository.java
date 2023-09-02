package leica.blog.repository;

import leica.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderById();
    List<Post> findByTitleContainingOrContentContaining(String keyword, String keyword2);

    List<Post> findByCategoryName(String name);

    Post[] findByCategoryNameOrCategory_ParentName(String name, String name1);

}

