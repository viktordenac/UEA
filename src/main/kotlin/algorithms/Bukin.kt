package algorithms
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.sqrt
import Problem
import Solution

class Bukin(override var dimensions: Int) : Problem() {
    override val name = "Bukin"
    override val lowerBound: MutableList<Double>
    override val upperBound: MutableList<Double>

    init {
        dimensions = 2
        lowerBound = MutableList(dimensions) { -3.0 }
        upperBound = MutableList(dimensions) { 3.0 }
        lowerBound[0] = -15.0
        upperBound[0] = -5.0
    }

    override fun fitness(candidate: Solution): Solution {
        candidate.fitnessValue = 100 * sqrt(abs(candidate.candidate[1] - 0.01 * pow(candidate.candidate[0], 2.0))) + 0.01 * abs(candidate.candidate[0] * 10)
        return candidate
    }
}
