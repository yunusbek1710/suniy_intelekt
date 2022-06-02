package district.scalajs

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime, LocalTime}

object DateUtils {

  def formatter: String => DateTimeFormatter = DateTimeFormatter.ofPattern

  def dateToStr: LocalDate => String = _.format(formatter("dd.MM.yyyy"))

  def ltToStr: LocalTime => String = _.format(formatter("HH:mm"))

  def ldtToString(date: LocalDateTime, format: String = "MMM dd, yyyy HH:mm"): String =
    date.format(DateTimeFormatter.ofPattern(format))

}
