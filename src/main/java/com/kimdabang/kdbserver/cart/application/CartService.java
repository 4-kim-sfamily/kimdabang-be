package com.kimdabang.kdbserver.cart.application;

import com.kimdabang.kdbserver.cart.dto.in.CartRequestDto;
import com.kimdabang.kdbserver.cart.vo.CartCheckResponseVo;
import com.kimdabang.kdbserver.cart.vo.CartResponseVo;

import java.util.List;

public interface CartService {

    void addCart(CartRequestDto cartRequestDto);
    CartCheckResponseVo putCart(CartRequestDto cartRequestDto);
    CartCheckResponseVo getCartCheck(String productCode, String Authorization, Long productOptionId);
    List<CartResponseVo> getAllCarts(String Authorization);
}
