package i_introduction._7_Nullable_Types

import org.junit.Assert
import org.junit.Test

class Test {

    fun testSendMessageToClient(client: Client?, message: String?, email: String? = null, shouldBeInvoked: Boolean = false) {
        var invoked = false
        sendMessageToClient(client, message, object : Mailer {
            override fun sendMessage(actualEmail: String, actualMessage: String) {
                invoked = true
                Assert.assertEquals("the message is not as expected", message, actualMessage)
                Assert.assertEquals("the email is not as expected", email, actualEmail)
            }
        })
        Assert.assertEquals("The function 'sendMessage' should${if (shouldBeInvoked) "" else "n't"} be invoked",
                shouldBeInvoked, invoked)
    }

    @Test
    fun isEverythingOK() {
        testSendMessageToClient(Client(PersonalInfo("khoatd@gmail.com")),
                "hello","khoatd@gmail.com", true)
    }

    @Test
    fun isMissingClientInfo() {
        testSendMessageToClient(Client(null), null)
    }

    @Test fun noClient() {
        testSendMessageToClient(null, "Hi Bob! We have an awesome proposition for you...")
    }

    @Test
    fun isMissingEmail() {
        testSendMessageToClient(Client(PersonalInfo(null)),
                "hello")
    }

    @Test
    fun isMissingMessage() {
        testSendMessageToClient(Client(PersonalInfo("khoatd@gmail.com")), null)
    }
}