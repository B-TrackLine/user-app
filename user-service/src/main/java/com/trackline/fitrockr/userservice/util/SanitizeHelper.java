package com.trackline.fitrockr.userservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SanitizeHelper
{
    public static String buildSaneLikeFilterValue(@NonNull String filterValue)
    {
        Objects.requireNonNull(filterValue);

        return "%" + filterValue.replace("%", "\\%").replace("_", "\\_") + "%";
    }

}