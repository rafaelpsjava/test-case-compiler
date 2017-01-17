package br.com.pegasus.solutions.tc.util.pojo;

/**
 * MatcherExpression
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class MatcherExpression {

	private MatcherExpressionType type;

	private String name;

	private String value;

	private String matcherFile;

	public MatcherExpression() {
	}

	public MatcherExpression(String name, String value) {
		this(name, value, null);
	}

	public MatcherExpression(String name, MatcherExpressionType type) {
		this(name, null, type);
	}

	public MatcherExpression(String name, String value, MatcherExpressionType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public MatcherExpressionType getType() {
		return type;
	}

	public void setType(MatcherExpressionType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMatcherFile() {
		return matcherFile;
	}

	public void setMatcherFile(String matcherFile) {
		this.matcherFile = matcherFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatcherExpression other = (MatcherExpression) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
