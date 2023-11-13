package algorithms
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.random.Random
import Problem
import Solution

class Easom(override var dimensions: Int) : Problem() {
    override val name = "Easom"
    override val lowerBound: MutableList<Double>
    override val upperBound: MutableList<Double>

    init {
        dimensions = 2
        lowerBound = MutableList(dimensions) { -100.0 }
        upperBound = MutableList(dimensions) { 100.0 }
    }

    override fun fitness(candidate: Solution): Solution {
        candidate.fitnessValue = -cos(candidate.candidate[0]) * cos(candidate.candidate[1]) * exp(
            -(candidate.candidate[0] - Math.PI).pow(2.0) - (candidate.candidate[1] - Math.PI).pow(2.0)
        )
        return candidate
    }
}