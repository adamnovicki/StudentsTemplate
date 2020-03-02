package pl.adamnovicki.studentstemplate.common

data class Result<out T>(
    var resultType: ResultType,
    val data: T? = null,
    val error: Exception? = null
) {

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(ResultType.SUCCESS, data)
        }

        fun <T> error(error: Exception? = null): Result<T> {
            return Result(ResultType.ERROR, error = error)
        }
    }
}