import algorithms.Sphere
import algorithms.Ackley
import algorithms.Schwefel26
import algorithms.Rosenbrock
import algorithms.Bukin
import algorithms.CarromTable
import algorithms.Easom
import algorithms.Trid

fun main() {
    val dimensions = 2
    val statistics = Statistics()
    val algorithm = HillClimbing( 0.5 ,10000)

    val problems = listOf(
        Ackley(dimensions),
        Bukin(2),
        CarromTable(dimensions),
        Easom(dimensions),
        Rosenbrock(dimensions),
        Schwefel26(dimensions),
        Sphere(dimensions),
        Trid(dimensions)
    )

    for (case in problems) {
        println(case.name)
        for (i in 0..100) {
            val solution = algorithm.execute(case)
            statistics.add(case.name, solution.fitnessValue)
        }
    }
    statistics.printStatistics()
}
