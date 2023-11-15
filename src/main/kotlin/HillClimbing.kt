class HillClimbing(var stepSize : Double = 0.5, maxFes: Int) : Algorithm(maxFes) {

    override fun execute(problem: Problem): Solution {
        // Initialization
        var solution = problem.randomSolution()
        var currentFes = 0
        solution = problem.fitness(solution)

        var bestSolution = solution

        val prevPoints = MutableList(0) { Solution() }
        prevPoints.add(solution)

        var neighbors = problem.neighbors(solution, stepSize)
        var repetitions = 0

        // Main Loop
        while (currentFes <= maxFes-1){
            currentFes += 4

            // Replenish neighbors if the list is empty
            if (neighbors.isEmpty()) {
                neighbors = problem.neighbors(bestSolution, stepSize)
            }

            // Remove already visited neighbors
            while (neighbors.isNotEmpty() && prevPoints.contains(neighbors[0])) {
                neighbors.removeAt(0)
            }

            // If neighbors is still empty, add a random solution
            if (neighbors.isEmpty()) {
                neighbors.add(problem.randomSolution())
            }

            // Select a neighbor
            var neighbor = neighbors[0]
            neighbors.removeAt(0)
            neighbor = problem.fitness(neighbor)

            // Fitness Comparison and Update
            if (neighbor.fitnessValue == bestSolution.fitnessValue) {
                repetitions += 1
            } else if (neighbor.fitnessValue < bestSolution.fitnessValue) {
                bestSolution = neighbor
                repetitions = 0
            }

            // Add the neighbor to the list of visited points
            prevPoints.add(neighbor)
//TODO fix the logic of this and above the neigbor.fitness if statement
            // Repetition Check
            if (repetitions > 5) {
                repetitions = 0
                neighbors.clear()
                neighbors.add(problem.randomSolution())
            }
        }

        // Return the best solution found
        return bestSolution
    }
}
