package com.example.communityboardrestspringreact.repository.impl;

import com.example.communityboardrestspringreact.repository.custom.AccountRepositoryCustom;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountSearchResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.communityboardrestspringreact.domain.QAccount.account;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory factory;

    public AccountRepositoryImpl(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<AccountSearchResponse> search(AccountSearch search, Pageable pageable) {
        JPAQuery<AccountSearchResponse> query = factory
                .select(
                        Projections.fields(
                                AccountSearchResponse.class,
                                account.id,
                                account.accountToken,
                                account.name,
                                account.email,
                                account.phone,
                                account.joinedAt,
                                account.profileImage
                        )
                )
                .from(account)
                .where(
                    keywordContains(search.getType(), search.getKeyword())
                );

        int totalSize = query.fetch().size();

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order order : pageable.getSort()) {
                PathBuilder pathBuilder = new PathBuilder<>(account.getType(), account.getMetadata());
                query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
            }
        }

        List<AccountSearchResponse> content = query.fetch();

        return new PageImpl<>(content, pageable, totalSize);
    }


    private BooleanExpression keywordContains(String type, String keyword) {

        if (StringUtils.hasText(type)) {
            if (StringUtils.hasText(keyword)) {
                switch (type) {
                    case "name":
                        return account.name.contains(keyword);
                    default:
                        return null;
                }
            }
        }

        return null;
    }
}
