//package leica.blog.entity;
//
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//public class Category {
//
//    @Id @GeneratedValue
//    @Column(name = "category_id")
//    private Long id;
//
//    private String categoryName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Category parent; // parent_id -> parent
//
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
//    private List<Category> children = new ArrayList<>();
//
//    // 추가 메소드를 사용하여 부모-자식 관계 설정 및 제거할 수 있습니다.
////    public void addChild(Category child) {
////        children.add(child);
////        child.setParent(this);
////    }
////
////    public void removeChild(Category child) {
////        children.remove(child);
////        child.setParent(null);
////    }
//
//}
