package com.ohmynone.rest.repository.specification;

import com.ohmynone.rest.dto.BookmarkSearch;
import com.ohmynone.rest.entity.Bookmark;
import org.springframework.data.jpa.domain.Specification;

public class BookmarkSpecification {

    public static Specification<Bookmark> available() {
        return (root, query, builder) -> builder.isNull(root.get("deletedAt"));
    }

    public static Specification<Bookmark> book(Long bookId) {
        return (root, query, builder) -> builder.equal(root.get("bookId"), bookId);
    }

    public static Specification<Bookmark> search(String normalizedText) {
        return (root, query, builder) -> builder.isTrue(builder.function(
                "tgrm_search",
                Boolean.class,
                root.join("searchData").get("normalizedText"),
                builder.literal(normalizedText)));
    }

    public static Specification<Bookmark> filter(BookmarkSearch filter) {
        return (root, query, builder) -> {

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
