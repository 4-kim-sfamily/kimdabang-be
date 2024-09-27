package com.kimdabang.kdbserver.cart.infrastructure;

import com.kimdabang.kdbserver.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProductCodeAndUserUuidAndProductOptionId(String productCode, String userUuid, Long productOptionId);
    List<Cart> findAllByUserUuid(String userUuid);
    List<Cart> findAllByUserUuidAndCheckBox(String userUuid, Boolean checkBox);

}
