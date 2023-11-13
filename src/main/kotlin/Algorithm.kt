abstract class Algorithm {
    var stepSize=0.5;
    var maxFes=10000;

    constructor(stepSize:Double, maxFes:Int){
        this.stepSize=stepSize;
        this.maxFes=maxFes;
    }

    abstract fun execute(problem: Problem): Solution
}