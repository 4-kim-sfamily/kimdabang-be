package com.kimdabang.kdbserver.product.media.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.product.media.domain.ProductMedia;
import com.kimdabang.kdbserver.product.media.dto.in.ProductMediaRequestDto;
import com.kimdabang.kdbserver.product.media.dto.out.ProductMediaResponseDto;
import com.kimdabang.kdbserver.product.media.infrastructure.ProductMediaRepository;
import com.kimdabang.kdbserver.product.product.domain.Product;
import com.kimdabang.kdbserver.product.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.PRODUCTMEDIA_NOT_FOUND;
import static com.kimdabang.kdbserver.common.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductMediaServiceImpl implements ProductMediaService {

    private final ProductMediaRepository productMediaRepository;
    private final ProductRepository productRepository;

    @Override
    public void addProductMedia(ProductMediaRequestDto productDto) {
        Product product = productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        productMediaRepository.save(productDto.toProductMediaEntity(product));
    }

    @Override
    public void updateProductMedia(ProductMediaRequestDto productDto) {
        Product product = productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));
        productMediaRepository.findByProductId(productDto.getProductId())
                .orElseThrow(() -> new CustomException(PRODUCTMEDIA_NOT_FOUND));

        productMediaRepository.save(productDto.toProductMediaEntity(product));

    }

    @Override
    public void deleteProductMedia(String productMediaId) {
        Long longProductMediaId = Long.parseLong(productMediaId);
        ProductMedia deleteProductMedia = productMediaRepository.findById(longProductMediaId)
                .orElseThrow(() -> new CustomException(PRODUCTMEDIA_NOT_FOUND));
        productMediaRepository.delete(deleteProductMedia);
    }

    @Override
    public List<ProductMediaResponseDto> getAllProductMedia(String productCode) {

        List<ProductMedia> productMediaList = productMediaRepository.findAllByProductCode(productCode);
        if (productMediaList != null) {
            return productMediaList.stream()
                    .map(productMedia -> ProductMediaResponseDto.builder()
                            .mediaName(productMedia.getMediaName())
                            .mediaType(productMedia.getMediaType())
                            .mediaURL(productMedia.getMediaURL())
                            .build())
                    .toList();
        }
        return List.of();
    }
}
