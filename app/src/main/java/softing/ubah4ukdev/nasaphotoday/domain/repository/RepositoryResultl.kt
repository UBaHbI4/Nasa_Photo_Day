package softing.ubah4ukdev.nasaphotoday.domain.repository.nasa

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val value: Throwable) : RepositoryResult<T>()