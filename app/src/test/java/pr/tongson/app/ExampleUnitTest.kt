package pr.tongson.app

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)

        //DDD.main(null)
        //System.out.println()
        //println(re())
        //println(WithException())

        Read();
    }

    fun re(): String {
        var value: String
        try {
            value = "try"
            return value
        } catch (e: Exception) {
            value = "catch"
        } finally {
            value = "finally"
        }
        value = "last"
        return value
    }

    fun WithException(): Int {
        var i = 10
        try {
            println("i in try block is：$i")
            i = i / 0
            return --i
        } catch (e: java.lang.Exception) {
            println("i in catch - form try block is：$i")
            --i
            println("i in catch block is：$i")
            return --i
        } finally {
            println("i in finally - from try or catch block is--$i")
            --i
            println("i in finally block is--$i")
            return --i
        }
    }
}
