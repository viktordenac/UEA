package algorithms.naloga3

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
        var i = 0
        while (i < sizeOfPopulation) {
            particles.add(ParticleSolution())
            i++
        }
    }

    override fun execute(problem: Problem): ParticleSolution {
        var g = ParticleSolution()

        //Define the number of particles
        var i = 0
        while (i < sizeOfPopulation) {
            particles[i].candidate = problem.randomSolution().candidate     //Init particle position
            particles[i].bestCandidate = particles[i].getCandidates()       //Init particle best position Pi <- Xi
            particles[i].velocity =
                MutableList(problem.dimensions) { Random.nextDouble(problem.lowerBound[it], problem.upperBound[it]) }

            particles[i].fitnessValue = problem.fitness(particles[i]).fitnessValue      //Evaluate the solution Xi denoting as f(Pi)
            if (particles[i].fitnessValue < g.bestFitnessValue) {
                g = particles[i].copy()                                     //Update global best position: g
            }
            i++
        }

        var j = 0
        while (j < maxFes - sizeOfPopulation) {                             //While termination criteria is not met
            for (i in 0 until sizeOfPopulation) {                     //For each particle Xi
                for (k in 0 until problem.dimensions) {
                    val r1 = Random.nextDouble()
                    val r2 = Random.nextDouble()
                    particles[i].velocity[k] =
                        w * particles[i].velocity[k] +                      //Update particle's velocity
                                c1 * r1 * (particles[i].bestCandidate[k] - particles[i].candidate[k]) +
                                c2 * r2 * (g.bestCandidate[k] - particles[i].candidate[k])
                }

                for (k in 0 until problem.dimensions - 1) {          //Update particle's position Xi=Xi+Vi
                    particles[i].candidate[k] =
                        particles[i].getCandidateAt(k) + particles[i].getVelocityAt(k)
                }

                particles[i].candidate = problem.setFeasible(particles[i]).candidate    //If isn't feasible set it

                particles[i].fitnessValue = problem.fitness(particles[i]).fitnessValue  //Fitness value

                if (particles[i].fitnessValue < particles[i].bestFitnessValue) {        //If f(Xi) < f(Pi)
                    particles[i].bestCandidate = particles[i].getCandidates()           //Update particle's best position
                }

                if (particles[i].fitnessValue < g.bestFitnessValue) {                   //If f(Pi) < f(g)
                    g.bestFitnessValue = particles[i].getFitnessValues()                //Update global best
                    g.bestCandidate = particles[i].getCandidates()
                }
            }
            j++
        }

        return g
    }
}