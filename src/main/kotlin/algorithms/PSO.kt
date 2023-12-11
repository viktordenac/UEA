package algorithms
import Algorithm
import Problem
import solution.ParticleSolution
import kotlin.random.Random

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

    override fun execute(problem: Problem): ParticleSolution {
        var g = ParticleSolution()

        repeat(sizeOfPopulation) { i ->
            particles[i].candidate = problem.randomSolution().candidate
            particles[i].bestCandidate = particles[i].getCandidates()
            particles[i].velocity =
                MutableList(problem.dimensions) { Random.nextDouble(problem.lowerBound[it], problem.upperBound[it]) }

            particles[i].fitnessValue = problem.fitness(particles[i]).fitnessValue
            if (particles[i].fitnessValue < g.bestFitnessValue) {
                g = particles[i].copy()
            }
        }

        repeat(maxFes - (sizeOfPopulation)) {
            repeat(sizeOfPopulation ) { i ->
                repeat(problem.dimensions ) {
                    var r1 = Random.nextDouble()
                    var r2 = Random.nextDouble()
                    particles[i].velocity[it] =
                        w * particles[i].velocity[it] + c1 * r1 * (particles[i].bestCandidate[it] - particles[i].candidate[it]) + c2 * r2 * (g.bestCandidate[it] - particles[i].candidate[it])
                }
                repeat(problem.dimensions - 1) {
                    particles[i].candidate[it] =
                        particles[i].getCandidateAt(it) + particles[i].getVelocityAt(it)
                }
                particles[i].candidate = problem.setFeasible(particles[i]).candidate

                particles[i].fitnessValue = problem.fitness(particles[i]).fitnessValue
                if (particles[i].fitnessValue < particles[i].fitnessValue) {
                    particles[i].bestCandidate = particles[i].getCandidates()
                }

                if (particles[i].fitnessValue < g.bestFitnessValue) {
                    g.bestFitnessValue = particles[i].getFitnessValues()
                    g.bestCandidate = particles[i].getCandidates()
                }
            }
        }

        return g
    }
}