package sandbox.joda.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.RoundingMode;

import org.joda.money.CurrencyMismatchException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;


public class JodaMoneyTest {

	private static final boolean VERBOSE = true;


	@Test
	public void test_parse() {
		final Money oneDollar = Money.parse("USD 1.00");
		if (VERBOSE) {
			System.out.println(oneDollar);
		}
	}


	@Test
	public void test_info() {
		for (final CurrencyUnit currencyUnit : CurrencyUnit.registeredCurrencies()) {
			System.out.println(currencyUnit.getCode());
		}
	}


	@Test
	public void test_conversions() {
		// from doubles
		{
			final String oneDollarString = "1.00";
			final Double oneDollarDouble = Double.valueOf(oneDollarString);
			final Money oneDollar = Money.of(CurrencyUnit.USD, oneDollarDouble);
			assertEquals(Money.parse("USD 1.00"), oneDollar);
			if (VERBOSE) {
				System.out.println(oneDollar);
			}
		}

		// from double with greater-than-expected precision
		{
			try {
				Money.of(CurrencyUnit.USD, 1.004d);
			} catch (final ArithmeticException ae) {
				//expected
			}

			// providing a sensible rounding mechanism gets around the problem
			assertEquals(Money.parse("USD 1.00"), Money.of(CurrencyUnit.USD, 1.004d, RoundingMode.HALF_UP));
			assertEquals(Money.parse("USD 1.01"), Money.of(CurrencyUnit.USD, 1.005d, RoundingMode.HALF_UP));
			assertEquals(Money.parse("USD 1.01"), Money.of(CurrencyUnit.USD, 1.009d, RoundingMode.HALF_UP));

		}

		// from longs, using minor
		final Money twoDollars = Money.ofMinor(CurrencyUnit.USD, 200L);
		assertEquals(Money.parse("USD 2.00"), twoDollars);
		if (VERBOSE) {
			System.out.println(twoDollars);
		}

		final Money threeDollars = Money.ofMajor(CurrencyUnit.USD, 3L);
		assertEquals(Money.parse("USD 3.00"), threeDollars);
		if (VERBOSE) {
			System.out.println(threeDollars);
		}
	}


	@Test
	public void test_calculations() {
		{
			// add two like currencies
			final Money oneDollar = Money.parse("USD 1.00");
			final Money twoDollars = Money.parse("USD 2.00");
			final Money sum = oneDollar.plus(twoDollars);
			assertEquals(Money.parse("USD 3.00"), sum);
			if (VERBOSE) {
				System.out.println(oneDollar + " + " + twoDollars + " = " + sum);
			}
		}

		{
			// add two unlike currencies
			final Money oneDollar = Money.of(CurrencyUnit.USD, 1.d);
			final Money oneEuro = Money.of(CurrencyUnit.EUR, 1.d);
			try {
				oneDollar.plus(oneEuro);
				fail("expected CurrencyMismatchException");
			} catch (final CurrencyMismatchException cme) {
				// expected
			}
		}

	}

}
