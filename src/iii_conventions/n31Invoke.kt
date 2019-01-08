package iii_conventions

import util.TODO

class Invokable {

    private var calls: Int = 0

    operator fun invoke(): Invokable {
        calls++
        return this
    }

    fun getNumberOfInvocations() = calls

}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
