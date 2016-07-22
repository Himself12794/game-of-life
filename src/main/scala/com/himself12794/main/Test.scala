package com.himself12794.main

import java.util.regex.Pattern

object Test extends App {
  
  println("Hello, World")
  
  //Main.main("Do some more stuff", "Hello, World", "Integration of Java into Scala")
  System.out.println("d'oh");
  
  doSomeStuff
  println(doRecursiveMath(5))
  
  println("Temp conversion")
  
  println(toCelsius(212))
  println(toFahrenheit(100))
  
  println(performFunction(f))
  
  val mult = multiplier(5)
  
  println(mult(6))
  
  val l = List(5, 2, 3)
  
  def f(x: Int, y: Int) = x + y
  
  def doSomeStuff = {

    val x = 2
    println(-x)
    println( "pink" contains 'p' )
    println( 1 to 10 by 3 )
    for (i <- 1 to 10 by 11) {
      println(i)
    }
    
  }
  
  def multiplier(x: Float) = (y: Float) => y * x
  
  def performFunction(f: ((Int, Int) => Int)) = f(5, 5)
  
  def doMath(x: Int) = x * x
  
  def doRecursiveMath(x: Int): Int = {
    if (x <= 1) 1
    else x * doRecursiveMath(x - 1)
  }
  
  def toCelsius(x: Float) = ((x - 32) * 5)/9
  
  def toFahrenheit(x: Float) = ((x * 9)/5) + 32 
  
}