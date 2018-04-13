package tricks.nothing

/**
 *
 * @author Ivan A. Reffatti
 * @since 12/04/18
 */
class NothingReturns {

    /**
     * [Nothing] provides to the compiler the ability to know that there is no way to continue with the execution
     * when this method is called
     */
    fun doAction() : Nothing {
        throw IllegalStateException("Hey, I'm getting an error, you gotta do something, plz!")
    }

}