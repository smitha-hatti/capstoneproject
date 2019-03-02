
package com.capstone.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.capstone.model.Items;
import com.capstone.service.ItemsService;

@Controller
public class UserController {

	@Autowired
	ItemsService itemsService;

	@RequestMapping("/itemShow")
	public String itemShow(Model model) {

		List<Items> itemList = itemsService.findAll();
		model.addAttribute("itemList", itemList);
		model.addAttribute("activeAll", true);

		return "itemShow";
	}

	@RequestMapping("/searchByCategory")
	public String searchByCategory(

			@RequestParam("category") String category, Model model) {

		String classActiveCategory = "active" + category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);

		List<Items> itemList = itemsService.findByitemCategory(category);

		model.addAttribute("itemList", itemList);

		return "itemShow";
	}

	@RequestMapping("/itemDetails")
	public String itemDetails(@PathParam("id") Integer id, Model model, Principal principal) {

		if (principal != null) {
			String currentUser = principal.getName();
			model.addAttribute("currentUser", currentUser);
		}

		Items item = itemsService.findOne(id);
		model.addAttribute("item", item);

		List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5);

		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		
		if (item.getItemType().equals("Tops") || item.getItemType().equals("Shirts") || item.getItemType().equals("Dress") || item.getItemType().equals("Jackets")) {
			List<String> sizeAvailable = Arrays.asList("S", "M", "L", "XL", "XXL");
			model.addAttribute("sizeAvailable", sizeAvailable);
			model.addAttribute("size", "S");
		}
		else if (item.getItemType().equals("Pants")) {
			List<String> sizeAvailable = Arrays.asList("28", "30", "32", "34", "36", "38", "40", "42", "44");
			model.addAttribute("sizeAvailable", sizeAvailable);
			model.addAttribute("size", "28");
		}
		else {
			List<String> sizeAvailable = Arrays.asList("N/A");
			model.addAttribute("sizeAvailable", sizeAvailable);
			model.addAttribute("size", "N/A");
		}
		return "itemDetails";
	}

}
