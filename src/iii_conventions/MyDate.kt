package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.lexiOrderedStr().compareTo(other.lexiOrderedStr())
    }

    private fun lexiOrderedStr() = "%4d%2d%2d".format(year, month, dayOfMonth)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate> {
    override fun contains(value: MyDate): Boolean {
        return value >= start && value <= endInclusive
    }
}
