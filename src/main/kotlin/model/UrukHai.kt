package model

import tricks.URUK_HAI

/**
 * @author Ivan A. Reffatti
 * @since 2018-04-27
 */
data class UrukHai(private val urukHaiName: String) : Race(URUK_HAI, urukHaiName) {

    val weapon = "Mace"

}