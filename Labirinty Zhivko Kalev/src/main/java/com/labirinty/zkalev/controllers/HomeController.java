package com.labirinty.zkalev.controllers;

import java.util.List;

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

	@Autowired
	GoodsService gs;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHome(@ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		ModelAndView mv = new ModelAndView(HOME_JSP);
		List<GoodsInStock> k = gs.getAllGoods();
		mv.addObject("goods", k);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addNewGood(@ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {
			if (gs.exist(gis))
				throw new IllegalStateException();
			gs.save(gis);
		}

		ModelAndView mv = new ModelAndView(HOME_JSP);
		List<GoodsInStock> k = gs.getAllGoods();
		mv.addObject("goods", k);
		return mv;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {
			if (!gs.exist(gis))
				throw new IllegalStateException();
			gs.delete(gis);
		}
		ModelAndView mv = new ModelAndView(HOME_JSP);
		List<GoodsInStock> k = gs.getAllGoods();

		mv.addObject("goods", k);
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("gis") GoodsInStock gis,
			BindingResult result) {
		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {
			if (!gs.exist(gis))
				throw new IllegalStateException();
			gs.update(gis);
		}
		ModelAndView mv = new ModelAndView(HOME_JSP);
		List<GoodsInStock> k = gs.getAllGoods();
		mv.addObject("goods", k);
		return mv;
	}
}
