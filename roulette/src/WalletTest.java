import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WalletTest {

	@Test
	void testInitialize() {
		Wallet wallet = new Wallet(10000);
		assertEquals(10000, wallet.getCache());
	}

	@Test
	void testPayCache() {
		Wallet wallet = new Wallet(10000);
		wallet.payCache(100);
		assertEquals(9900, wallet.getCache());
	}

	@Test
	void testIsInsolvency() {
		Wallet wallet = new Wallet(10000);
		wallet.payCache(10001);
		assertTrue(wallet.isInsolvency());
	}
}
