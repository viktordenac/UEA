package solution

open class Solution {
    var candidate: MutableList<Double> = MutableList(0) { 0.0 }
    var fitnessValue: Double = 0.0

    open fun copy(): Solution {
        val copiedSolution = Solution()
        copiedSolution.candidate = candidate.toMutableList()
        copiedSolution.fitnessValue = fitnessValue
        return copiedSolution
    }
}