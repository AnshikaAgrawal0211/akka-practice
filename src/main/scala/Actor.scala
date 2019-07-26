import akka.actor.{Actor, ActorSystem, Props}

class HelloAkka extends Actor {
  def receive = {
    case msg: String => println(msg)
    case _ => println("Unknown message")
  }
}

object Main {
  def main(args: Array[String]) {
    val actorSystem = ActorSystem("ActorSystem")
    val actor = actorSystem.actorOf(Props[HelloAkka], "HelloAkka")
    actor ! "Hello Akka" // Sending messages by using !
    actor ! 100.52
  }
}