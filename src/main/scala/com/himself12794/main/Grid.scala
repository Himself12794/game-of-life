package com.himself12794.main

import java.awt.{Graphics2D,Color,Dimension}
import scala.swing._
import scala.collection.mutable.HashMap
import javax.swing.Timer
import java.awt.event.ActionListener

class DataPanel(var data: Array[Array[Color]]) extends Panel {

  override def paintComponent(g: Graphics2D) {
    
    val dx = g.getClipBounds.width.toFloat  / data.length
    val dy = g.getClipBounds.height.toFloat / data.map(_.length).max
    for {
      x <- 0 until data.length
      y <- 0 until data(x).length
      x1 = (x * dx).toInt
      y1 = (y * dy).toInt
      x2 = ((x + 1) * dx).toInt
      y2 = ((y + 1) * dy).toInt
    } {
      data(x)(y) match {
        case c: Color => g.setColor(c)
        case _ => g.setColor(Color.WHITE)
      }
      g.fillRect(x1, y1, x2 - x1, y2 - y1)
    }
  }
}

object Grid extends SimpleSwingApplication {
  
  val mapping = new HashMap[Char,Color]
  
  private val ggg = """|                                      
                       |
                        """
  
  private val t2 = """|                          X
                      |                        X X           
                      |             XX      XX            XX 
                      |            X   X    XX            XX 
                      | XX        X     X   XX                  
                      | XX        X   X XX     X X              
                      |           X     X        X              
                      |            X   X                        
                      |             XX                          
                      |                                         
                      |"""
  
  private val gl = new GameLife2(t2)
  
  
  mapping.put(gl.living, Color.BLACK)
  mapping.put(gl.dead, Color.WHITE)
  
  def getAsArray = {
    
    val str = Array.ofDim[Color](gl.width,gl.height)
    gl.getCells foreach { cell => { str(cell._1._1)(cell._1._2) = mapping(cell._2) } }
    str
  }
  
  val pan = new DataPanel(getAsArray) { preferredSize = new Dimension(600, 600) }
  
  def doCycle = {
    
    gl.doCycle
    pan.data = getAsArray
    pan.repaint
    
  }
  
  def top = new MainFrame {
    val toggle = 0
    val timer = new Timer(100, Swing.ActionListener { x => doCycle })
    contents = pan
    timer.start
  }
  
}