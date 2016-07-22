package com.himself12794.main

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics2D

import scala.collection.mutable.HashMap
import scala.swing._

import javax.swing.Timer

class DataPanel(val gl: GameLife2, val mapping: HashMap[Boolean,Color]) extends Panel {
  
  override def paintComponent(g: Graphics2D) {
    
    val dx = g.getClipBounds.width.toFloat  / gl.width
    val dy = g.getClipBounds.height.toFloat / gl.height
    for {
      x <- 0 until gl.width
      y <- 0 until gl.height
      x1 = (x * dx).toInt
      y1 = (y * dy).toInt
      x2 = ((x + 1) * dx).toInt
      y2 = ((y + 1) * dy).toInt
    } {
      g.setColor(mapping(gl(x,y)))
      //gl(x,y) match {
      //  case c: Bool => g.setColor(mapping(c))
      //}
      g.fillRect(x1, y1, x2 - x1, y2 - y1)
    }
  }
}

object Grid extends SimpleSwingApplication {
  
  var cycles = 0
  
  private val gl = new GameLife2(Shapes.gosperGlidingGun)
  
  val mapping = new HashMap[Boolean,Color]
  
  mapping.put(true, Color.WHITE)
  mapping.put(false, Color.BLACK)
  
  val pan = new DataPanel(gl,mapping) { preferredSize = new Dimension(600, 600) }
  
  def doCycle = {
    
    gl.doCycle
    pan.repaint
    println(cycles)
    cycles += 1
    
  }
  
  def top = new MainFrame {
    val toggle = 0
    val timer = new Timer(1, Swing.ActionListener { _ => doCycle })
    contents = pan
    timer.start
  }
  
}