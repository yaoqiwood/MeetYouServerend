package com.meetyou.system.controller;

import com.meetyou.system.common.model.Option;
import com.meetyou.system.common.result.Result;
import com.meetyou.system.model.form.MenuForm;
import com.meetyou.system.model.query.MenuQuery;
import com.meetyou.system.model.vo.MenuVO;
import com.meetyou.system.model.vo.RouteVO;
import com.meetyou.system.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import com.meetyou.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器
 *
 * @author haoxr
 * @since 2020/11/06
 */
@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@Slf4j
public class SysMenuController {

	private final SysMenuService menuService;

	@Operation(summary = "菜单列表")
	@GetMapping
	public Result<List<MenuVO>> listMenus(@ParameterObject MenuQuery queryParams) {
		List<MenuVO> menuList = menuService.listMenus(queryParams);
		return Result.success(menuList);
	}

	@Operation(summary = "菜单下拉列表")
	@GetMapping("/options")
	public Result listMenuOptions() {
		List<Option> menus = menuService.listMenuOptions();
		return Result.success(menus);
	}

	@Operation(summary = "路由列表")
	@GetMapping("/routes")
	public Result<List<RouteVO>> listRoutes(HttpServletRequest request) {
		List<RouteVO> routeList = menuService.listRoutes();
		return Result.success(routeList);
	}

	@Operation(summary = "菜单表单数据")
	@GetMapping("/{id}/form")
	public Result<MenuForm> getMenuForm(
		@Parameter(description = "菜单ID") @PathVariable Long id
	) {
		MenuForm menu = menuService.getMenuForm(id);
		return Result.success(menu);
	}

	@Operation(summary = "新增菜单")
	@PostMapping
	@PreAuthorize("@ss.hasPerm('sys:menu:add')")
	@PreventDuplicateSubmit
	public Result addMenu(@RequestBody MenuForm menuForm) {
		boolean result = menuService.saveMenu(menuForm);
		return Result.judge(result);
	}

	@Operation(summary = "修改菜单")
	@PutMapping(value = "/{id}")
	@PreAuthorize("@ss.hasPerm('sys:menu:edit')")
	public Result updateMenu(
		@RequestBody MenuForm menuForm
	) {
		boolean result = menuService.saveMenu(menuForm);
		return Result.judge(result);
	}

	@Operation(summary = "删除菜单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@ss.hasPerm('sys:menu:delete')")
	@CacheEvict(cacheNames = "system", key = "'routes'")
	public Result deleteMenu(
		@Parameter(description = "菜单ID，多个以英文(,)分割") @PathVariable("id") Long id
	) {
		boolean result = menuService.deleteMenu(id);
		return Result.judge(result);
	}

	@Operation(summary = "修改菜单显示状态")
	@PatchMapping("/{menuId}")
	public Result updateMenuVisible(
		@Parameter(description = "菜单ID") @PathVariable Long menuId,
		@Parameter(description = "显示状态(1:显示;0:隐藏)") Integer visible

	) {
		boolean result = menuService.updateMenuVisible(menuId, visible);
		return Result.judge(result);
	}


}

