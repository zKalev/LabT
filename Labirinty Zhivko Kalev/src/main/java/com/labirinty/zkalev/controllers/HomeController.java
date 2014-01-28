package com.labirinty.zkalev.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.labirinty.zkalev.dao.model.GoodsInStock;
import com.labirinty.zkalev.services.GoodsService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	private static final String HOME_JSP = "home";
	private static final String OBJECT_LIST_NAME = "goods";
	private static final String DELETE_GOOD = "/delete";
	private static final String UPDATE_GOOD = "/update";

	@Autowired
	GoodsService gs;

	/**
	 * This method just returns all items
	 * 
	 * @param gis
	 * @param result
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHome(@Valid @ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		ModelAndView mv = new ModelAndView(HOME_JSP);

		List<GoodsInStock> gisList = gs.getAllGoods();
		mv.addObject(OBJECT_LIST_NAME, gisList);
		return mv;
	}

	/**
	 * This method handles the request that insert new item
	 * 
	 * @param gis
	 * @param result
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addNewGood(
			@Valid @ModelAttribute("gis") GoodsInStock gis, BindingResult result) {
		ModelAndView mv = new ModelAndView(HOME_JSP);

		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {
			if (gs.exist(gis))
				throw new IllegalStateException(
						"This item already exist, so you can not add it again!");
			gs.save(gis);
		}

		List<GoodsInStock> gisList = gs.getAllGoods();
		mv.addObject(OBJECT_LIST_NAME, gisList);
		return mv;
	}

	/**
	 * This method handles the request that delete item
	 * 
	 * @param gis
	 * @param result
	 * @return
	 */
	@RequestMapping(value = DELETE_GOOD, method = RequestMethod.POST)
	public ModelAndView delete(@Valid @ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		ModelAndView mv = new ModelAndView(HOME_JSP);

		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {
			if (!gs.exist(gis))
				throw new IllegalStateException(
						"This item does not exist, so it can't be deleted");
			gs.delete(gis);
		}

		List<GoodsInStock> gisList = gs.getAllGoods();

		mv.addObject(OBJECT_LIST_NAME, gisList);
		return mv;
	}

	/**
	 * This method handles the request that update item
	 * 
	 * @param result
	 * @return
	 */
	@RequestMapping(value = UPDATE_GOOD, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		ModelAndView mv = new ModelAndView(HOME_JSP);

		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {
			if (!gs.exist(gis))
				throw new IllegalStateException(
						"This item does not exist, so it can't be updated");
			gs.update(gis);
		}
		List<GoodsInStock> gisList = gs.getAllGoods();
		mv.addObject(OBJECT_LIST_NAME, gisList);
		return mv;
	}
}
