package com.ohmynone.rest.repository.specification;

import com.ohmynone.rest.dto.BookmarkSearchDto;
import com.ohmynone.rest.entity.Bookmark;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.UUID;

public class BookmarkSpecification {

    public static Specification<Bookmark> available() {
        return (root, query, builder) -> builder.and(
                builder.isNull(root.get("book").get("deletedAt")),
                builder.isNull(root.get("deletedAt"))
        );
    }

    public static Specification<Bookmark> book(Long id) {
        return (root, query, builder) -> builder.equal(root.get("book").get("id"), id);
    }

    public static Specification<Bookmark> identity(UUID uid) {
        return (root, query, builder) -> builder.equal(root.get("book").get("identity").get("uid"), uid);
    }

    public static Specification<Bookmark> search(String normalizedText) {
        return (root, query, builder) -> {

            query.orderBy(builder.desc(builder.function(
                    "tgrm_order",
                    Float.class,
                    root.get("searchData").get("normalizedText"),
                    builder.literal(normalizedText))),
                    builder.desc(root.get("id"))
            );

            return builder.isTrue(builder.function(
                    "tgrm_search",
                    Boolean.class,
                    root.get("searchData").get("normalizedText"),
                    builder.literal(normalizedText)));
        };
    }

    public static Specification<Bookmark> filter(BookmarkSearchDto filter) {
        return (root, query, builder) -> {

            root.fetch("book", JoinType.LEFT);
            root.fetch("searchData");

            Specification<Bookmark> specification = Specification.where(available());

            if (filter.getBookId() != null) {
                specification = specification.and(book(filter.getBookId()));
            }

            if (filter.getSearch() != null) {
                specification = specification.and(search(filter.getNormalizedText()));
            } else {
                query.orderBy(builder.desc(root.get("id")));
            }

            if (filter.getIdentity() != null) {
                specification = specification.and(identity(filter.getIdentity().getUid()));
            }

            return specification.toPredicate(root, query, builder);
        };
    }
}
