package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }

    operator fun plus(timeInterval: TimeInterval): MyDate {
        return plus(timeInterval.times(1))
    }

    operator fun plus(repeatedInterval: RepeatedTimeInterval): MyDate =
            this.addTimeIntervals(repeatedInterval.timeInterval, repeatedInterval.times)

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate)
    : ClosedRange<MyDate>, Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> =
            object : Iterator<MyDate> {

                private var current: MyDate = start

                override fun hasNext() = current <= endInclusive

                override fun next(): MyDate {
                    val tmp = current
                    current = current.nextDay()
                    return tmp
                }

            }

}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)

operator fun TimeInterval.times(repetitions: Int) = RepeatedTimeInterval(this, repetitions)