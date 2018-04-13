package tricks.lateinit

/**
 *
 * @author Ivan A. Reffatti
 * @since 23/04/18
 */
class LateInit {

    lateinit var iAmLate: String

    fun isLateInitialized(): Boolean {
        return this::iAmLate.isInitialized
    }

}