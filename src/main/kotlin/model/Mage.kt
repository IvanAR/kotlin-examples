package model

fun Mage.callMyFather() : Mage{
    if (this.firstName == "Gandalf") {
        this.fatherName = "J. R. R. Tolkien"
    }
    return this
}

/**
 * Class attributes on kotlin, must be initialized unless they are on a constructor,
 * avoiding this way null initializations
 *
 * @author Ivan A. Reffatti
 */
data class Mage(
        val firstName : String,
        val surName : String,
        val age : Int) : Race("Mage", "$firstName $surName")
{

    var fatherName : String? = null

    companion object {
        fun create() = Mage("Gandalf", "The Gray", 1000)
    }

}