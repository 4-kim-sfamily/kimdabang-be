package com.kimdabang.kdbserver.category.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Comment("카테고리 제목")
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    @Comment("카테고리 깊이")
    @Column(nullable = true)
    private Long depth;

    @Comment("카테고리 삭제 여부")
    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @Builder
    public Category(
            Long id,
            String name,
            Category parent,
            Long depth
    ) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.depth = depth;
        this.active = true;
        this.children = new ArrayList<>();
    }
    public void updateActive(Boolean active){
        this.active = active;
    }
}

