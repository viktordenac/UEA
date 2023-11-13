package algorithms
import Problem
import Solution
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sqrt


class Ackley(override val dimensions: Int) : Problem() {
    override val name = "Ackley"
    override val lowerBound: MutableList<Double> = MutableList(dimensions) { -32.768 }
    override val upperBound: MutableList<Double> = MutableList(dimensions) { 32.768 }

    override fun fitness(candidate: Solution): Solution {

        val term1 = -20 * exp(
            -0.2 * sqrt((1.0 / dimensions) * candidate.candidate.sumOf { it * it })
        )
        val term2 = exp(
            (1.0 / dimensions) * candidate.candidate.sumOf { cos(2 * PI * it) }
        )
        candidate.fitnessValue = term1 - term2 + 20 + exp(1.0)
        return candidate
    }
}

