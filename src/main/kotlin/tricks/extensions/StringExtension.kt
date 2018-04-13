package tricks.extensions

fun String.cutByThreeLetters() : String {
    return if(this.length > 3) this.substring(0, 3) else this
}

