package com.shreyas.go_train_schedule.models

enum class RouteNames(val lineCode: String) {

    // Main Lines
    BARRIE_LINE("BR"),
    KITCHENER_LINE("KI"),
    LAKESHORE_EAST_LINE("LE"),
    LAKESHORE_WEST_LINE("LW"),
    MILTON_LINE("MI"),
    RICHMOND_HILL_LINE("RH"),
    STOUFFVILLE_LINE("ST"),
    UP_EXPRESS("UP"),

    // Additional Off time Lines
    GEORGETOWN_LINE("GT"),

}