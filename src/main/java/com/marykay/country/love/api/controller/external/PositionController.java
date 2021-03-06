package com.marykay.country.love.api.controller.external;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marykay.country.love.api.contract.dto.GetCityDto;
import com.marykay.country.love.api.contract.dto.GetCountryDto;
import com.marykay.country.love.api.contract.dto.GetProvinceDto;
import com.marykay.country.love.api.contract.dto.GetTownDto;
import com.marykay.country.love.api.contract.dto.GetVillageDto;
import com.marykay.country.love.api.contract.response.GetCityResponse;
import com.marykay.country.love.api.contract.response.GetCountryResponse;
import com.marykay.country.love.api.contract.response.GetProvinceResponse;
import com.marykay.country.love.api.contract.response.GetTownResponse;
import com.marykay.country.love.api.contract.response.GetVillageResponse;
import com.marykay.country.love.service.PositionService;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangliu on 18/4/8.
 */
@RestController
@EnableAutoConfiguration
public class PositionController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	private PositionService positionService;

	/**
	 * 1-获取所有省，直辖市信息
	 *
	 */
	@ApiOperation(value = "getting all province", notes = "getting all province")
	@RequestMapping(value = "/v1/positions/provinces", method = RequestMethod.GET)
	public GetProvinceResponse provinceAll() {

		GetProvinceResponse getProvinceResponse = new GetProvinceResponse();
		List<GetProvinceDto> provinceAll = positionService.getAllProvince();
		getProvinceResponse.setGetProvinceDto(provinceAll);
		return getProvinceResponse;
	}

	/**
	 * 2-根据省，直辖市ID，获取所有城市信息
	 *
	 */
	@ApiOperation(value = "getting all cities in province", notes = "getting all cities in province")
	@RequestMapping(value = "/v1/positions/{provinceId}/cities", method = RequestMethod.GET)
	public GetCityResponse cityAll(@PathVariable Integer provinceId) {

		GetCityResponse getProvinceResponse = new GetCityResponse();
		List<GetCityDto> cityAll = positionService.getAllCity(provinceId);
		getProvinceResponse.setGetCityDto(cityAll);
		return getProvinceResponse;
	}

	/**
	 * 3-根据城市ID，获取所有地区信息
	 *
	 */
	@ApiOperation(value = "getting all countries in city", notes = "getting all countries in city")
	@RequestMapping(value = "/v1/positions/provinces/{cityId}/countries", method = RequestMethod.GET)
	public GetCountryResponse countryAll(@PathVariable long cityId) {

		GetCountryResponse getCountryResponse = new GetCountryResponse();
		List<GetCountryDto> countryAll = positionService.getAllCountry(cityId);
		getCountryResponse.setGetCountryDto(countryAll);
		return getCountryResponse;
	}

	/**
	 * 4-根据地区ID，获取所有乡镇信息
	 *
	 */
	@ApiOperation(value = "getting all towns in country", notes = "getting all towns in country")
	@RequestMapping(value = "/v1/positions/provinces/city/{countryId}/towns", method = RequestMethod.GET)
	public GetTownResponse townAll(@PathVariable long countryId) {

		GetTownResponse getTownResponse = new GetTownResponse();
		List<GetTownDto> cityAll = positionService.getAllTown(countryId);
		getTownResponse.setGetTownDto(cityAll);
		return getTownResponse;
	}

	/**
	 * 5-根据乡镇ID，获取所有村信息
	 *
	 */
	@ApiOperation(value = "getting all villages in town", notes = "getting all villages in town")
	@RequestMapping(value = "/v1/positions/provinces/city/country/{townId}/villages", method = RequestMethod.GET)
	public GetVillageResponse villageAll(@PathVariable long townId) {

		GetVillageResponse getVillageResponse = new GetVillageResponse();
		List<GetVillageDto> villageAll = positionService.getAllVillage(townId);
		getVillageResponse.setGetVillageDto(villageAll);
		return getVillageResponse;
	}
}
