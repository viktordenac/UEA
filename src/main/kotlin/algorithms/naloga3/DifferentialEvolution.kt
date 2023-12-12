import solution.Solution
import kotlin.random.Random

class DifferentialEvolution(maxFes: Int) : Algorithm(maxFes) {
    var agents = mutableListOf<Solution>()
    var population = 20 //NP >= 4
    var CR = 0.5        //€[0,1]
    var F = 0.6         //€[0,2]

    private fun generateDistinctRndNum(from: Int, to: Int): List<Int> {
        return (from..to).shuffled().take(3)
    }

    override fun execute(problem: Problem): Solution {
        repeat(population) {                            //Initialize agents
            agents.add(problem.randomSolution())
            agents[it] = problem.fitness(agents[it])
        }

        var j = 0
        while (j < maxFes - population) {                   //While criteria isn't met
            for (i in 0 until population - 1) {       //For each agent Xi
                var randomNumbers = generateDistinctRndNum(1, population - 1)
                val a = agents[randomNumbers[0]].copy()     //Pick random agents
                val b = agents[randomNumbers[1]].copy()
                val c = agents[randomNumbers[2]].copy()
                var y = agents[i].copy()

                val R = Random.nextInt(0, problem.dimensions - 1)   //Select random index R €{1..D]


                for (j in 0 until problem.dimensions - 1) {
                    if (Random.nextDouble() < CR || R == j) {
                        y.candidate[j] = a.candidate[j] + F * (b.candidate[j] - c.candidate[j])
                    }
                    y = problem.setFeasible(y)
                    y = problem.fitness(y)
                    if (y.fitnessValue < agents[i].fitnessValue) {
                        agents[i] = y.copy()                //Replace agent x with improved solution y
                    }
                }
            }
            j++
        }
        return agents.minBy { it.fitnessValue }
    }
}
