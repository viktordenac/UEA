package algorithms
import java.lang.Math.pow
import Problem
import solution.Solution

class Trid(override val dimensions: Int) : Problem() {
    override val name = "Trid"
    override val lowerBound: MutableList<Double>
    override val upperBound: MutableList<Double>

    init {
        lowerBound = MutableList(dimensions) { dimensions * dimensions * -1.0 }
        upperBound = MutableList(dimensions) { dimensions * dimensions * 1.0 }
    }

    override fun fitness(candidate: Solution): Solution {
        val result = candidate.candidate.sumOf { pow(it - 1, 2.0) }
        var res = 0.0
        for (i in 1..<candidate.candidate.count()) {
            res += candidate.candidate[i] * candidate.candidate[i - 1]
        }
        candidate.fitnessValue = result - res
        return candidate
    }
}