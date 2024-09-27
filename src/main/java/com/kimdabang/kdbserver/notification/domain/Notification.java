package com.kimdabang.kdbserver.notification.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("공지 제목")
    @Column(nullable = false, length = 50)
    private String title;

    @Comment("공지 생성일자")
    @Column(nullable = false)
    private Date activeDate;

    @Comment("공지 만료일자")
    @Column(nullable = false)
    private Date expireDate;

    @Comment("공지 미디어url")
    @Column(nullable = false, length = 100)
    private String mediaUrl;

}
