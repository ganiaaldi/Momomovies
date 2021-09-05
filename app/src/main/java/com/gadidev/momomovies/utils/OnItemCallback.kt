package com.gadidev.momomovies.utils

import com.gadidev.momomovies.model.Movies

interface OnItemCallback {
    fun onItemClicked (data : Movies)
}