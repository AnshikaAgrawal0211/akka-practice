import akka.actor._
class CreatingActor extends Actor{
  def receive = {
    case msg:String => println(msg+" "+self.path.name)
  }
}

object CreatingActorExample{
  def main(args:Array[String]){
    val actorSystem = ActorSystem("ActorSystem")
    val props1 = Props[CreatingActor];
    val actor1 = actorSystem.actorOf(props1);
    val actor2 = actorSystem.actorOf(Props[CreatingActor],"Actor2")
    val actor3 = actorSystem.actorOf(Props(classOf[CreatingActor]),"Actor3")
    val actor4 = actorSystem.actorOf(Props[CreatingActor], name = "Actor4")
    val actor5 = actorSystem.actorOf(Props(new CreatingActor()), name = "Actor5")
    actor1 ! "Hello"
    actor2 ! "Hi"
    actor3 ! "Heya"
    actor4 ! "Hola"
    actor5 ! "Hie"
  }
}