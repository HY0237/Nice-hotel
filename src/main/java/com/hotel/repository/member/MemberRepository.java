package com.hotel.repository.member;

import com.hotel.entity.Member;
import com.hotel.entity.Room;
import com.hotel.repository.room.RoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member>, MemberRepositoryCustom {

    Member findByEmail(String email);

    Member findByName(String name);
}
