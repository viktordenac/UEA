class hillClimbing(steps: Double, maxFes: Int) : Algorithm(steps, maxFes) {
    override fun execute(problem: Problem): Solution {
        var solution = problem.randomSolution()
        solution = problem.fitness(solution)
        var bestSolution = solution
        var prevPoints = MutableList(0) { Solution() }
        prevPoints.add(solution)
        var neighbors = problem.neighbors(solution, stepSize)
        var repetitions = 0
        repeat(maxFes - 1) {
            if (neighbors.isEmpty()) {
                neighbors = problem.neighbors(bestSolution, stepSize)
            }
            while (neighbors.isNotEmpty() && prevPoints.contains(neighbors[0])) {
                neighbors.removeAt(0)
            }

            if (neighbors.isEmpty()) {
                neighbors.add(problem.randomSolution())
            }

            var neighbor = neighbors[0]
            neighbors.removeAt(0)
            neighbor = problem.fitness(neighbor)

            if (neighbor.fitnessValue == bestSolution.fitnessValue) {
                repetitions += 1
            } else if (neighbor.fitnessValue < bestSolution.fitnessValue) {
                bestSolution = neighbor
                repetitions = 0
            }
            prevPoints.add(neighbor)

            if (repetitions > 5) {
                repetitions = 0
                neighbors.clear()
                neighbors.add(problem.randomSolution())
            }
        }
        return bestSolution
    }
}