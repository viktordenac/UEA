import solution.Solution
import kotlin.math.pow

class HillClimbing(var stepSize: Double = 0.5, maxFes: Int) : Algorithm(maxFes) {

    // Function to generate neighbors for a given solution
    fun neighbors(solution: Solution, stepSize: Double, problem: Problem): MutableList<Solution> {
        val neighbors = mutableListOf<Solution>()
        val dimensions = solution.candidate.size

        // Iterate through all possible neighbors
        for (i in 0 until 3.0.pow(dimensions.toDouble()).toInt()) {
            val neighbor = Solution()
            val ternary = i.toString(3).padStart(dimensions, '0')
            for (j in 0 until dimensions) {
                val dimensionIndex = ternary[j].toString().toInt() - 1
                neighbor.candidate.add(solution.candidate[j] + dimensionIndex * stepSize)
            }
            // Avoid duplicates
            if (neighbor.candidate != solution.candidate) {
                neighbors.add(problem.setFeasible(neighbor))
            }
        }
        neighbors.distinct()
        return neighbors
    }

    // Execute the Hill Climbing algorithm on the given problem
    override fun execute(problem: Problem): Solution {
        val solution = problem.randomSolution()
        var bestSolution = problem.fitness(solution)

        // List to store previously visited points
        val prevPoints = mutableListOf<Solution>()
        prevPoints.add(solution)

        var neighbors = neighbors(solution, stepSize, problem)
        var repetitions = 0

        var currentFes = 0

        // Hill Climbing loop
        while (currentFes <= maxFes - 1) {
            currentFes += 2 * problem.dimensions

            // If neighbors list is empty, generate new neighbors
            if (neighbors.isEmpty()) {
                neighbors = neighbors(bestSolution, stepSize, problem)
            }

            // Remove neighbors that have been visited before
            neighbors.removeAll { prevPoints.contains(it) }

            // If neighbors list is still empty, add a random solution
            if (neighbors.isEmpty()) {
                neighbors.add(problem.randomSolution())
            }

            // Select the first neighbor from the list
            var neighbor = neighbors.removeAt(0)
            neighbor = problem.fitness(neighbor)

            // Update best solution and repetitions count
            if (neighbor.fitnessValue == bestSolution.fitnessValue) {
                repetitions++
            } else if (neighbor.fitnessValue < bestSolution.fitnessValue) {
                bestSolution = neighbor
                repetitions = 0
            }
            prevPoints.add(neighbor)

            // If repetitions exceed a threshold, reset neighbors
            if (repetitions > 5) {
                repetitions = 0
                neighbors.clear()
                neighbors.add(problem.randomSolution())
            }
        }
        return bestSolution
    }
}
