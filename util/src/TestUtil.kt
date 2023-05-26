import org.junit.jupiter.api.assertTimeoutPreemptively
import kotlin.time.Duration
import kotlin.time.toJavaDuration

fun <R> runTimeout(limit: Duration, body: () -> R) = runTimeout(limit, { "" }, body)

fun <R> runTimeout(limit: Duration, message: String, body: () -> R) = runTimeout(limit, { message }, body)

fun <R> runTimeout(limit: Duration, message: () -> String, body: () -> R) =
    assertTimeoutPreemptively(limit.toJavaDuration(), message, body)

fun <R> runIfTimeout(withTimeout: Boolean, limit: Duration, message: String, body: () -> R) = when (withTimeout) {
    true -> runTimeout(limit, message, body)
    else -> body()
}

fun <R> runIfTimeout(withTimeout: Boolean, limit: Duration, body: () -> R) = when (withTimeout) {
    true -> runTimeout(limit, body)
    else -> body()
}
