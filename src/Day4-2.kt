fun main() {
    val rawData = readInput("day4")
    checkTestConditions()
    val result = solve(rawData)
    println("the solution is: $result")

}

private fun solve(rawData: List<String>): Int {
    val result = rawData.mapIndexed { row, line ->
        line.mapIndexed { col, char ->
            if (col > 0 && row > 0 && col < line.length - 1 && row < rawData.size - 1 && char == 'A') {
                var matches = 0
                if (rawData[row + 1][col + 1] != rawData[row - 1][col - 1]
                    && rawData[row + 1][col - 1] != rawData[row - 1][col + 1]) {
                    val chars = listOf(
                        rawData[row - 1][col - 1],
                        rawData[row - 1][col + 1],
                        rawData[row + 1][col + 1],
                        rawData[row + 1][col - 1],
                    )
                    if (chars.count { it == 'M' } == 2 && chars.count { it == 'S' } == 2) {
                        matches++
                    }
                }
                matches
            } else 0
        }.sum()
    }.sum()
    return result
}

private fun checkTestConditions() {

    check(
        1 == solve(
            listOf(
                "SAS",
                "MAM",
                "MMM",
            )
        )
    )
    check(
        1 == solve(
            listOf(
                "MAM",
                "MAM",
                "SMS",
            )
        )
    )
    check(
        1 == solve(
            listOf(
                "MAS",
                "MAM",
                "MMS",
            )
        )
    )
    check(
        0 == solve(
            listOf(
                "MAS",
                "MAM",
                "MMM",
            )
        )
    )
    check(
        9 == solve(
            listOf(
                ".M.S......",
                "..A..MSMS.",
                ".M.S.MAA..",
                "..A.ASMSM.",
                ".M.S.M....",
                "..........",
                "S.S.S.S.S.",
                ".A.A.A.A..",
                "M.M.M.M.M.",
                "..........",
            )
        )
    )
}
