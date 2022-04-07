package com.hotel.repository.member;

import com.hotel.constant.Role;
import com.hotel.constant.RoomType;
import com.hotel.dto.QReservationMainDto;
import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import com.hotel.dto.client.QClientDto;
import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.entity.QMember;
import com.hotel.entity.QReservation;
import com.hotel.entity.QRoom;
import com.hotel.repository.room.RoomRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    //시간별 옵션
    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        }
        else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }
        else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        }
        else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QMember.member.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if(StringUtils.equals("name", searchBy)){
            return QMember.member.name.like("%" + searchQuery + "%");
        }else if(StringUtils.equals("email", searchBy)){
            return QMember.member.email.like("%" + searchQuery + "%");
        }

        return  null;
    }
    @Override
    public Page<ClientDto> getClientPage(ClientSearchDto clientSearchDto, Pageable pageable) {
        QMember member = QMember.member;

        QueryResults<ClientDto> results = queryFactory
                .select(
                        new QClientDto(
                                member.id,
                                member.name,
                                member.email,
                                member.phoneNum,
                                member.role)
                )
                .from(member)
                .where(member.role.eq(Role.USER),
                        regDtsAfter(clientSearchDto.getSearchDateType()),
                        searchByLike(clientSearchDto.getSearchBy(), clientSearchDto.getSearchQuery())
                        )
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ClientDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }
}
