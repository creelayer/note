package com.ohmynone.rest.repository.dialect;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class PostgreSqlDialect extends PostgreSQL10Dialect {
    public PostgreSqlDialect(){
        super();
        registerFunction("tgrm_search", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "?1 %> ?2"));
        registerFunction("tgrm_order", new SQLFunctionTemplate(StandardBasicTypes.FLOAT, "word_similarity(?1, ?2)"));
    }
}
