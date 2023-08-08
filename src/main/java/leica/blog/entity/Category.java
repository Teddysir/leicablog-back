package leica.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // 부모 삭제시 자식도 삭제
    private Category parent; // parent_id -> parent

    public Category(String name, Category parent){
        this.name = name;
        this.parent = parent;
    }

    // 추가 메소드를 사용하여 부모-자식 관계 설정 및 제거할 수 있습니다.
//    public void addChild(Category child) {
//        children.add(child);
//        child.setParent(this);
//    }
//
//    public void removeChild(Category child) {
//        children.remove(child);
//        child.setParent(null);
//    }

}
