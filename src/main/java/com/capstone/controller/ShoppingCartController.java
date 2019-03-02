package com.capstone.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.model.Cart;
import com.capstone.model.Items;
import com.capstone.repo.CartRepository;
import com.capstone.repo.ItemsRepository;
import com.capstone.service.ItemsService;
import com.capstone.service.ShoppingCartService;
import com.capstone.service.UserService;
import com.capstone.utility.EmailConstructor;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	ItemsService itemService;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	UserService userService;

	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	EmailConstructor emailConstructor;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Value("${spring.mail.password}")
	private String fromEmailPassword;

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private String mailPost;
	


	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		BigDecimal grandTotal = new BigDecimal(0);

		String currentUserEmail = principal.getName();
		List<Cart> cartList = cartRepository.findByUserId(currentUserEmail);
		if (cartList.isEmpty()) {
			model.addAttribute("emptyCart", true);
			return "shoppingCart";
		}
		for (Cart cartItemList : cartList) {
			grandTotal = grandTotal.add(cartItemList.getItemTotal());

		}
		int cartSize = cartList.size();
		System.out.println(cartSize);
		model.addAttribute("cartList", cartList);
		model.addAttribute("grandTotal", grandTotal);
		return "shoppingCart";

	}

	@RequestMapping("/addItem")
	public String addItem(@ModelAttribute("items") Items items, @ModelAttribute("qty") int qty,
			@ModelAttribute("Size") String sizeOrdered, Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("successMessage", "Please login/register for adding to cart");
			return "login";
		}

		items = itemService.findOne(items.getId());
		if (qty > (items.getItemStock())) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/itemDetails?id=" + items.getId();
		}

		String userId = principal.getName();
		int itemsId = items.getId();
		String itemsName = items.getItemName();
		BigDecimal itemsPrice = items.getItemPrice();

				shoppingCartService.addItem(userId, itemsId, itemsName, itemsPrice, sizeOrdered, qty);
		model.addAttribute("addItemSuccess", true);
		return "forward:/itemDetails?id=" + items.getId();
	}

	@GetMapping("/removeItem/{cartId}")
	public String removeItem(@PathVariable("cartId") Integer cartId) {

		shoppingCartService.removeItem(cartId);

		return "forward:/shoppingCart/cart";

	}

	@RequestMapping(value = "/orderConfirmation", method = RequestMethod.POST)
	public ModelAndView orderConfirmation(Principal principal) throws MessagingException {

		String userEmail = principal.getName();
		BigDecimal grandTotal = new BigDecimal(0);
		List<Cart> cartFetch = cartRepository.findByUserId(userEmail); 			// finding by userId
		for (Cart cartEntry : cartFetch) {
			Items itemFetch = itemService.findOne(cartEntry.getItemId()); 		// going thru each entry
			itemFetch.setItemStock(itemFetch.getItemStock() - cartEntry.getQtyOrdered()); // updating the stock
			itemsRepository.save(itemFetch); 								// saving
			grandTotal = grandTotal.add(cartEntry.getItemTotal());
		}	

		mailSender.send(
				emailConstructor.constructOrderConfirmationEmail(userEmail, cartFetch, grandTotal , Locale.ENGLISH));

		cartRepository.removeByUserId(userEmail);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userEmail", userEmail);
		modelAndView.addObject("grandTotal", grandTotal);

		modelAndView.setViewName("orderConfirmation");
		return modelAndView;

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage() {
		return "forward:/index";
	}

}
