package com.himself12794.main

import scala.swing.Swing
import scala.swing.MainFrame
import java.awt.Dimension
import scala.swing.SimpleSwingApplication
import scala.collection.mutable.HashMap
import javax.swing.Timer
import java.awt.Color

object Grid3 extends SimpleSwingApplication {
  
  var cycles = 0
  
  private val gl = new GameLife3(Shapes.gosperGlidingGun)
  
  val mapping = new HashMap[Boolean,Color]
  
  mapping.put(true, Color.WHITE)
  mapping.put(false, Color.BLACK)
  
  val pan = new DataPanel3(gl,mapping) { preferredSize = new Dimension(600, 600) }
  
  def doCycle(t: Timer) = {
    
    //if (cycles == 0) println(gl.gc)
    
    if (cycles == 0 || cycles == 1) {
      //println(gl)
      //println
    
      gl.doCycle
      pan.repaint
      cycles += 1
    } else { 
      //t.stop
      //quit
    }
    
  }
  
  def top = new MainFrame {
    val toggle = 0
    val timer: Timer = new Timer(63, Swing.ActionListener { _ => doCycle(timer) })
    contents = pan
    timer.start
  }
  
}