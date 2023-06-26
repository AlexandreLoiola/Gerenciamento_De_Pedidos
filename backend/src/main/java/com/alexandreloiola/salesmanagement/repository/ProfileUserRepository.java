package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.ProfileUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileUserRepository extends JpaRepository<ProfileUserModel, Long> {

    @Query(value = "SELECT * FROM TB_PROFILE_USER WHERE PROFILE_ID=:profileId", nativeQuery = true)
    Optional<ProfileUserModel> findBypProfileId(@Param("profileId") long id);
}
