import kotlin.random.Random

import algorithms.Sphere
import algorithms.Ackley
import algorithms.Schwefel26
import algorithms.Rosenbrock
import algorithms.Bukin
import algorithms.CarromTable
import algorithms.Easom
import algorithms.Trid

fun main(args: Array<String>) {
    val dimensions = 2
    val problems = listOf(
        Sphere(dimensions),
        Ackley(dimensions),
        Schwefel26(dimensions),
        Rosenbrock(dimensions),
        Bukin(2),
        CarromTable(dimensions),
        Easom(dimensions),
        Trid(dimensions)
    )
    var stats = Statistics()
    val algorithm = hillClimbing(0.5, 10000)

    for (problem in problems) {
        println(problem.name)
        for (i in 0..100) {
            val solution = algorithm.execute(problem)
            stats.add(problem.name, solution.fitnessValue)
            //println("${problem.name} ${solution.fitnessValue}")
        }
    }
    stats.printStatistics()
}
