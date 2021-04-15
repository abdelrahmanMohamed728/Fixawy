package com.example.fixawy.network.common

class GraphqlQueries {
    companion object{
        const val GET_ALL_FIXER = "fixer {" +
                "    getFixers {" +
                "      id ," +
                "      name ," +
                "      rate ," +
                "      departmentId ," +
                "      mobile ," +
                "      cityId ," +
                "prices{" +
                   " minPrice"+
                "}"+
                "      identityNo" +
                "    }" +
                "  }"
    }
}