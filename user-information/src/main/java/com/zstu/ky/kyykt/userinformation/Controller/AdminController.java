package com.zstu.ky.kyykt.userinformation.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zstu.ky.RestResult.Result;
import com.zstu.ky.RestResult.ResultCode;
import com.zstu.ky.RestResult.ResultGenerator;
import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Entity.VO.AdminUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.AdminUserMapper;
import com.zstu.ky.kyykt.userinformation.Service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@Api("管理员用户信息管理")
public class AdminController {
    private final AdminUserService adminUserService;

    @Autowired
    public AdminController(AdminUserMapper adminUserMapper, AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    @ApiOperation("检索管理员用户列表")
    public Result getAdminUsers(Integer id, String username, String adminName, Integer index, Integer pageSize, String orderBy, String orderByDesc) {
        LambdaQueryWrapper<AdminUser> wrapper = Wrappers.<AdminUser>lambdaQuery();
        if (adminName != null) {
            wrapper.like(AdminUser::getAdminName, "%" + adminName + "%");
        }
        if (id != null) {
            wrapper.eq(AdminUser::getId, id);
        }

        if (orderBy != null) {
            var orders = orderBy.split("-");
            for (String order : orders) {
                if ("adminName".equals(order)) {
                    wrapper.orderByAsc(AdminUser::getAdminName);
                }
            }
        }

        if (orderByDesc != null) {
            var orders = orderByDesc.split("-");
            for (String order : orders) {
                if ("adminName".equals(order)) {
                    wrapper.orderByDesc(AdminUser::getAdminName);
                }
            }
        }

        Page page = new Page<>(index == null ? 1 : index, pageSize == null ? 20 : pageSize);
        var data = adminUserService.getAdminUsers(wrapper, page);
        return ResultGenerator.genSuccessResult().setData(data);
    }

    @PostMapping
    @ApiOperation("导入管理员用户列表")
    public Result postUser(@RequestBody AdminUserVO... adminUsers) throws Exception {
        for (AdminUserVO adminUser : adminUsers) {
            if (adminUser == null || adminUser.getUser() == null || adminUser.getAdmin() == null) {
                return ResultGenerator.genFailResult("创建用户失败，请检查参数。").setCode(ResultCode.BAD_REQUEST);
            }
        }
        try {
            adminUserService.insertAdminUser(adminUsers);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @PutMapping
    @ApiOperation("修改管理员用户信息")
    public Result putUser(@RequestBody AdminUserVO adminUserVO) {
        if (adminUserVO == null) {
            return ResultGenerator.genFailResult("Data is empty.");
        }
        if (adminUserVO.getAdmin() == null || adminUserVO.getUser() == null) {
            return ResultGenerator.genFailResult("Data is defect.");
        }
        try {
            adminUserService.modifyAdminUser(adminUserVO);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @DeleteMapping
    @ApiOperation("删除管理员信息")
    public Result deleteUser(@RequestBody Integer... id) {
        if (id == null) {
            return ResultGenerator.genFailResult("Id can not be null.");
        }
        try {
            adminUserService.deleteAdminUser(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

}
