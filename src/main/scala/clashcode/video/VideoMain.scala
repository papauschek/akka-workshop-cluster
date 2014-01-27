package clashcode.video

import clashcode.video.swing.SwingDevice
import java.awt.Graphics2D
import clashcode.video.swing.AwtRectGraphicsSimple
import clashcode.video.swing.AwtGraphics

object VideoMain extends App {

  val cans = Set(
    Pos(1, 4),
    Pos(2, 5),
    Pos(3, 3),
    Pos(10, 1))

  val cans1 = cans - Pos(3, 3)

  val stages = List(
    Stage(Robot(Pos(0, 0), W), cans),
    Stage(Robot(Pos(0, 1), S), cans),
    Stage(Robot(Pos(1, 1), SW), cans),
    Stage(Robot(Pos(1, 3), S), cans1))

  val device = new SwingDevice(createGraphics)

  def createGraphics(g: Graphics2D): AwtGraphics = new AwtRectGraphicsSimple(0.8, 50, 30) {

    def graphics: Graphics2D = g
    def drawArea: DrawArea = device.drawArea

  }

  Video.play(device, stages, Max(20, 20), 2)

}