package com.ohmynone.rest.repository.specification;

import com.ohmynone.rest.dto.BookmarkSearch;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.entity.BookmarkSearchData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class BookmarkSpecification {

    public static Specification<Bookmark> available() {
        return (root, query, builder) -> builder.and(
                builder.isNull(root.get("book").get("deletedAt")),
                builder.isNull(root.get("deletedAt"))
        );
    }

    public static Specification<Bookmark> book(Long bookId) {
        return (root, query, builder) -> builder.equal(root.get("bookId"), bookId);
    }

    public static Specification<Bookmark> search(String normalizedText) {
        return (root, query, builder) -> {

            query.orderBy(builder.desc(builder.function(
                    "tgrm_order",
                    Float.class,
                    root.get("searchData").get("normalizedText"),
                    builder.literal(normalizedText))));

            return builder.isTrue(builder.function(
                    "tgrm_search",
                    Boolean.class,
                    root.get("searchData").get("normalizedText"),
                    builder.literal(normalizedText)));
        };
    }

    public static Specification<Bookmark> filter(BookmarkSearch filter) {
        return (root, query, builder) -> {

            root.fetch("book", JoinType.LEFT);
            root.fetch("searchData");

            Specification<Bookmark> specification = Specification.where(available());

            if (filter.getBookId() != null) {
                specification = specification.and(book(filter.getBookId()));
            }

            if (filter.getS() != null) {
                specification = specification.and(search(filter.getNormalizedText()));
            }

            return specification.toPredicate(root, query, builder);
        };
    }
}
