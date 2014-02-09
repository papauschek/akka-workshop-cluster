package clashcode

import clashcode.robot.{RobotCode, Robot}
import scala.util.Random

/** A simple strategy for creating a new robot from an existing generation */
object SampleStrategy {

  /**
   *  Create a member for the next generation of robots.
   *
   *  To do so perform the following steps (for example)
   *  - Select two candidates to be the parents of the new robot
   *  - Create a new robot code combining the parents' codes (by applying crossover)
   *  - Apply mutation on the new robot code (optional)
   *
   *  creatorName: Tag the new robot with your name, to track its origins back to you
   *  currentRobots: The candidates from the current generation sorted by their fitness
   */
  def createNewCode(creatorName: String, currentRobots: Seq[Robot]): RobotCode = {

    // select parents
    val mother = currentRobots(Random.nextInt(currentRobots.size)).code
    val father = currentRobots(Random.nextInt(currentRobots.size)).code

    // crossover
    val motherCount = Random.nextInt(mother.code.length)
    val result = mother.code.take(motherCount) ++ father.code.drop(motherCount)

    // TODO: mutate
    RobotCode(result, creatorName, Seq(mother, father))
  }

}
