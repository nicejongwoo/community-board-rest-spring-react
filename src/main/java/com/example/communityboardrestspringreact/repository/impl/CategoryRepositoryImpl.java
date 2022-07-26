package com.example.communityboardrestspringreact.repository.impl;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.repository.custom.CategoryRepositoryCustom;
import com.example.communityboardrestspringreact.web.dto.response.CategoryResponse;
import com.example.communityboardrestspringreact.web.dto.search.CategorySearch;
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
import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory factory;

    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<CategoryResponse> search(CategorySearch search, Pageable pageable) {

        JPAQuery<CategoryResponse> query = factory
                .select(
                        Projections.fields(
                                CategoryResponse.class,
                                category.id,
                                category.name,
                                category.type
                        )
                )
                .from(category)
                .where(
                        keywordContains(search.getType(), search.getKeyword())
                );

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order order : pageable.getSort()) {
                PathBuilder pathBuilder = new PathBuilder<>(category.getType(), category.getMetadata());
                query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));

            }
        }

        List<CategoryResponse> content = query.fetch();

        JPAQuery<Category> countQuery = factory
                .select(category)
                .from(category)
                .where(
                        keywordContains(search.getType(), search.getKeyword())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression keywordContains(String type, String keyword) {

        if (hasText(type)) {
            if (hasText(keyword)) {
                switch (type) {
                    case "name":
                        return category.name.contains(keyword);
                    case "type":
                        return category.type.contains(keyword);
                    default:
                        return null;
                }
            }
        }

        return null;
    }

}
