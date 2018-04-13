package tricks.deprecation

/**
 *
 * @author Ivan A. Reffatti
 * @since 12/04/18
 */
class Deprecation {

    @Deprecated("We had to do it, sorry",
            level = DeprecationLevel.ERROR,
            replaceWith = ReplaceWith("any other method, plz..."))
    fun iamAnError() {
        println("no way to reach me")
    }

    @Deprecated("Be warnerd!",
                        level = DeprecationLevel.WARNING,
                        replaceWith = ReplaceWith("iamNotDeprecated()"))
    fun iamAWarn() {
        println("no way to reach me")
    }

    @Deprecated("Use something that is not hidden", level = DeprecationLevel.HIDDEN)
    fun iamHidden() {
        println("no way to reach me")
    }

    fun iamNotDeprecated() {
        println("Not deprecated!")
    }

}