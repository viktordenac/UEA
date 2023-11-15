abstract class Algorithm(var maxFes: Int = 10000) {

    abstract fun execute(problem: Problem): Solution
}