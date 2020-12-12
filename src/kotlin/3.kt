fun countTrees(map: List<String>, right: Int, down: Int): Int {
    var treeCount = 0;
    var idx = 0;
    map.forEachIndexed { i, row ->
        if (i % down == 0) {
            if (row[idx] == '#') treeCount++;
            idx = (idx + right) % row.length
        }
    }
    return treeCount
}

fun main() {
    val map = {}.javaClass.getResource("3.txt").readText().lines()
    val route1 = countTrees(map, 1, 1).toLong()
    val route2 = countTrees(map, 3, 1).toLong()
    val route3 = countTrees(map, 5, 1).toLong()
    val route4 = countTrees(map, 7, 1).toLong()
    val route5 = countTrees(map, 1, 2).toLong()
    println(route1 * route2 * route3 * route4 * route5)
}