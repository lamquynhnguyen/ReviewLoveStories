package vn.sunasterisk.reviewlovestories.utils

import androidx.annotation.IntDef

@IntDef(
    MethodType.INSERT,
    MethodType.UPDATE,
    MethodType.DELETE
)
annotation class MethodType {
    companion object {
        const val INSERT = 1
        const val UPDATE = 2
        const val DELETE = 3
    }
}
