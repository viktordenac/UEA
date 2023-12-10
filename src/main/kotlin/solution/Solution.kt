package solution

open class Solution {
    var candidate: MutableList<Double> = MutableList(0){0.0}
    var fitnessValue: Double = 0.0

    open fun copy(): Solution {
        var g = Solution()
        g.candidate = candidate.toMutableList()
        g.fitnessValue = fitnessValue
        return g
    }
}