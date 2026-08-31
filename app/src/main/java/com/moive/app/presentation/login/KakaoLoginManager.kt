package com.moive.app.presentation.login

import android.content.Context
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.moive.app.core.utils.suspendRunCatching
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class KakaoLoginManager {
    suspend fun loginKakao(
        context: Context,
    ): KakaoLoginResult =
        suspendRunCatching { getKakaoAccessToken(context) }
            .fold(
                onSuccess = { KakaoLoginResult.Success(it) },
                onFailure = { error -> KakaoLoginResult.Failure(resolveErrorMessage(error)) },
            )

    private suspend fun getKakaoAccessToken(
        context: Context,
    ): String =
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            try {
                loginWithKakaoTalk(context)
            } catch (cancellation: CancellationException) {
                throw cancellation
            } catch (error: Exception) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    throw error
                }
                loginWithKakaoAccount(context)
            }
        } else {
            loginWithKakaoAccount(context)
        }

    private suspend fun loginWithKakaoTalk(
        context: Context,
    ): String = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                continuation.resumeWithException(error)
                return@loginWithKakaoTalk
            } else if (token != null) {
                continuation.resume(token.accessToken)
            }
        }
    }

    private suspend fun loginWithKakaoAccount(
        context: Context,
    ): String = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                continuation.resumeWithException(error)
                return@loginWithKakaoAccount
            } else if (token != null) {
                continuation.resume(token.accessToken)
            }
        }
    }


    private fun resolveErrorMessage(error: Throwable): String = when {
        error is ClientError && error.reason == ClientErrorCause.Cancelled -> "로그인이 취소되었어요"
        error is IOException -> "네트워크 연결을 확인해주세요"
        else -> "로그인을 완료하지 못했어요. 다시 시도해주세요"
    }

    sealed interface KakaoLoginResult {
        data class Success(val token: String): KakaoLoginResult
        data class Failure(val error: String): KakaoLoginResult
    }
}

