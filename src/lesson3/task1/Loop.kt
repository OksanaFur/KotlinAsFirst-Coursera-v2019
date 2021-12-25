@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var newNumber = n
    while (newNumber / 10 != 0) {
        count++
        newNumber /= 10
    }
    return count
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2) {
        return 1
    }
    var f1 = 1
    var f2 = 1
    for (i in 3..n) {
        val f3 = f1 + f2
        f1 = f2
        f2 = f3
    }
    return f2
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val a = if (m > n) m else n
    val b = if (m < n) m else n
    var k = a
    while (k % b != 0) {
        k += a
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var div = 2
    if (n % div == 0) return 2
    div = 3
    while (n % div != 0) {
        div += 2
    }
    return div
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var dev = n - 1
    while (n % dev != 0) {
        dev -= 1
    }
    return dev
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val a = if (m > n) m else n
    val b = if (m < n) m else n
    if (a == 1) {
        return true
    }
    if (a % b == 0) {
        return false
    }
    for (dev in 2..sqrt(b.toDouble()).toInt()) {
        if (a % dev == 0 && b % dev == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var preK = 1
    for (preK in 1..sqrt(n.toDouble()).toInt()) {
        if (preK * preK in m..n) return true
    }
    return false
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var a = x
    var count = 0
    while (a != 1) {
        if (a % 2 == 0) {
            a /= 2
        } else {
            a = a * 3 + 1
        }
        count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val x1 = x % (2 * PI)
    var sum = 0.0
    var add = x1
    var rank = 1
    var sign = 1
    while (abs(add) >= eps) {
        sum += add
        rank += 2
        sign *= -1
        add = sign * x1.pow(rank) / factorial(rank)
    }
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val x1 = x % (2 * PI)
    var sum = 0.0
    var add = 1.0
    var rank = 0
    var sign = 1
    while (abs(add) >= eps) {
        sum += add
        rank += 2
        sign *= -1
        add = sign * x1.pow(rank) / factorial(rank)
    }
    return sum

}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var i = digitNumber(n)
    var newN = 0.0
    var n1 = n
    for (j in i downTo 1) {
        newN += (n1 % 10) * 10.0.pow(j - 1)
        n1 /= 10
    }
    return newN.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var i = digitNumber(n)
    var newN = 0.0
    var n1 = n
    for (j in i downTo 1) {
        newN += (n1 % 10) * 10.0.pow(j - 1)
        n1 /= 10
    }
    return n == newN.toInt()
}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1 = n / 10
    val lastDigit = n % 10
    while (n1 > 0) {
        if (lastDigit != n1 % 10) {
            return true
        }
        n1 /= 10
    }
    return false
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var x = 1
    var kvadrat1 = 0
    var i = 0
    do {
        kvadrat1 = x * x
        i += digitNumber(kvadrat1)
        x++
    } while (i < n)
    var digitNumber = i - n + 1
    var findDigit = kvadrat1
    while (digitNumber > 1) {
        findDigit /= 10
        digitNumber--
    }
    return findDigit % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var x = 1
    var fibNumber = 0
    var i = 0
    do {
        fibNumber = fib(x)
        i += digitNumber(fibNumber)
        x++
    } while (i < n)
    var digitNumber = i - n + 1
    var findDigit = fibNumber
    while (digitNumber > 1) {
        findDigit /= 10
        digitNumber--
    }
    return findDigit % 10
}
