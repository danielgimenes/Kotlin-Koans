package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.lexiOrderedStr().compareTo(other.lexiOrderedStr())
    }

    private fun lexiOrderedStr() = "%4d%2d%2d".format(year, month, dayOfMonth)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(value: MyDate): Boolean {
        return value >= start && value <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return object: Iterator<MyDate> {
            var next = start
            override fun hasNext(): Boolean {
                return next <= endInclusive
            }

            override fun next(): MyDate {
                val toReturn = next
                next = next.nextDay()
                return toReturn
            }

        }
    }
}
