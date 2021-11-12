package com.meli.projetointegradorgroup1.entity;


import ch.qos.logback.core.status.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StockType {
       @JsonProperty("FRESH")
        FRESH,
        @JsonProperty("FROZEN")
        FROZEN,
        @JsonProperty("NATURAL")
        NATURAL,

        //    FRESH("FRESH"),
    //    FROZEN("FROZEN"),
   //     NATURAL("NATURAL");

   /*     private static Map<String, StockType> FORMAT_MAP = Stream
                .of(StockType.values())
                .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

        private final String formatted;

        StockType(String formatted) {
                this.formatted = formatted;
        }

        @JsonCreator // This is the factory method and must be static
        public static StockType fromString(String string) {
                return Optional
                        .ofNullable(FORMAT_MAP.get(string))
                        .orElseThrow(() -> new IllegalArgumentException(string));
        }*/
}
