package com.kimdabang.kdbserver.address.infrastructure;

import com.kimdabang.kdbserver.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserUuid(String uuid);
    Optional<Address> findByUserUuidAndIsDefault(String uuid, Boolean isDefault);
    Optional<Address> findById(Long id);

}
