package com.kimdabang.kdbserver.cart.application;

import com.kimdabang.kdbserver.cart.dto.in.CartRequestDto;
import com.kimdabang.kdbserver.cart.vo.CartCheckBoxResponseVo;
import com.kimdabang.kdbserver.cart.vo.CartCheckResponseVo;
import com.kimdabang.kdbserver.cart.vo.CartResponseVo;

import java.util.List;

public interface CartService {

    void addCart(CartRequestDto cartRequestDto);
    CartCheckResponseVo putCart(CartRequestDto cartRequestDto);
    CartCheckBoxResponseVo getCheckBox(String productCode, String Authorization, Long productOptionId);
    CartCheckBoxResponseVo changeCheckBox(String productCode, String Authorization, Long productOptionId);
    CartCheckResponseVo getCartCheck(String productCode, String Authorization, Long productOptionId);
    List<CartResponseVo> getAllCarts(String Authorization);
    List<CartResponseVo> getAllCheckedCarts(String Authorization);
}
