fun main() {
    val lines = {}.javaClass.getResource("1.txt").readText().lines()
    var result = 0

    for (i in lines.indices) {
        val num = lines[i].toInt()

        val newStart = i + 1
        for (j in (newStart..lines.size - 1)) {
            val num2 = lines[j].toInt()

            if ((num + num2) == 2020) {
                result = num * num2
                println(num)
                println(num2)
                break
            }
        }
    }

    println(result)
}

