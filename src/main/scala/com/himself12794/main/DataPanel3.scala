package com.himself12794.main

import java.awt.Graphics2D
import scala.swing.Panel
import scala.collection.mutable.HashMap
import java.awt.Color

class DataPanel3(val gl: GameLife3, val mapping: HashMap[Boolean,Color]) extends Panel {
  
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
      g.setColor(mapping(gl(x,y) == 1))
      g.fillRect(x1, y1, x2 - x1, y2 - y1)
    }
  }
}