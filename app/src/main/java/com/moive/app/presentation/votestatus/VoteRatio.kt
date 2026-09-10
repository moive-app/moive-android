package com.moive.app.presentation.votestatus

fun voteRatio(voterCount: Int?, totalVoterCount: Int?): Float {
    if (voterCount == null || totalVoterCount == null || totalVoterCount <= 0) return 0f
    return (voterCount.toFloat() / totalVoterCount.toFloat()).coerceIn(0f, 1f)
}
