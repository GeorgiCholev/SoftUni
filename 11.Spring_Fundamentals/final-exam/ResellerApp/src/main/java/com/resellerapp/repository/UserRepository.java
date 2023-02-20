package com.resellerapp.repository;

import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "select * " +
            "from reseller.users as u " +
            "inner join reseller.users_offers as uo " +
            "on u.id = uo.seller_id " +
            "where uo.offer_id = :offerId")
    Optional<User> findByOffer(@Param("offerId") Long offerId);
}
