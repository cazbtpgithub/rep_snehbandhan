package com.xanadutec.coreflex.EducationOccupation;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.State_Districts;

@RequestMapping(value = "/educationOccupation")
@Controller
public class EducationOccupationController {

	@Autowired
	EducationOccupationService educationOccupationService;

	@Autowired
	LocationService  locationService;
	
	
	@RequestMapping(value = "/loadCityAccordingToState", method = RequestMethod.GET)
	@ResponseBody
	public String loadCityAccordingToState(ModelMap model, HttpServletRequest request) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<State_Districts> districts = null;
		try {
			districts = locationService.getListOfDistrictByStateId(request.getParameter("id"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try {
			json = ow.writeValueAsString(districts);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/loadsubDistrictAccordingToDistrict", method = RequestMethod.GET)
	@ResponseBody
	public String loadsubDistrictAccordingToDistrict(ModelMap model, HttpServletRequest request) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<District_SubDistricts> subDistricts = null;
		try {
			subDistricts = locationService.getListOfSubDistrictByDistrictId(request.getParameter("id"));
		} catch (Exception e) {

		}
		try {
			json = ow.writeValueAsString(subDistricts);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/loadCasteAccordingToReligion", method = RequestMethod.GET)
	@ResponseBody
	public String loadCasteAccordingToReligion(ModelMap model, HttpServletRequest request) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<Caste> castes = null;
		try {
			castes = educationOccupationService.loadCasteAccordingToReligion(request.getParameter("id"));
		} catch (Exception e) {

		}
		try {
			json = ow.writeValueAsString(castes);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/loadOccupationAccordingToOccuCategory", method = RequestMethod.GET)
	@ResponseBody
	public String loadOccupationAccordingToOccuCategory(ModelMap model, HttpServletRequest request) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<Occupation> occupations = null;
		try {
			occupations = educationOccupationService.loadOccupationAccordingToOccuCategory(request.getParameter("id"));
		} catch (Exception e) {

		}
		try {
			json = ow.writeValueAsString(occupations);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/loadEducationAccordingToEduCategory", method = RequestMethod.GET)
	@ResponseBody
	public String loadEducationAccordingToEduCategory(ModelMap model, HttpServletRequest request) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<Education> educations = null;
		try {
			educations = educationOccupationService.loadEducationAccordingToEduCategory(request.getParameter("id"));
		} catch (Exception e) 
		{
          e.printStackTrace();
		}
		try {
			json = ow.writeValueAsString(educations);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	}

}
