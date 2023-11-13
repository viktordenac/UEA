import kotlin.math.pow
import kotlin.random.Random

abstract class Problem {
    abstract val name: String
    abstract val lowerBound: MutableList<Double>
    abstract val upperBound: MutableList<Double>
    abstract val dimensions: Int
    abstract fun fitness(candidate: Solution): Solution

    fun randomSolution(): Solution {
        val solution = Solution()
        for (i in 0..<dimensions) {
            solution.candidate.add(Random.nextDouble(lowerBound[i], upperBound[i]))
        }
        return solution
    }

    fun neighbors(solution: Solution, stepSize: Double): MutableList<Solution> {
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
            neighbors.add(setFeasible(neighbor))
        }
        neighbors = neighbors.distinct().toMutableList()
        return neighbors
    }

    fun isFeasible(candidate: Solution): Boolean {
        for (i in 0..<candidate.candidate.size) {
            if (candidate.candidate[i] < lowerBound[i] || candidate.candidate[i] > upperBound[i]) {
                return false
            }
        }
        return true
    }

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
