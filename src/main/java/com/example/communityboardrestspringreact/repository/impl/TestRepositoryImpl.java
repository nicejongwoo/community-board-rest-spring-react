package com.example.communityboardrestspringreact.repository.impl;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.domain.QTest;
import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.custom.TestRepositoryCustom;
import com.example.communityboardrestspringreact.web.dto.request.TestSearch;
import com.example.communityboardrestspringreact.web.dto.response.CommunityListResponse;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
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

import static com.example.communityboardrestspringreact.domain.QCategory.category;
import static com.example.communityboardrestspringreact.domain.QCommunity.community;
import static com.example.communityboardrestspringreact.domain.QTest.*;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class TestRepositoryImpl implements TestRepositoryCustom {

    private final JPAQueryFactory factory;

    public TestRepositoryImpl(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Page<TestListResponse> search(TestSearch search, Pageable pageable) {
        JPAQuery<TestListResponse> query = factory
                .select(
                        Projections.fields(
                                TestListResponse.class,
                                test.id,
                                test.content,
                                test.useYn,
                                test.useEnabled
                        )
                )
                .from(test)
                .where(
                        keywordContains(search.getType(), search.getKeyword()),
                        eqUseYn(search.getUseYn())
                );

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order order : pageable.getSort()) {
                PathBuilder pathBuilder = new PathBuilder<>(test.getType(), test.getMetadata());
                query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));

            }
        }

        List<TestListResponse> content = query.fetch();

        JPAQuery<Test> countQuery = factory
                .select(test)
                .from(test)
                .where(
                        keywordContains(search.getType(), search.getKeyword()),
                        eqUseYn(search.getUseYn())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression keywordContains(String type, String keyword) {
        if (hasText(type)) {
            if (hasText(keyword)) {
                switch (type) {
                    case "content":
                        return test.content.contains(keyword);
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    private BooleanExpression eqUseYn(String useYn) {
        if (hasText(useYn)) {
                switch (useYn) {
                    case "Y":
                        return test.useYn.isTrue();
                    case "N":
                        return test.useYn.isFalse();
                    default:
                        return null;
                }
        }
        return null;
    }

}
