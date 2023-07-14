package com.uptalent.account.mapper;

import com.uptalent.account.model.entity.Talent;
import com.uptalent.account.model.response.TalentFullProfile;
import com.uptalent.account.model.response.TalentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TalentMapper {
    @Mapping(target = "name", expression = "java(talent.getFirstname() + \" \" + talent.getLastname())")
    TalentFullProfile toTalentFullProfile(Talent talent);

    @Mapping(target = "name", expression = "java(talent.getFirstname() + \" \" + talent.getLastname())")
    TalentProfile toTalentProfile(Talent talent);
}