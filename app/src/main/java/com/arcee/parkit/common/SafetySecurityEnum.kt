package com.arcee.parkit.common

enum class SafetySecurityEnum(val value: String) {
    NONE("none"),
    CCTV("cctv"),
    LIGHTING("lighting");

    companion object {
        fun fromValue(value: String): SafetySecurityEnum {
            return entries.firstOrNull { it.value == value } ?: NONE
        }
    }
}