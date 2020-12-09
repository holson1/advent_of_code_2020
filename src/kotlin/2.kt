fun validateByCount(password: String, requiredChar: Char, min: Int, max: Int): Boolean {
    val count = password.count { it.equals(requiredChar) }

    if (count >= min && count <= max) {
        return true
    }
    return false
}


fun validateByPosition(password: String, targetChar: Char, firstPos: Int, secondPos: Int): Boolean {
    return(password.elementAt(firstPos - 1) == targetChar).xor(password.elementAt(secondPos - 1) == targetChar)
}


fun validatePasswords(passwordRows: List<String>, method: String = "old"): Int {
    var valid = 0

    for (line in passwordRows) {
        val parts = line.split("-", " ", ": ")
        val numOne = parts[0].toInt()
        val numTwo = parts[1].toInt()
        val targetChar = parts[2].first()
        val password = parts[3]

        val validationResult = if (method == "old") {
            validateByCount(password, requiredChar = targetChar, min = numOne, max = numTwo)
        }
        else {
            validateByPosition(password, targetChar = targetChar, firstPos = numOne, secondPos = numTwo)
        }

        if (validationResult) valid += 1
    }

    return valid
}

fun main() {
    val lines = {}.javaClass.getResource("2.txt").readText().lines()
    println("Answer to part 1: ${validatePasswords(lines)}")
    println("Answer to part 2: ${validatePasswords(lines, method = "new")}")
}