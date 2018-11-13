
public class Number {
	private String _strnum;
	public String getStrnum() {
		return this._strnum;
	}
	public void setStrnum(String val) {
		this._strnum = val;
	}

	private static enum Range { SMALL, MEDIUM, LARGE }
	private Range _range;
	public Range getRange() {
		return this._range;
	}
	public void setRange(Range val) {
		this._range = val;
	}

	private static enum Color { BLACK, RED, GREEN };
	private Color _color;
	public Color getColor() {
		return this._color;
	}
	public void setColor(Color val) {
		this._color = val;
	}
}
