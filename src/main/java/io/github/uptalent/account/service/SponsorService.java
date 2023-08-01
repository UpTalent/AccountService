package io.github.uptalent.account.service;

import io.github.uptalent.account.exception.SponsorNotFoundException;
import io.github.uptalent.account.mapper.SponsorMapper;
import io.github.uptalent.account.model.common.Author;
import io.github.uptalent.account.model.entity.Account;
import io.github.uptalent.account.model.entity.Sponsor;
import io.github.uptalent.account.model.response.AccountProfile;
import io.github.uptalent.account.repository.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SponsorService {
    private final SponsorRepository sponsorRepository;
    private final AccountSecurityService accountSecurityService;
    private final SponsorMapper sponsorMapper;

    public AccountProfile getSponsorProfile(Long id) {
        Sponsor sponsor = getSponsorById(id);
        if (accountSecurityService.isPersonalProfile(id))
            return sponsorMapper.toSponsorProfile(sponsor);
        else
            return AccountProfile.builder()
                    .id(sponsor.getId())
                    .avatar(sponsor.getAvatar())
                    .name(sponsor.getFullname())
                    .build();
    }

    public Sponsor getSponsorById(Long id) {
        return sponsorRepository.findById(id).orElseThrow(SponsorNotFoundException::new);
    }

    public Sponsor getSponsorByAccount(Account account) {
        return sponsorRepository.findSponsorByAccount(account)
                .orElseThrow(SponsorNotFoundException::new);
    }

    public Author getAuthorById(long id) {
        return sponsorRepository.getAuthorById(id)
                .orElseThrow(SponsorNotFoundException::new);
    }
}