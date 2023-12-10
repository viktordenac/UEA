import solution.Solution
import kotlin.math.pow

class HillClimbing(var stepSize: Double = 0.5, maxFes: Int) : Algorithm(maxFes) {

    fun neighbors(solution: Solution, stepSize: Double, problem: Problem): MutableList<Solution> {
        var neighbors = mutableListOf<Solution>()
        val dimensions = solution.candidate.size

        for (i in 0..<3.0.pow(dimensions.toDouble()).toInt()) {
            val neighbor = Solution()
            var ternary = i.toString(3).padStart(dimensions, '0')
            for (j in 0..<dimensions) {
                val dimensionIndex = ternary[j].toString().toInt() - 1
                neighbor.candidate.add(solution.candidate[j] + dimensionIndex * stepSize)
            }
            if (neighbor.candidate == solution.candidate) continue
            neighbors.add(problem.setFeasible(neighbor))
        }
        neighbors = neighbors.distinct().toMutableList()
        return neighbors
    }

    override fun execute(problem: Problem): Solution {
        var solution = problem.randomSolution()
        solution = problem.fitness(solution)
        var bestSolution = solution
        var prevPoints = MutableList(0) { Solution() }
        prevPoints.add(solution)
        var neighbors = neighbors(solution, stepSize, problem)
        var repetitions = 0

        var currentFes = 0

        while (currentFes <= maxFes - 1) {
            currentFes += 4

            if (neighbors.isEmpty()) {
                neighbors = neighbors(bestSolution, stepSize, problem)
            }
            while (neighbors.isNotEmpty() && prevPoints.contains(neighbors[0])) {
                neighbors.removeAt(0)
            }

            if (neighbors.isEmpty()) {
                neighbors.add(problem.randomSolution())
            }

            var neighbor = neighbors[0]
            neighbors.removeAt(0)
            neighbor = problem.fitness(neighbor)

            if (neighbor.fitnessValue == bestSolution.fitnessValue) {
                repetitions += 1
            } else if (neighbor.fitnessValue < bestSolution.fitnessValue) {
                bestSolution = neighbor
                repetitions = 0
            }
            prevPoints.add(neighbor)

            if (repetitions > 5) {
                repetitions = 0
                neighbors.clear()
                neighbors.add(problem.randomSolution())
            }
        }
        return bestSolution
    }
}
