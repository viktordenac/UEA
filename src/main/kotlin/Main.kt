import algorithms.Sphere
import algorithms.Ackley
import algorithms.Schwefel26
import algorithms.Rosenbrock
import algorithms.Bukin
import algorithms.CarromTable
import algorithms.Easom
import algorithms.Trid
import algorithms.naloga3.PSO
import java.io.File

fun main() {
    var dimensions = 10
    var file = File("DE.txt")
    //var algorithm = DifferentialEvolution(20000)
    file.appendText("DE Dim = 10 && maxFes = 20'000\n")


    var problems = listOf(
        Ackley(dimensions),
        Bukin(2),
        CarromTable(2),
        Easom(2),
        Rosenbrock(dimensions),
        Schwefel26(dimensions),
        Sphere(dimensions),
        Trid(dimensions)
    )
    var stats = Statistics()

    /*for (i in 0..2) {
        if (i == 1) {
            file.appendText("DE Dim = 20 && maxFes = 50'000\n")
            dimensions = 20
            algorithm = DifferentialEvolution(50000)

            problems = listOf(
                Ackley(dimensions),
                Bukin(2),
                CarromTable(2),
                Easom(2),
                Rosenbrock(dimensions),
                Schwefel26(dimensions),
                Sphere(dimensions),
                Trid(dimensions)
            )
            stats = Statistics()
        } else if (i == 2) {
            file.appendText("DE Dim = 30 && maxFes = 100'000\n")
            dimensions = 30
            algorithm = DifferentialEvolution(100000)

            problems = listOf(
                Ackley(dimensions),
                Bukin(2),
                CarromTable(2),
                Easom(2),
                Rosenbrock(dimensions),
                Schwefel26(dimensions),
                Sphere(dimensions),
                Trid(dimensions)
            )
            stats = Statistics()
        }
        problems.forEach { problem ->
            file.appendText("${problem.name}\n")
            println(problem.name)

            repeat(50) {
                val solution = algorithm.execute(problem)
                stats.add(problem.name, solution.fitnessValue)
                file.appendText("${solution.fitnessValue}\n")
            }

            file.appendText(stats.printStatisticsForProblem(problem.name))
        }
        stats.printStatistics()
    }*/

    /*val dimensions = 2
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
    statistics.printStatistics()*/

    var problem = Sphere(2)
    val algorithm = PSO(100000)
    val solution = algorithm.execute(problem)
    println(solution.bestFitnessValue)

    /*problem = Sphere(2)
    val tmp = HillClimbing(100000)
    val sol=tmp.execute(problem)
    println(sol.fitnessValue)*/

    problem = Sphere(2)
    val tmp2 = DifferentialEvolution(100000)
    val sol2=tmp2.execute(problem)
    println(sol2.fitnessValue)
    println(sol2.candidate)
}
