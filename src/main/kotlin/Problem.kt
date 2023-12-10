import solution.Solution
import kotlin.math.pow
import kotlin.random.Random

// Abstract class representing a optimization problem
abstract class Problem {
    abstract val name: String // Name of the problem
    abstract val lowerBound: MutableList<Double> // Lower bounds for each dimension
    abstract val upperBound: MutableList<Double> // Upper bounds for each dimension
    abstract val dimensions: Int // Number of dimensions in the problem
    abstract fun fitness(candidate: Solution): Solution // Abstract method to calculate fitness of a solution

    // Generates a random solution within the specified bounds
    fun randomSolution(): Solution {
        val solution = Solution()
        for (i in 0 until dimensions) {
            solution.candidate.add(Random.nextDouble(lowerBound[i], upperBound[i]))
        }
        return solution
    }

    // Generates a list of neighboring solutions based on the given step size
    fun neighbors(solution: Solution, stepSize: Double): MutableList<Solution> {
        var neighbors = mutableListOf<Solution>()
        val dimensions = solution.candidate.size

        // Iterate over all possible neighbors using ternary representation
        for (i in 0 until 2.0.pow(dimensions.toDouble()).toInt()) {
            val neighbor = Solution()
            val ternary = i.toString(3).padStart(dimensions, '0')
            for (j in 0 until dimensions) {
                val dimensionIndex = ternary[j].toString().toInt() - 1
                neighbor.candidate.add(solution.candidate[j] + dimensionIndex * stepSize) //zahteva 2n sosedov
            }
            // Skip if the neighbor is the same as the original solution or if it is not feasible
            if (neighbor.candidate == solution.candidate || !isFeasible(neighbor)) continue
            neighbors.add(setFeasible(neighbor))
        }
        neighbors = neighbors.distinct().toMutableList() // Remove duplicates
        return neighbors
    }

    // Checks if a solution is within the feasible bounds
    fun isFeasible(candidate: Solution): Boolean {
        for (i in 0..<candidate.candidate.size) {
            if (candidate.candidate[i] < lowerBound[i] || candidate.candidate[i] > upperBound[i]) {
                return false
            }
        }
        return true
    }

    // Adjusts a solution to be within the feasible bounds
    fun setFeasible(candidate: Solution): Solution {
        for (i in 0..<candidate.candidate.size) {
            if (candidate.candidate[i] < lowerBound[i]) {
                candidate.candidate[i] = lowerBound[i]
            } else if (candidate.candidate[i] > upperBound[i]) {
                candidate.candidate[i] = upperBound[i]
            }
        }
        return candidate
    }

}
