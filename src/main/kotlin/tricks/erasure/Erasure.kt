package tricks.erasure

/**
 *
 * @author Ivan A. Reffatti
 * @since 12/04/18
 */
class Erasure {

    fun doAction(any: List<String>) {

    }

    @JvmName("doActionWithInt")
    fun doAction(any: List<Int>) {

    }
}