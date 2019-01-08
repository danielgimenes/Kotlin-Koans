package iv_properties

import util.TODO
import util.doc34
import kotlin.reflect.KProperty

class LazyPropertyUsingDelegates(val initializer: () -> Int) {
    val lazyValue: Int by LazyDelegate()
}

fun todoTask34(): Lazy<Int> = TODO(
        """
        Task 34.
        Read about delegated properties and make the property lazy by using delegates.
    """,
        documentation = doc34()
)

class LazyDelegate {

    private var value: Int? = null

    operator fun getValue(thisRef: LazyPropertyUsingDelegates, property: KProperty<*>): Int {
        if (value == null) {
            value = thisRef.initializer()
        }
        return value!!
    }

    operator fun setValue(thisRef: LazyPropertyUsingDelegates, property: KProperty<*>, value: Int) {
        this.value = value
    }

}