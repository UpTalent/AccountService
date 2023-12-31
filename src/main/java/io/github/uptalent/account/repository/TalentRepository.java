package io.github.uptalent.account.repository;

import io.github.uptalent.account.model.entity.Account;
import io.github.uptalent.account.model.entity.Talent;
import io.github.uptalent.starter.model.common.Author;
import io.github.uptalent.starter.model.enums.ModerationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {
    Optional<Talent> findTalentByAccount(Account account);

    @Query("SELECT new io.github.uptalent.starter.model.common.Author(t.id, CONCAT(t.firstname, ' ', t.lastname), t.avatar) " +
            "FROM Talent t WHERE t.id = :id")
    Optional<Author> getAuthorById(long id);

    @Query("SELECT t.avatar FROM Talent t WHERE t.id = :id")
    Optional<String> findAvatarById(Long id);

    @Query("SELECT t.banner FROM Talent t WHERE t.id = :id")
    Optional<String> findBannerById(Long id);

    Page<Talent> findAllByAccountStatus(PageRequest pageRequest, ModerationStatus moderationStatus);
}
