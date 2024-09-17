package com.kimdabang.kdbserver.cart.application;

import com.kimdabang.kdbserver.cart.domain.Cart;
import com.kimdabang.kdbserver.cart.dto.in.CartRequestDto;
import com.kimdabang.kdbserver.cart.dto.out.CartCheckResponseDto;
import com.kimdabang.kdbserver.cart.dto.out.CartResponseDto;
import com.kimdabang.kdbserver.cart.infrastructure.CartRepository;
import com.kimdabang.kdbserver.cart.vo.CartCheckResponseVo;
import com.kimdabang.kdbserver.cart.vo.CartResponseVo;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public CartCheckResponseVo getCartCheck(String productCode, String Authorization, Long productOptionId) {

        String userUuid = jwtTokenProvider.useToken(Authorization);

        Cart cart = cartRepository.findByProductCodeAndUserUuidAndProductOptionId(productCode, userUuid, productOptionId)
                .orElse(null);

        if (cart == null) {
            return new CartCheckResponseDto(false, 0).toVo();
        }

        boolean cartStatus = cart.isChecked();

        return new CartCheckResponseDto(cartStatus, cart.getAmount()).toVo();
    }

    @Override
    public void addCart(CartRequestDto cartRequestDto) {

        String userUuid = jwtTokenProvider.useToken(cartRequestDto.getAccessToken());

        cartRepository.save(cartRequestDto.toCartEntity(userUuid, true));

    }

    @Override
    public CartCheckResponseVo putCart(CartRequestDto cartRequestDto) {

        String userUuid = jwtTokenProvider.useToken(cartRequestDto.getAccessToken());

        Cart cart = cartRepository.findByProductCodeAndUserUuidAndProductOptionId(cartRequestDto.getProductCode(), userUuid, cartRequestDto.getProductOptionId())
                .orElse(null);

        boolean cartStatus;

        if (cart == null) {
            addCart(cartRequestDto);
            cartStatus = true;
        } else {
            if (cartRequestDto.getAmount() == 0) {
                cart.toZero();
                cartRepository.save(cart);
                cartStatus = false;
            } else {
                cart.onAmount(cartRequestDto.getAmount());
                cartRepository.save(cart);
                cartStatus = true;
            }
        }

        return new CartCheckResponseDto(cartStatus, cartRequestDto.getAmount()).toVo();

    }

    @Override
    public List<CartResponseVo> getAllCarts(String Authorization) {

        String userUuid = jwtTokenProvider.useToken(Authorization);
        List<Cart> cartList = cartRepository.findAllByUserUuid(userUuid);
        if (cartList != null) {
            return cartList.stream()
                    .filter(cart -> cart.getAmount() != 0)
                    .map(cart -> CartResponseDto.builder()
                            .productCode(cart.getProductCode())
                            .amount(cart.getAmount())
                            .productOptionId(cart.getProductOptionId())
                            .build().toVo())
                    .toList();
        }
        return List.of();
    }

}
