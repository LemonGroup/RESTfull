import org.lemongroup.lemonstat.rest.datamodel.Account;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Account.class})
@JsonTest
public class AccountJsonTest {

        @Autowired
	private JacksonTester<Account> json;

	@Test
	public void testSerialize() throws Exception {
	    byte privilege = 1;
	    Account account = new Account("egor", "qwerty", "egor@me.com",privilege);
	    //In expected.json file has not password fiels. This is asimmetric serialization 
	    assertThat(this.json.write(account)).isEqualToJson("expected.json");
	    assertThat(this.json.write(account)).hasJsonPathStringValue("@.username");
	    assertThat(this.json.write(account)).extractingJsonPathStringValue("@.username")
		.isEqualTo("egor");
	}
	@Test
	public void testDeserialize() throws Exception {
	    String content = "{\"username\":\"egor\",\"password\":\"qwerty\",\"email\":\"egor@me.com\",\"privilege\":1}";
	    //assertThat(this.json.parse(content))
	    //	.isEqualTo(new Account("egor","qwerty","egor@me.com",(byte)1));
	    assertThat(this.json.parseObject(content).getUsername()).isEqualTo("egor");
	    //Check that password exists in deserialized object
	    assertThat(this.json.parseObject(content).getPassword()).isEqualTo("qwerty");
	    assertThat(this.json.parseObject(content).getEmail()).isEqualTo("egor@me.com");
	    assertThat(this.json.parseObject(content).getPrivilege()).isEqualTo((byte)1);
	}
}
//Run this specific test:
//mvn -Dtest=AccountJsonTest test
