import kotlin.math.pow

fun applyBitMaskToValue(value: Long, mask: String): Long {
    val bits = java.lang.Long.toBinaryString(value).padStart(36, '0').toCharArray()

    val newBits = bits.mapIndexed() { i, c ->
        if (mask[i] != 'X') mask[i] else c
    }

    return java.lang.Long.parseLong(newBits.joinToString(""), 2)
}

fun getMaskedValues(lines: List<String>, fn: (key: Long, value: Long, mask: String) -> Map<Long, Long>): Long {
    var mask = "X".repeat(36)
    val memMap = mutableMapOf<Long, Long>()

    for (line in lines) {
        val (action, value) = line.split(" = ")
        if (action == "mask") {
            mask = value
        } else {
            val key = action.removeSurrounding("mem[", "]").toLong()
            val newMap = fn(key, value.toLong(), mask)
            memMap.putAll(newMap)
        }
    }

    return memMap.values.sum();
}

fun createMemMap1(key: Long, value: Long, mask: String): Map<Long, Long> {
    val newVal = applyBitMaskToValue(value, mask)
    return mapOf(key to newVal)
}

fun createMemMap2(key: Long, value: Long, mask: String): Map<Long, Long> {
    val bits = java.lang.Long.toBinaryString(key).padStart(36, '0').toCharArray()

    val xIndices = mask.mapIndexed() { i, c ->
        when (c) {
            'X' -> i
            else -> -1
        }
    }.filter { it != -1 }

    val newBits = mask.mapIndexed() { i, c ->
        when (c) {
            '0' -> bits[i]
            '1' -> '1'
            'X' -> 'X'
            else -> ' '
        }
    }.toMutableList()

    val totalKeys = (2.0).pow(xIndices.size).toInt()

    val keys = (0..totalKeys).map {
        val binaryRepr = Integer.toBinaryString(it).padStart(xIndices.size, '0').toCharArray()
        xIndices.forEachIndexed() { i, xi ->
            newBits[xi] = binaryRepr[i]
        }
        Pair(java.lang.Long.parseLong(newBits.joinToString(""), 2), value)
    }.toTypedArray()

    return mapOf(*keys)
}


fun main() {
    val lines = {}.javaClass.getResource("14.txt").readText().lines()
    val total = getMaskedValues(lines, ::createMemMap1)
    println(total)
    val total2 = getMaskedValues(lines, ::createMemMap2)
    println(total2)
}