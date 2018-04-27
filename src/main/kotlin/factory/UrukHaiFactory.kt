package factory

import model.Race
import tricks.URUK_HAI

/**
 * @author Ivan A. Reffatti
 * @since 2018-04-27
 */
interface UrukHaiFactory {

    fun produceUrukHai(): Race

    fun generateAnyRandom(): Race {
        return Race(URUK_HAI, "Some random name")
    }

}