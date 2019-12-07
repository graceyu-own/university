package cn.falleaves.common

class ResponseData<T>(code: Double? = 0.0, data: T? = null) {

    var code: Double? = 0.0
    var data: T? = null

    init {
        this.code = code
        this.data = data;
    }

    companion object {

        fun <T> new(rv: ReturnValue): ResponseData<T> {
            return ResponseData(rv.code, rv.data as T)
        }

        fun <T> new(code: Double, data: T): ResponseData<T> {
            return ResponseData(code, data)
        }

    }

}