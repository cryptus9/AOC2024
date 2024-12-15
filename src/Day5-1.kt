fun main() {
    val rawRules = readInput("day5-rules")
    val rawUpdates = readInput("day5-updates")
    runTests()
    val rules = rawRules.map { mapRule(it) }
    val updates = rawUpdates.map { mapUpdate(it) }

    val result = solve(rules, updates)
    println("Solution for part one is: $result")
}

fun solve(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
    return updates.sumOf { update ->
        val isUpdateValid = rules.filter { update.containsAll(it.toList()) }
            .none { !isUpdateValidForRule(it, update) }
        if (isUpdateValid) {
            update[update.size / 2]
        } else {
            0
        }
    }
}

fun isUpdateValidForRule(rule: Pair<Int, Int>, update: List<Int>): Boolean {
    return update.indexOf(rule.first) == -1
            || update.indexOf(rule.second) == -1
            || update.indexOf(rule.first) < update.indexOf(rule.second)
}

fun mapRule(input: String): Pair<Int, Int> {
    val ruleVariables = input.split("|").map { it.toInt() }
    return Pair(ruleVariables[0], ruleVariables[1])
}

fun mapUpdate(input: String): List<Int> {
    return input.split(",").map { it.toInt() }
}


fun runTests() {
    check(isUpdateValidForRule(Pair(3, 4), listOf(2, 3, 4)))
    check(isUpdateValidForRule(Pair(2, 4), listOf(2, 3, 4)))
    check(isUpdateValidForRule(Pair(2, 12), listOf(2, 3, 4)))
    check(isUpdateValidForRule(Pair(133, 2), listOf(2, 3, 4)))
    check(!isUpdateValidForRule(Pair(2, 4), listOf(3, 4, 2)))

    check(Pair(2, 3) == mapRule("2|3"))
    check(listOf(2, 5, 7, 45) == mapUpdate("2,5,7,45"))

    val rules = listOf(
        Pair(47, 53),
        Pair(97, 13),
        Pair(97, 61),
        Pair(97, 47),
        Pair(75, 29),
        Pair(61, 13),
        Pair(75, 53),
        Pair(29, 13),
        Pair(97, 29),
        Pair(53, 29),
        Pair(61, 53),
        Pair(97, 53),
        Pair(61, 29),
        Pair(47, 13),
        Pair(75, 47),
        Pair(97, 75),
        Pair(47, 61),
        Pair(75, 61),
        Pair(47, 29),
        Pair(75, 13),
        Pair(53, 13),
    )
    val updates = listOf(
        listOf(75, 47, 61, 53, 29),
        listOf(97, 61, 53, 29, 13),
        listOf(75, 29, 13),
        listOf(75, 97, 47, 61, 53),
        listOf(61, 13, 29),
        listOf(97, 13, 75, 29, 47),
    )
    check(143 == solve(rules, updates))
}