package district.scalajs

object ComputeUtils {

  def calculatePercentage(price: Double, percent: Double): Double = f"${price * 0.01 * (100 - percent)}%1.2f".toDouble

}
