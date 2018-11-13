
public class Number {
	private String _strnum;
	private static enum final Range { SMALL, MEDIUM, LARGE };
	private Range _range;
	private static enum final Color { BLACK, RED, GREEN };
	private Color _color;

	public String getStrnum() {
		return this._strnum;
	}
	public void setStrnum(String val) {
		this._strnum = val;
	}

	public Range getRange() {
		return this._range;
	}
	public void setRange(Range val) {
		this._range = val;
	}

	public Color getColor() {
		return this._color;
	}
	public void setColor(Color val) {
		this._color = val;
	}
}
