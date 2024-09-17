package com.kimdabang.kdbserver.cart.presentation;

import com.kimdabang.kdbserver.cart.application.CartService;
import com.kimdabang.kdbserver.cart.dto.in.CartRequestDto;
import com.kimdabang.kdbserver.cart.dto.out.CartCheckResponseDto;
import com.kimdabang.kdbserver.cart.vo.CartCheckResponseVo;
import com.kimdabang.kdbserver.cart.vo.CartResponseVo;
import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{productCode}")
    public CommonResponseEntity<CartCheckResponseVo> getCartCheck(
            @RequestHeader ("Authorization") String Authorization,
            @PathVariable String productCode,
            @RequestParam(defaultValue = "0") Long productOptionId) {
        CartCheckResponseVo cartCheckResponseVo = cartService.getCartCheck(productCode, Authorization, productOptionId);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "개별 장바구니 정보 조회 성공",
                cartCheckResponseVo
        );
    }

    @PutMapping("/{productCode}")
    public CommonResponseEntity<CartCheckResponseVo> updateCart(
            @RequestHeader ("Authorization") String Authorization,
            @PathVariable String productCode,
            @RequestParam Integer amount,
            @RequestParam(defaultValue = "0") Long productOptionId) {
        CartRequestDto cartRequestDto = CartRequestDto.builder()
                .productCode(productCode)
                .accessToken(Authorization)
                .amount(amount)
                .productOptionId(productOptionId)
                .build();
        CartCheckResponseVo cartCheckResponseVo = cartService.putCart(cartRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "개별 장바구니 업데이트 성공",
                cartCheckResponseVo
        );
    }

    @GetMapping("/list")
    public CommonResponseEntity<List<CartResponseVo>> getCartList(
            @RequestHeader ("Authorization") String Authorization) {
        List<CartResponseVo> cartResponseVoList = cartService.getAllCarts(Authorization);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "회원 장바구니 리스트 조회 성공",
                cartResponseVoList
        );
    }
}
