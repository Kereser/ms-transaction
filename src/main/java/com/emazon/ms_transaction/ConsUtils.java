package com.emazon.ms_transaction;

public class ConsUtils {

    public static PathBuilder builderPath() {
        return new PathBuilder();
    }

    private ConsUtils() {
    }

    public static final String MS_STOCK = "MS-STOCK";
    public static final String MS_STOCK_URL = "${external.feign.url.ms-stock}";
    public static final String JWT_KEY = "${security.jwt.key.private}";
    public static final String JWT_USER = "${security.jwt.user.generator}";
    public static final Long PLUS_30_MINUTES = 1800000L;

    public static final String COMMA_DELIMITER = ",";
    public static final String COLON_DELIMITER = ":";

    public static final String USER = "testUser";
    public static final String AUX_DEPOT = "AUX_DEPOT";
    public static final String CLIENT = "CLIENT";
    public static final Long USER_ID_1 = 1L;
    public static final String PASSWORD = "password";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ROLE = "ROLE_";

    public static final String FIELD_ITEM = "$.fieldErrors.item";
    public static final String FIELD_MESSAGE = "$.message";
    public static final String FIELD_USER_ID = "$.fieldErrors.userId";
    public static final String NOT_NULL = "must not be null";

    public static final Integer INTEGER_1 = 1;
    public static final Long LONG_1 = 1L;

    public static final String BASIC_URL = "/transactions";
    public static final String SUPPLY_URL = "/supply";
    public static final String SALES_URL = "/sales";

    public static final String ARTICLES_SUPPLY_URL = "/articles/supply";
    public static final String SWAGGER_URL = "/swagger-ui/**";
    public static final String SWAGGER_DOCS_URL = "/v3/api-docs/**";

    /*** Methods ***/
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String REQUESTED_WITH = "X-Requested-With";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String FRONT_URL = "http://localhost:4200";
    public static final String MATCH_ALL_URL = "/**";

    /*** DB ***/
    public static final boolean FALSE = false;
    public static final boolean TRUE = true;

    public static class PathBuilder {
        private String finalPath = BASIC_URL;

        public PathBuilder withSupply() {
            this.finalPath += SUPPLY_URL;
            return this;
        }

        public PathBuilder withSales() {
            this.finalPath += SALES_URL;
            return this;
        }

        public String build() {
            return finalPath;
        }
    }
}
