fun main() {
    val rawData = readInput("day4")
    checkTestConditions()
    val result = solve(rawData)
    println("the solution is: $result")

}

private fun solve(rawData: List<String>): Int {
    val result = rawData.mapIndexed { row, line ->
        line.mapIndexed { col, char ->
            if (char.toString() == "X") {
                var matches = 0;
                if (col <= line.length - 4) {
                    val rest = line.substring(col + 1, col + 4)
                    if (rest.matches(Regex("MAS"))) {
                        matches++
                    }
                }
                if (col >= 4) {
                    val prev = line.substring(col - 3, col)
                    if (prev.matches(Regex("SAM"))) {
                        matches++
                    }
                }
                if (row > 2) {
                    val up = rawData[row - 1][col].toString() + rawData[row - 2][col] + rawData[row - 3][col]
                    if (up.matches(Regex("MAS"))) {
                        matches++
                    }
                }
                if (row < rawData.size - 3) {
                    val down = rawData[row + 1][col].toString() + rawData[row + 2][col] + rawData[row + 3][col]
                    if (down.matches(Regex("MAS"))) {
                        matches++
                    }
                }
                val diagonals = mutableListOf<String>()
                if (row < rawData.size - 3) {
                    if (col <= line.length - 4) {
                        diagonals.add(rawData[row + 1][col + 1].toString() + rawData[row + 2][col + 2] + rawData[row + 3][col + 3])
                    }
                    if (col >= 4) {
                        diagonals.add(rawData[row + 1][col - 1].toString() + rawData[row + 2][col - 2] + rawData[row + 3][col - 3])
                    }
                }
                if (row > 2) {
                    if (col <= line.length - 4) {
                        diagonals.add(rawData[row - 1][col + 1].toString() + rawData[row - 2][col + 2] + rawData[row - 3][col + 3])
                    }
                    if (col >= 4) {
                        diagonals.add(rawData[row - 1][col - 1].toString() + rawData[row - 2][col - 2] + rawData[row - 3][col - 3])
                    }
                }
                matches += diagonals.count { it.matches(Regex("MAS")) }
                matches
            } else 0
        }.sum()
    }.sum()
    return result
}

private fun checkTestConditions() {
    check(2 == solve(listOf("ABCSDFXGGGDGXMASFSDFXMASSDF")))
    check(3 == solve(listOf("ABCSAMXSDFXGGGDGXMASFSDFXMASSDF")))
    check(4 == solve(listOf("ABCSAMXMASSDFXGGGDGXMASFSDFXMASSDF")))
    check(5 == solve(listOf("ABCSAMXMASSDFXGGGDGXMASAMXFSDFXMASSDF")))
    check(
        1 == solve(
            listOf(
                "ADXSA",
                "MBSAM",
                "ABFMX",
                "XBGXS",
            )
        )
    )
    check(
        1 == solve(
            listOf(
                "ADXDA",
                "MBMAM",
                "ABAMX",
                "XBSXS",
            )
        )
    )
    check(
        2 == solve(
            listOf(
                "XDXSAA",
                "MMAAMA",
                "AMAMXA",
                "XBDSSA",
            )
        )
    )
    check(
        18 == solve(

            listOf(
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX",
            )
        )
    )
}
