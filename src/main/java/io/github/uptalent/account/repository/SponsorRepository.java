package io.github.uptalent.account.repository;

import io.github.uptalent.account.model.entity.Account;
import io.github.uptalent.account.model.entity.Sponsor;
import io.github.uptalent.starter.model.common.Author;
import io.github.uptalent.starter.model.enums.ModerationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    Optional<Sponsor> findSponsorByAccount(Account account);

    @Query("SELECT new io.github.uptalent.starter.model.common.Author(s.id, s.fullname, s.avatar) " +
            "FROM Sponsor s WHERE s.id = :id")
    Optional<Author> getAuthorById(long id);

    @Query("SELECT s.avatar FROM Sponsor s WHERE s.id = :id")
    Optional<String> findAvatarById(Long id);

    Page<Sponsor> findAllByAccountStatus(PageRequest pageRequest, ModerationStatus status);
}
