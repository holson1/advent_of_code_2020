import java.math.BigInteger

fun calculateJoltDiffs(joltages: List<Int>): MutableMap<Int, Int> {
    val diffs = mutableMapOf<Int, Int>(1 to 0, 2 to 0, 3 to 1)
    var prev = 0
    joltages.sorted().forEach() { it ->
        val d = it - prev
        if (d > 3) {
           return diffs
        } else {
            diffs[d] = diffs[d]!!.plus(1)
            prev = it
        }
    }
    return diffs
}

fun calcPermutations(joltages: MutableList<Int>): BigInteger {
    val numPathsMap = mutableMapOf<Int, BigInteger>(0 to BigInteger.ONE)
    joltages += 0 // needed to get the final result
    joltages.sortedDescending().forEach() {
        var pathsForVal = BigInteger.ZERO
        for (i in it+1..it+3) {
            if (numPathsMap.containsKey(i)) pathsForVal += numPathsMap[i]!!
        }
        numPathsMap[it] = maxOf(pathsForVal, BigInteger.ONE)
    }
    return numPathsMap[0]!!
}


fun main() {
    val joltages = {}.javaClass.getResource("10.txt").readText().lines().map() { it.toInt() }
    val diffs = calculateJoltDiffs(joltages)
    println(diffs)
    println("diffs_1 * diffs_3 = ${diffs[1]!! * diffs[3]!!}")
    val paths = calcPermutations(joltages.toMutableList())
    println("${paths} different permutations possible!")
}