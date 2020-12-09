import java.io.File

fun main() {
    val lines = {}.javaClass.getResource("1.txt").readText().lines()
    var result = 0

    for (i in lines.indices) {
        val a = lines[i].toInt()

        for (j in ((i + 1)..(lines.size - 1))) {
            val b = lines[j].toInt()

            for (k in ((j + 1)..(lines.size - 1))) {
                val c = lines[k].toInt()

                if ((a + b + c) == 2020) {
                    result = a * b * c
                    println(a)
                    println(b)
                    println(c)
                    break
                }
            }
        }
    }

    println(result)
}
