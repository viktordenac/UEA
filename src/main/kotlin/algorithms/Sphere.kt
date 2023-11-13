package algorithms

import Problem
import Solution

class Sphere(override val dimensions: Int) : Problem() {
    override val name = "Sphere"
    override val lowerBound: MutableList<Double>
    override val upperBound: MutableList<Double>

    init {
        lowerBound = MutableList(dimensions) { -100.0 }
        upperBound = MutableList(dimensions) { 100.0 }
        for (i in 0 until dimensions) {
            if (i % 2 == 1) {
                lowerBound[i] = -10.0
                upperBound[i] = 10.0
            }
        }
    }

    override fun fitness(candidate: Solution): Solution {
        candidate.fitnessValue = candidate.candidate.sumOf { it * it }
        return candidate
    }
}
