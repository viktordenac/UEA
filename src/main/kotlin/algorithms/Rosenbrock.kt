package algorithms
import java.lang.Math.pow
import Problem
import solution.Solution

class Rosenbrock(override val dimensions: Int) : Problem() {
    override val name = "Rosenbrock"
    override val lowerBound: MutableList<Double>
    override val upperBound: MutableList<Double>

    init {
        lowerBound = MutableList(dimensions) { -100.0 }
        upperBound = MutableList(dimensions) { 100.0 }
        for (i in 0 until dimensions) {
            lowerBound[i] = -5.0
            upperBound[i] = 10.0
        }
    }

    override fun fitness(candidate: Solution): Solution {
        for (i in 0..candidate.candidate.count() - 2) {
            candidate.fitnessValue += 100 * pow((candidate.candidate[i + 1] - pow(candidate.candidate[i], 2.0)), 2.0) + pow((candidate.candidate[i] - 1), 2.0)
        }
        return candidate
    }
}

