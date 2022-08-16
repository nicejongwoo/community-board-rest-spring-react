package com.example.communityboardrestspringreact.repository.impl;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.domain.QAccount;
import com.example.communityboardrestspringreact.repository.custom.CommunityRepositoryCustom;
import com.example.communityboardrestspringreact.web.dto.response.CommunityListResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommunitySearch;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.communityboardrestspringreact.domain.QAccount.*;
import static com.example.communityboardrestspringreact.domain.QAnswer.answer;
import static com.example.communityboardrestspringreact.domain.QCategory.category;
import static com.example.communityboardrestspringreact.domain.QCommunity.community;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class CommunityRepositoryImpl implements CommunityRepositoryCustom {

    private final JPAQueryFactory factory;

    public CommunityRepositoryImpl(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<CommunityListResponse> search(CommunitySearch search, Pageable pageable) {

        JPAQuery<CommunityListResponse> query = factory
                .select(
                        Projections.fields(
                                CommunityListResponse.class,
                                    community.id,
                                    community.title,
                                    community.content,
                                    community.createdBy,
                                    community.createdAt,
                                    community.updatedBy,
                                    community.updatedAt,
                                    account.name.as("createdName")
                                )
                )
                .from(community)
                .leftJoin(account)
                .on(account.email.eq(community.createdBy))
                .where(
                        keywordContains(search.getType(), search.getKeyword()),
                        eqCategory(search.getCategoryId())
                );

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order order : pageable.getSort()) {
                PathBuilder pathBuilder = new PathBuilder<>(community.getType(), community.getMetadata());
                query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));

            }
        }

        List<CommunityListResponse> content = query.fetch();

        JPAQuery<Community> countQuery = factory
                .select(community)
                .from(community)
                .leftJoin(account)
                .on(account.email.eq(community.createdBy))
                .where(
                        keywordContains(search.getType(), search.getKeyword()),
                        eqCategory(search.getCategoryId())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression keywordContains(String type, String keyword) {

        if (hasText(type)) {
            if (hasText(keyword)) {
                switch (type) {
                    case "title":
                        return community.title.contains(keyword);
                    case "content":
                        return community.content.contains(keyword);
                    case "createdName":
                        return account.name.contains(keyword);
                    default:
                        return null;
                }
            }
        }

        return null;
    }

    private BooleanExpression eqCategory(Long categoryId) {

        if (categoryId != null) {
            return community.category.id.eq(categoryId);
        }

        return null;
    }

}
