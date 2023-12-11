package solution

class ParticleSolution : Solution() {
    var velocity: MutableList<Double> = MutableList(0) { 0.0 }
    var bestFitnessValue: Double = Double.MAX_VALUE
    var bestCandidate: MutableList<Double> = MutableList(0) { 0.0 }

    init {
        fitnessValue = Double.MAX_VALUE
    }

    override fun copy(): ParticleSolution {
        val copiedSolution = ParticleSolution()
        copiedSolution.candidate = candidate.toMutableList()
        copiedSolution.bestCandidate = bestCandidate.toMutableList()
        copiedSolution.velocity = velocity.toMutableList()
        copiedSolution.fitnessValue = fitnessValue
        copiedSolution.bestFitnessValue = bestFitnessValue
        return copiedSolution
    }

    fun getFitnessValues(): Double {
        return fitnessValue
    }

    fun getCandidates(): MutableList<Double> {
        return candidate.toMutableList()
    }

    fun getVelocityAt(index: Int): Double {
        return velocity[index]
    }

    fun getCandidateAt(index: Int): Double {
        return candidate[index]
    }

    fun print() {
        println("Candidate: $candidate")
        println("Fitness: $fitnessValue")
        println("Velocity: $velocity")
        println("Best Fitness: $bestFitnessValue")
        println("Best Candidate: $bestCandidate")
    }
}
