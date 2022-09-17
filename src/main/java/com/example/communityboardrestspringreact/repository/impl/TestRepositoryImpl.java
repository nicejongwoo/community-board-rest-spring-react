package com.example.communityboardrestspringreact.repository.impl;

import com.example.communityboardrestspringreact.repository.custom.TestRepositoryCustom;
import com.example.communityboardrestspringreact.web.dto.request.TestSearch;
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
                                test.title,
                                test.content,
                                test.notice,
                                test.deleted,
                                test.createdBy,
                                test.createdAt
                        )
                )
                .from(test)
                .where(
                        keywordContains(search.getType(), search.getKeyword()),
                        deleted(search.getDeleted()),
                        notice(search.getNotice())
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

        return PageableExecutionUtils.getPage(content, pageable, content::size);
    }

    private BooleanExpression keywordContains(String type, String keyword) {
        if (hasText(type)) {
            if (hasText(keyword)) {
                switch (type) {
                    case "title":
                        return test.title.contains(keyword);
                    case "content":
                        return test.content.contains(keyword);
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    private BooleanExpression deleted(String deleted) {
        if (hasText(deleted)) {
                switch (deleted) {
                    case "Y":
                        return test.deleted.isTrue();
                    case "N":
                        return test.deleted.isFalse();
                    default:
                        return null;
                }
        }
        return null;
    }

    private BooleanExpression notice(String notice) {
        if (hasText(notice)) {
                switch (notice) {
                    case "Y":
                        return test.notice.isTrue();
                    case "N":
                        return test.notice.isFalse();
                    default:
                        return null;
                }
        }
        return null;
    }

}
