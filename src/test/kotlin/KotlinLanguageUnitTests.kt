import model.Mage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import tricks.GANDALF
import tricks.JRR_TOLKIEN
import tricks.THE_GRAY
import tricks.THE_WHITE
import tricks.extensions.cutByThreeLetters
import tricks.deprecation.Deprecation
import tricks.lateinit.LateInit
import tricks.nothing.NothingReturns
import kotlin.system.measureTimeMillis

/**
 *
 * Test methods on this class are not made by focusing on good pratices, but on explanation
 *
 * @author Ivan A. Reffatti
 * @since 23/04/18
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows us to avoid JUnit static methods
class KotlinLanguageUnitTests {

    private val gandalfTheGray = Mage(firstName = GANDALF, surName = THE_GRAY, age = 2019)

    /**
     * Copy methods on data classes allow us to copy object changing only the properties that we desire
     *
     * @author Ivan A. Reffatti
     * @since 2018-04-12
     */
    @Test
    fun `copy must keep only person age`() {

        val gandalfTheWhite = gandalfTheGray.copy(firstName = GANDALF, surName = THE_WHITE)

        assertEquals(gandalfTheGray.firstName, gandalfTheWhite.firstName)
        assertEquals(THE_WHITE, gandalfTheWhite.surName)
        assertEquals(2019, gandalfTheWhite.age)
    }

    @Test
    fun `for Loop used with stepNumber must set last step as age`() {

        val stepNumber = 1000

        for (i in 0 .. stepNumber) {
            if (i == stepNumber) {
                val copy = gandalfTheGray.copy(age = stepNumber)
                assertEquals(1000, copy.age)
            }
        }
    }

    @Test
    fun `null values can be forced and you get what you wanted`() {
        assertThrows<NullPointerException> {
            gandalfTheGray.fatherName!!.length
        }
    }

    /**
     * Through Null Safety Kotlin allows us to just ignore null values
     */
    @Test
    fun `null values can be ignored and return null`() {
        assertNull(gandalfTheGray.fatherName?.length)
    }


    @Nested
    inner class ScopedOnes {

        @Test
        fun `let it run and isolate some block of code with "this" as argument`() {
            run {
                assertEquals(GANDALF, gandalfTheGray.firstName)
                assertEquals(THE_GRAY, gandalfTheGray.surName)
                assertEquals(2019, gandalfTheGray.age)
            }
        }

        @Test
        fun `access object attributes by using with block`() {
            with(gandalfTheGray) {
                assertEquals(GANDALF, firstName)
                assertEquals(THE_GRAY, surName)
                assertEquals(2019, age)
            }
        }

        @Test
        fun `but better don't try it with null values`() {
            var mage: Mage? = null

            with(mage) {
                assertThrows<NullPointerException> {
                    assertEquals(GANDALF, this!!.firstName)
                    assertEquals(THE_GRAY, this.surName)
                    assertEquals(2019, this.age)
                }
            }
        }

        @Test
        fun `instead use the T run extension`() {
            var mage: Mage? = null

            mage?.run {
                assertEquals(GANDALF, firstName)
                assertEquals(THE_GRAY, surName)
                assertEquals(2019, age)
            }
        }

        @Test
        fun `you can also use let, having it on block scope`() {

            // let give you an extra argument, as "it", and "this" as an external scope access
            gandalfTheGray.let {
                assertEquals(GANDALF, it.firstName)
                assertEquals(THE_GRAY, it.surName)
                assertEquals(2019, it.age)
            }
        }

        @Test
        fun `after let, you can "also" do something else, like level up`() {

            // let it fight with the Balrog of Morgoth!
            val theWhiteIsBack = gandalfTheGray.let {
                assertEquals(GANDALF, it.firstName)
                assertEquals(THE_GRAY, it.surName)
                assertEquals(2019, it.age)
                it.copy(surName = THE_WHITE)
            }.also {
                // also {} always return the object that entered
                assertEquals(THE_WHITE, it.surName)
                it.fatherName = JRR_TOLKIEN
            }

            assertEquals(theWhiteIsBack.surName, THE_WHITE)
            assertEquals(theWhiteIsBack.fatherName, JRR_TOLKIEN)
        }

        @Test
        fun `And at last, apply for creating instances`() {

            // commonly used for post constructing objects
            val gandalf = Mage(GANDALF,THE_GRAY, 2019)
                    .apply {
                fatherName = JRR_TOLKIEN
            }

            assertEquals(GANDALF, gandalf.firstName)
            assertEquals(JRR_TOLKIEN, gandalf.fatherName)

        }
    }

    @Nested
    inner class TrickOnes {

        @Test
        fun `measure any execution time inside a block`() {

            val timeToExecute = measureTimeMillis {
                for (i in 0 .. 100) {
                    Thread.sleep(10)
                }
            }

            println("Time to execute: $timeToExecute")
            assertTrue(timeToExecute > 0)
        }

        @Test
        fun `TODO method is understood by compiler and that's great`() {
            assertThrows<NotImplementedError> {
                TODO("I think we should implement something here!")
            }
        }

        @Test
        fun `when returning Nothing, the compiler will warn you that there is nothing to do`() {
            assertThrows<IllegalStateException> {
                NothingReturns().doAction()
                println("no way to reach this part of the code")
            }
        }

        @Test
        fun `deprecation got better on Kotlin`() {
            // Deprecation().iamAnError() // this will show compiler errors
            Deprecation().iamAWarn()
        }

        @Test
        fun `lateinit lets you later initialize objects`() {

            val lateInit = LateInit().apply {
                iAmLate = "Late"
            }

            assertEquals("Late", lateInit.iAmLate)
        }

        @Test
        fun `lateinit can be checked if it's initialized`() {

            val lateInit = LateInit()
            assertFalse(lateInit.isLateInitialized())

            lateInit.iAmLate = "initialize!"
            assertTrue(lateInit.isLateInitialized())

        }

        @Test
        fun `as lateinit lazy ones are loaded on memory only by it's access`() {
            // but are vals and must be initialized
            with( gandalfTheGray ) {

//                 val fullName : String by kotlin.lazy { "$firstName $surName" }
                // Double check locking, assures thread access
                // and as a constant it must be initialized just once, almost like a singleton
                val fullName = kotlin.lazy { "$firstName $surName" }

                // fullName is really accessed just by here
                kotlin.io.println(fullName.value)
                assertTrue(fullName.isInitialized())

                // Double check locking NO MORE, it may get "corrupted"
                kotlin.lazy(kotlin.LazyThreadSafetyMode.NONE) { "$firstName $surName $age" }

            }.also {
                // fullNameWithAge is never initialized
                assertFalse(it.isInitialized())
            }
        }

        @Test
        fun `validations are also better on kotlin`() {
            assertThrows<IllegalArgumentException> {
                require(gandalfTheGray.firstName == "Frodo") {"Hey, it should be Gandalf!"}
            }
//        requireNotNull() // throws IllegalArgumentException
//        check() throws IllegalStateException
//        checkNotNull() throws IllegalStateException
//        assert() throws AssertionError
        }

        @Test
        fun `extensions are great`() {
            assertTrue("123456".cutByThreeLetters().length == 3 )
        }
    }
}