package com.goda.newstk.domain.common


fun Float.formatTo(numberOfDecimals: Int) =
        String.format("%.${numberOfDecimals}f", this)