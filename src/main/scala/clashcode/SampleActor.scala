package clashcode

import akka.actor._
import clashcode.robot._

class SampleActor(broadcast: ActorRef) extends Actor {

  def receive = {

     case robot: Robot =>
      println("I received a robot from someone! what should I do?")

  }

}


