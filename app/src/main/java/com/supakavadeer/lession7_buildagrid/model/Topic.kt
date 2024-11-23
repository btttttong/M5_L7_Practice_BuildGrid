package com.supakavadeer.lession7_buildagrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val number: Int,
    @DrawableRes val imageResourceId: Int

)
