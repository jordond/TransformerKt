package dev.transformerkt.ktx.inputs

import androidx.annotation.CheckResult
import androidx.media3.transformer.TransformationRequest
import androidx.media3.transformer.Transformer
import dev.transformerkt.TransformerKt
import dev.transformerkt.internal.createTransformerCallbackFlow
import dev.transformerkt.ktx.start
import kotlinx.coroutines.flow.Flow
import java.io.File

/**
 * Create a [TransformerKt.Input] from this [File].
 */
public fun File.asTransformerInput(): TransformerKt.Input {
    return TransformerKt.Input.File(this)
}


/**
 * Start a [Transformer] request for a [File] and return a [Flow] of [TransformerKt.Status].
 *
 * @see createTransformerCallbackFlow
 * @param[input] The input to transform.
 * @param[output] The output file to write to.
 * @param[request] The [TransformationRequest] to use.
 * @param[progressPollDelayMs] The delay between polling for progress.
 * @return A [Flow] that emits [TransformerKt.Status].
 */
@CheckResult
public fun Transformer.start(
    input: File,
    output: File,
    request: TransformationRequest,
    progressPollDelayMs: Long = TransformerKt.DEFAULT_PROGRESS_POLL_DELAY_MS,
): Flow<TransformerKt.Status> = start(
    input = TransformerKt.Input.File(input),
    output = output,
    request = request,
    progressPollDelayMs = progressPollDelayMs,
)

/**
 * Start a [Transformer] request for a [File] in a coroutine and return
 * a [TransformerKt.Status.Finished] when the request is finished.
 *
 * For progress updates pass a [onProgress] callback.
 *
 * @see start
 * @param[input] The input to transform.
 * @param[output] The output file to write to.
 * @param[request] The [TransformationRequest] to use.
 * @param[progressPollDelayMs] The delay between polling for progress.
 * @param[onProgress] The callback to use for progress updates.
 * @return A [TransformerKt.Status.Finished] status.
 */
public suspend fun Transformer.start(
    input: File,
    output: File,
    request: TransformationRequest,
    progressPollDelayMs: Long = TransformerKt.DEFAULT_PROGRESS_POLL_DELAY_MS,
    onProgress: (Int) -> Unit = {},
): TransformerKt.Status.Finished = start(
    input = TransformerKt.Input.File(input),
    output = output,
    request = request,
    progressPollDelayMs = progressPollDelayMs,
    onProgress = onProgress,
)