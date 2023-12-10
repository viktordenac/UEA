package solution

class ParticleSolution : Solution() {
    var velocity: MutableList<Double> = MutableList(0) { 0.0 }
    var bestFitnessValue: Double = Double.MAX_VALUE
    var bestCandidate: MutableList<Double> = MutableList(0) { 0.0 }

    init {
        fitnessValue = Double.MAX_VALUE
    }

    fun print() {
        println("Candidate: $candidate")
        println("Fitness: $fitnessValue")
        println("Velocity: $velocity")
        println("Best Fitness: $bestFitnessValue")
        println("Best Candidate: $bestCandidate")
    }

    override fun copy(): ParticleSolution {
        var g = ParticleSolution()
        g.candidate = candidate.toMutableList()
        g.bestCandidate = bestCandidate.toMutableList()
        g.velocity = velocity.toMutableList()
        g.fitnessValue = fitnessValue
        g.bestFitnessValue = bestFitnessValue
        return g
    }

    fun returnFitnes(): Double {
        val neke = fitnessValue
        return neke
    }

    fun returnCandidate(): MutableList<Double> {
        return candidate.toMutableList()
    }

    fun returnVelocityAtI(i: Int): Double {
        val neke = velocity[i]
        return neke
    }

    fun returnCandidateAtI(i: Int): Double {
        val neke = candidate[i]
        return neke
    }
}
