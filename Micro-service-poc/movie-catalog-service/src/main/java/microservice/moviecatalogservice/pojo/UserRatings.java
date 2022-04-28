package microservice.moviecatalogservice.pojo;

import java.util.List;

public class UserRatings {
	
	private List<Rating> userRathings;

	public UserRatings() {
		super();
	}

	public UserRatings(List<Rating> userRathings) {
		super();
		this.userRathings = userRathings;
	}

	public List<Rating> getUserRathings() {
		return userRathings;
	}

	public void setUserRathings(List<Rating> userRathings) {
		this.userRathings = userRathings;
	}

}
