package person.marlon.diamond.common.time;

public class Period {

	private final int years;

	private final int months;

	private final int days;

	private Period(int months, int days) {
		this.years = 0;
		this.months = months;
		this.days = days;
	}

	private Period(int years,int months, int days) {
		this.years = years;
		this.months = months;
		this.days = days;
	}

	public int getYears() {
		return years;
	}

	public int getMonths() {
		return months;
	}

	public int getDays() {
		return days;
	}

	/**
	 * A constant for a period of zero.
	 */
	public static final Period ZERO = new Period(0, 0, 0);

	/**
	 * Obtains a {@code Period} representing a number of years.
	 * <p>
	 * The resulting period will have the specified years.
	 * The months and days units will be zero.
	 *
	 * @param years  the number of years, positive or negative
	 * @return the period of years, not null
	 */
	public static Period ofYears(int years) {
		return create(years, 0, 0);
	}

	/**
	 * Obtains a {@code Period} representing a number of months.
	 * <p>
	 * The resulting period will have the specified months.
	 * The years and days units will be zero.
	 *
	 * @param months  the number of months, positive or negative
	 * @return the period of months, not null
	 */
	public static Period ofMonths(int months) {
		return create(0, months, 0);
	}

	/**
	 * Obtains a {@code Period} representing a number of weeks.
	 * <p>
	 * The resulting period will be day-based, with the amount of days
	 * equal to the number of weeks multiplied by 7.
	 * The years and months units will be zero.
	 *
	 * @param weeks  the number of weeks, positive or negative
	 * @return the period, with the input weeks converted to days, not null
	 */
	public static Period ofWeeks(int weeks) {
		return create(0, 0, multiplyExact(weeks, 7));
	}

	/**
	 * Obtains a {@code Period} representing a number of days.
	 * <p>
	 * The resulting period will have the specified days.
	 * The years and months units will be zero.
	 *
	 * @param days  the number of days, positive or negative
	 * @return the period of days, not null
	 */
	public static Period ofDays(int days) {
		return create(0, 0, days);
	}

	public static Period ofMonthsAndDays(int months,int days) {
		return create(0, months, days);
	}

	/**
	 * Obtains a {@code Period} representing a number of years, months and days.
	 * <p>
	 * This creates an instance based on years, months and days.
	 *
	 * @param years  the amount of years, may be negative
	 * @param months  the amount of months, may be negative
	 * @param days  the amount of days, may be negative
	 * @return the period of years, months and days, not null
	 */
	public static Period of(int years, int months, int days) {
		return create(years, months, days);
	}

	/**
	 * Creates an instance.
	 *
	 * @param years  the amount
	 * @param months  the amount
	 * @param days  the amount
	 */
	private static Period create(int years, int months, int days) {
		if ((years | months | days) == 0) {
			return ZERO;
		}
		return new Period(years, months, days);
	}

	/**
	 * Returns the product of the arguments,
	 * throwing an exception if the result overflows an {@code int}.
	 *
	 * @param x the first value
	 * @param y the second value
	 * @return the result
	 * @throws ArithmeticException if the result overflows an int
	 * @since 1.8
	 */
	public static int multiplyExact(int x, int y) {
		long r = (long)x * (long)y;
		if ((int)r != r) {
			throw new ArithmeticException("integer overflow");
		}
		return (int)r;
	}

}
