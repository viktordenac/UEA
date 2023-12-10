package algorithms
import Algorithm
import Problem
import solution.ParticleSolution
import solution.Solution

class PSO(maxFes: Int) : Algorithm(maxFes) {
    var particles = mutableListOf<ParticleSolution>()
    var sizeOfPopulation = 20
    var w = 0.7 //omega -> inertia weight
    var c1 = 2.0 //c1 -> cognitive coefficient (acceleration constant)
    var c2 = 2.0 //c2 -> social coefficient (acceleration constant)

    init {
        repeat(sizeOfPopulation) {
            particles.add(ParticleSolution())
        }
    }

    override fun execute(problem: Problem): Solution {
        TODO("Not yet implemented")
    }
}