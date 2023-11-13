package algorithms
import java.lang.Math.cos
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
import Problem
import Solution

class CarromTable(override var dimensions: Int) : Problem() {
    override val name = "CarromTable"
    override val lowerBound: MutableList<Double>
    override val upperBound: MutableList<Double>

    init {
        dimensions = 2
        lowerBound = MutableList(dimensions) { -10.0 }
        upperBound = MutableList(dimensions) { 10.0 }
    }

    override fun fitness(candidate: Solution): Solution {
        val firstTerm = -(1 / 30.0) * exp(
            2 * abs(1.0 - (sqrt(candidate.candidate[0] * candidate.candidate[0] + candidate.candidate[1] * candidate.candidate[1]) / Math.PI))
        )
        val secondTerm = cos(candidate.candidate[0]).pow(2.0) * cos(candidate.candidate[1]).pow(2.0)
        candidate.fitnessValue = firstTerm * secondTerm

        return candidate
    }

}
