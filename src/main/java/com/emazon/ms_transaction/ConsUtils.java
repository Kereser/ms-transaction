package com.emazon.ms_transaction;

public class ConsUtils {

    public static PathBuilder builderPath() {
        return new PathBuilder();
    }

    private ConsUtils() {
    }

    public static final String AUX_DEPOT_ROLE = "AUX_DEPOT";
    public static final String CLIENT_ROLE = "CLIENT";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";

    public static final String FIELD_ITEM = "$.fieldErrors.item";
    public static final String FIELD_MESSAGE = "$.message";
    public static final String FIELD_USER_ID = "$.fieldErrors.userId";
    public static final String NOT_NULL = "must not be null";

    public static final Integer INTEGER_1 = 1;
    public static final Long LONG_1 = 1L;

    /*** DB ***/
    public static final boolean FALSE = false;

    public static class PathBuilder {
        private String finalPath = "/transactions";

        public PathBuilder withSupply() {
            this.finalPath += "/supply";
            return this;
        }

        public PathBuilder withSales() {
            this.finalPath += "/sales";
            return this;
        }

        public String build() {
            return finalPath;
        }
    }
}
