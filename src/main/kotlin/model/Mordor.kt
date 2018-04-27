package model

import factory.UrukHaiFactory

/**
 * @author Ivan A. Reffatti
 * @since 2018-04-27
 */
class Mordor : MiddleEarth(), UrukHaiFactory {

    override fun produceUrukHai(): Race {
        return UrukHai("Grom, The Killer")
    }

}