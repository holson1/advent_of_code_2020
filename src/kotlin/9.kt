import java.math.BigInteger

fun getWeakNumber(lines: List<BigInteger>): Pair<Int, BigInteger> {
    val PREAMBLE = 25
    var result = Pair(0, BigInteger.ZERO)

    // loop over all the numbers to check
    for (i in PREAMBLE until lines.size) {
        val target: BigInteger = lines[i]
        var found = false

        for (j in i - PREAMBLE until i) {
            for (k in j + 1 until i) {
                found = lines[k] + lines[j] == target
                if (found) break
            }
            if (found) break
        }

        if (!found) {
            result = Pair(i, target)
            break
        }
    }
    return result
}

fun getMinMaxForRange(range: List<BigInteger>): Pair<BigInteger, BigInteger> {
    var min = range.first()
    var max = range.first()

    for (num in range) {
       min = if (num < min) num else min
       max = if (num > max) num else max
    }

    return Pair(min, max)
}

fun exploitWeakNum(lines: List<BigInteger>, weakNumIndex: Int, weakNum: BigInteger): BigInteger {
    var result = BigInteger.ZERO

    // caterpillar mode
    for (leftBound in 0 until weakNumIndex) {
        var rightBound = leftBound + 1
        var total = lines[leftBound]

        while (total < weakNum) {
            total += lines[rightBound]
            if (total == weakNum) {
                val (min, max) = getMinMaxForRange(lines.subList(leftBound, rightBound))
                result = min + max
                break
            }
            rightBound++
        }
    }
    return result
}


fun main() {
    val lines = {}.javaClass.getResource("9.txt").readText().lines().map() { it.toBigInteger() }
    val (weakNumIndex, weakNum) = getWeakNumber(lines)
    println("weakNum: ${weakNum}")
    val finalResult = exploitWeakNum(lines, weakNumIndex, weakNum)
    println("finalResult: ${finalResult}")
}