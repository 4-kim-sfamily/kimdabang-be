package com.kimdabang.kdbserver.agreement.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
public class Agreement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이용 약관 동의")
    @Column(nullable = false)
    private Boolean termsChecked;

    @Comment("개인정보 동의")
    @Column(nullable = false)
    private Boolean privacyChecked;

    @Comment("카드 동의")
    @Column(nullable = false)
    private Boolean cardChecked;

    @Comment("이메일 수신 동의")
    @Column(nullable = false)
    private Boolean emailChecked;

    @Comment("문자 수신 동의")
    @Column(nullable = false)
    private Boolean smsChecked;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Agreement(
            Long id,
            Boolean termsChecked,
            Boolean privacyChecked,
            Boolean cardChecked,
            Boolean emailChecked,
            Boolean smsChecked,
            User user

    ) {
        this.id = id;
        this.termsChecked = termsChecked;
        this.privacyChecked = privacyChecked;
        this.cardChecked = cardChecked;
        this.emailChecked = emailChecked;
        this.smsChecked = smsChecked;
        this.user = user;
    }
}
