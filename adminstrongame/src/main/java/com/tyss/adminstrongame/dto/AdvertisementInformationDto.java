package com.tyss.adminstrongame.dto;

import java.util.List;

import com.tyss.adminstrongame.entity.AdvertisementImage;

import lombok.Data;

@Data
public class AdvertisementInformationDto {

	private int advertisementId;

	private String advertisementDescription;
	
	private List<AdvertisementImage> advertisementImage;

	public AdvertisementInformationDto() {
		super();
	}

	public AdvertisementInformationDto(int advertisementId, String advertisementDescription,
			List<AdvertisementImage> advertisementImage) {
		super();
		this.advertisementId = advertisementId;
		this.advertisementDescription = advertisementDescription;
		this.advertisementImage = advertisementImage;
	}
	
}
